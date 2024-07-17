package com.losgai.gulimall.coupon.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 商品阶梯价格
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "商品阶梯价格")
public class SkuLadderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "spu_id")
	private Long skuId;

	@SchemaProperty(name = "满几件")
	private Integer fullCount;

	@SchemaProperty(name = "打几折")
	private BigDecimal discount;

	@SchemaProperty(name = "折后价")
	private BigDecimal price;

	@SchemaProperty(name = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
	private Integer addOther;


}
