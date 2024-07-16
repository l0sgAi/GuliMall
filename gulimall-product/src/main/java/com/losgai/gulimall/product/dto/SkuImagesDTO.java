package com.losgai.gulimall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * sku图片
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "sku图片")
public class SkuImagesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "sku_id")
	private Long skuId;

	@SchemaProperty(name = "图片地址")
	private String imgUrl;

	@SchemaProperty(name = "排序")
	private Integer imgSort;

	@SchemaProperty(name = "默认图[0 - 不是默认图，1 - 是默认图]")
	private Integer defaultImg;


}
