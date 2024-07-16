package com.losgai.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 品牌分类关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity {

    /**
     * 
     */
	private Long id;
    /**
     * 品牌id
     */
	private Long brandId;
    /**
     * 分类id
     */
	private Long catelogId;
    /**
     * 
     */
	private String brandName;
    /**
     * 
     */
	private String catelogName;
}