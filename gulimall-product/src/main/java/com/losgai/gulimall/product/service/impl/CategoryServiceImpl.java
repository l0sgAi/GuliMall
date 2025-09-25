package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.CategoryBrandRelationDao;
import com.losgai.gulimall.product.dao.CategoryDao;
import com.losgai.gulimall.product.dto.CategoryDTO;
import com.losgai.gulimall.product.entity.CategoryBrandRelationEntity;
import com.losgai.gulimall.product.entity.CategoryEntity;
import com.losgai.gulimall.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 商品三级分类
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CrudServiceImpl<CategoryDao, CategoryEntity, CategoryDTO> implements CategoryService {

    private final CategoryDao categoryDao;

    private final CategoryBrandRelationDao categoryBrandRelationDao;

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public QueryWrapper<CategoryEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    @Cacheable(value = "category", key = "'tree'")
    public List<CategoryEntity> listWithTree() {

//        synchronized (){ // 数据库查询加锁，同步到缓存，防止过大并发
//                单机锁，需要改分布式锁
//        }
        List<CategoryEntity> list = new ArrayList<>();
        // 占分布式锁，设置过期时间
        String uuid = UUID.randomUUID().toString();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(lock)) { // 加锁成功
            //1.查出所有分类
            List<CategoryEntity> categoryList = baseDao.selectList(null);
            //2.递归组装树型结构
            //2.1 找到所有的一级分类，并进行递归封装
            list = categoryList
                    .stream()
                    .filter(category -> category.getParentCid() == 0 && category.getShowStatus() == 1)
                    .peek((menu) -> menu.setChildren(getChildren(menu, categoryList)))
                    .sorted((menu1, menu2) -> menu1.getSort() == null ? 0 : menu1.getSort() - (menu2.getSort() == null ? 0 : menu2.getSort()))
                    .toList();
            // 检查锁值
            String lockUuid = stringRedisTemplate.opsForValue().get("lock");
            if (StrUtil.isNotBlank(lockUuid) && lockUuid.equals(uuid)) {
                // 只删除自己对应uuid的锁
                stringRedisTemplate.delete("lock");
            }

        } else { // 锁失败，重试
            // 自旋
            return listWithTree();
        }
        return list;
    }

    @Override
    @CacheEvict(value = "category", key = "'tree'")
    public void removeMenus(List<Long> ids) {
        //TODO 1.检查当前删除的菜单是否在别的地方引用
        baseDao.deleteBatchIds(ids);
    }

    @Override
    @Transactional
    @CacheEvict(value = "category", key = "'tree'")
    public void doBatchUpdate(CategoryEntity dto) {
        categoryDao.updateById(dto);

        Long catId = dto.getCatId();
        String catelogName = dto.getName();

        // 创建UpdateWrapper实例
        UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件
        updateWrapper.eq("catelog_id", catId);
        // 设置要更新的字段
        updateWrapper.set("catelog_name", catelogName);

        categoryBrandRelationDao.update(updateWrapper);
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()) && categoryEntity.getShowStatus() == 1)
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, all)))
                .sorted((menu1, menu2) -> menu1.getSort() == null ? 0 : menu1.getSort() - (menu2.getSort() == null ? 0 : menu2.getSort()))
                .toList();
    }
}