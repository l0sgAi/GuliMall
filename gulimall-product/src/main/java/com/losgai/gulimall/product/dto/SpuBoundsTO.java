package com.losgai.gulimall.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpuBoundsTO {
	private Long spuId;
    /**
     * 成长积分
     */
	private BigDecimal growBounds;
    /**
     * 购物积分
     */
	private BigDecimal buyBounds;
}