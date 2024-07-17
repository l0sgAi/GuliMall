package com.losgai.gulimall.coupon.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 商品满减信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "商品满减信息")
public class SkuFullReductionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "spu_id")
	private Long skuId;

	@SchemaProperty(name = "满多少")
	private BigDecimal fullPrice;

	@SchemaProperty(name = "减多少")
	private BigDecimal reducePrice;

	@SchemaProperty(name = "是否参与其他优惠")
	private Integer addOther;


}
