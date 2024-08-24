/**
 * Copyright 2024 lzltool.com
 */

package com.losgai.gulimall.product.vo.spus;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPrice {

	private Long id;
	private String name;
	private BigDecimal price;
}