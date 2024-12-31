package com.losgai.gulimall.ware.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * sku信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "sku信息")
public class SkuInfoDTO implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "skuId")
	private Long skuId;

	@SchemaProperty(name = "spuId")
	private Long spuId;

	@SchemaProperty(name = "sku名称")
	private String skuName;

	@SchemaProperty(name = "sku介绍描述")
	private String skuDesc;

	@SchemaProperty(name = "所属分类id")
	private Long catalogId;

	@SchemaProperty(name = "品牌id")
	private Long brandId;

	@SchemaProperty(name = "默认图片")
	private String skuDefaultImg;

	@SchemaProperty(name = "标题")
	private String skuTitle;

	@SchemaProperty(name = "副标题")
	private String skuSubtitle;

	@SchemaProperty(name = "价格")
	private BigDecimal price;

	@SchemaProperty(name = "销量")
	private Long saleCount;


}
