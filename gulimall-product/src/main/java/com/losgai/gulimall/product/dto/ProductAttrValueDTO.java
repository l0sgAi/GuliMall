package com.losgai.gulimall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * spu属性值
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "spu属性值")
public class ProductAttrValueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "商品id")
	private Long spuId;

	@SchemaProperty(name = "属性id")
	private Long attrId;

	@SchemaProperty(name = "属性名")
	private String attrName;

	@SchemaProperty(name = "属性值")
	private String attrValue;

	@SchemaProperty(name = "顺序")
	private Integer attrSort;

	@SchemaProperty(name = "快速展示【是否展示在介绍上；0-否 1-是】")
	private Integer quickShow;


}
