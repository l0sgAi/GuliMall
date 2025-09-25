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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 商品三级分类
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class CategoryServiceImpl extends CrudServiceImpl<CategoryDao, CategoryEntity, CategoryDTO> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    // 缓存key前缀
    private static final String CATEGORY_CACHE_KEY = "product:category:tree";
    // 缓存过期时间（1天）
    private static final long CACHE_EXPIRE_TIME = 1;
    private static final TimeUnit CACHE_EXPIRE_UNIT = TimeUnit.DAYS;

    @Override
    public QueryWrapper<CategoryEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CategoryEntity> listWithTree() {
        // 1. 先从缓存中获取
        String cacheData = stringRedisTemplate.opsForValue().get(CATEGORY_CACHE_KEY);
        
        if (StringUtils.hasText(cacheData)) {
            // 缓存中有数据，直接返回
            try {
                return objectMapper.readValue(cacheData, new TypeReference<List<CategoryEntity>>() {});
            } catch (JsonProcessingException e) {
                // 如果解析失败，继续从数据库获取
                e.printStackTrace();
            }
        }
        
        // 2. 缓存中没有数据，从数据库查询
        //2.1 查出所有分类
        List<CategoryEntity> categoryList = baseDao.selectList(null);
        //2.2 递归组装树型结构
        //2.3 找到所有的一级分类，并进行递归封装
        List<CategoryEntity> treeList = categoryList
                .stream()
                .filter(category -> category.getParentCid() == 0 && category.getShowStatus()==1)
                .peek((menu) -> menu.setChildren(getChildren(menu, categoryList)))
                .sorted((menu1,menu2)-> menu1.getSort() == null ? 0 : menu1.getSort() - (menu2.getSort() == null ? 0 : menu2.getSort()))
                .toList();
        
        // 3. 将查询结果放入缓存
        try {
            String jsonString = objectMapper.writeValueAsString(treeList);
            stringRedisTemplate.opsForValue().set(CATEGORY_CACHE_KEY, jsonString, CACHE_EXPIRE_TIME, CACHE_EXPIRE_UNIT);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return treeList;
    }

    @Override
    public void removeMenus(List<Long> ids) {
        //TODO 1.检查当前删除的菜单是否在别的地方引用
        baseDao.deleteBatchIds(ids);
        
        // 删除后清除缓存
        clearCategoryCache();
    }

    @Override
    @Transactional
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
        
        // 更新后清除缓存
        clearCategoryCache();
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()) && categoryEntity.getShowStatus()==1)
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, all)))
                .sorted((menu1,menu2)-> menu1.getSort() == null ? 0 : menu1.getSort() - (menu2.getSort() == null ? 0 : menu2.getSort()))
                .toList();
    }
    
    /**
     * 清除分类缓存
     */
    private void clearCategoryCache() {
        stringRedisTemplate.delete(CATEGORY_CACHE_KEY);
    }
    
    /**
     * 重写父类的save方法，在保存后清除缓存
     */
    @Override
    public void save(CategoryDTO dto) {
        super.save(dto);
        // 新增分类后清除缓存
        clearCategoryCache();
    }
    
    /**
     * 重写父类的update方法，在更新后清除缓存
     */
    @Override
    public void update(CategoryDTO dto) {
        super.update(dto);
        // 更新分类后清除缓存
        clearCategoryCache();
    }
    
    /**
     * 重写父类的delete方法，在删除后清除缓存
     */
    @Override
    public void delete(Long[] ids) {
        super.delete(ids);
        // 删除分类后清除缓存
        clearCategoryCache();
    }
}