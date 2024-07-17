package com.losgai.gulimall.coupon.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 商品spu积分设置
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "商品spu积分设置")
public class SpuBoundsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "")
	private Long spuId;

	@SchemaProperty(name = "成长积分")
	private BigDecimal growBounds;

	@SchemaProperty(name = "购物积分")
	private BigDecimal buyBounds;

	@SchemaProperty(name = "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
	private Integer work;


}
