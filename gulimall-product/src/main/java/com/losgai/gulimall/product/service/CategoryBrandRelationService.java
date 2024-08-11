package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.CategoryBrandRelationDTO;
import com.losgai.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface CategoryBrandRelationService extends CrudService<CategoryBrandRelationEntity, CategoryBrandRelationDTO> {

    List<CategoryBrandRelationEntity> getCategoryRelation(Long brandId);
}