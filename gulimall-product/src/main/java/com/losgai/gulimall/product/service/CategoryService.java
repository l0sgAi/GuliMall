package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.service.CrudService;
import com.losgai.gulimall.product.dto.CategoryDTO;
import com.losgai.gulimall.product.entity.CategoryEntity;

import java.util.List;

/**
 * 商品三级分类
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface CategoryService extends CrudService<CategoryEntity, CategoryDTO> {

    List<CategoryEntity> listWithTree();
}