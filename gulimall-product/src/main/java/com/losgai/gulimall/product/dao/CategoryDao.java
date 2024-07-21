package com.losgai.gulimall.product.dao;

import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.product.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {
	
}