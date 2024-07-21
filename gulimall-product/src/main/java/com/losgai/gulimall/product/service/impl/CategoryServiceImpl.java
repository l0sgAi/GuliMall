package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.CategoryDao;
import com.losgai.gulimall.product.dto.CategoryDTO;
import com.losgai.gulimall.product.entity.CategoryEntity;
import com.losgai.gulimall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class CategoryServiceImpl extends CrudServiceImpl<CategoryDao, CategoryEntity, CategoryDTO> implements CategoryService {

    @Override
    public QueryWrapper<CategoryEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> categoryList = baseDao.selectList(null);
        //2.递归组装树型结构
        //2.1 找到所有的一级分类，并进行递归封装
        return categoryList
                .stream()
                .filter(category -> category.getParentCid() == 0)
                .peek((menu) -> menu.setChildren(getChildren(menu, categoryList)))
                .sorted((menu1,menu2)-> menu1.getSort() == null ? 0 : menu1.getSort() - (menu2.getSort() == null ? 0 : menu2.getSort()))
                .toList();
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, all)))
                .sorted((menu1,menu2)-> menu1.getSort() == null ? 0 : menu1.getSort() - (menu2.getSort() == null ? 0 : menu2.getSort()))
                .toList();
    }
}