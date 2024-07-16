package com.losgai.gulimall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 品牌分类关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "品牌分类关联")
public class CategoryBrandRelationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "")
	private Long id;

	@SchemaProperty(name = "品牌id")
	private Long brandId;

	@SchemaProperty(name = "分类id")
	private Long catelogId;

	@SchemaProperty(name = "")
	private String brandName;

	@SchemaProperty(name = "")
	private String catelogName;


}
