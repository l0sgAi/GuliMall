/**
 * Copyright 2024 lzltool.com
 */

package com.losgai.gulimall.product.vo.spus;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Skus {

	private List<Attr> attr;
	private String skuName;
	private BigDecimal price;
	private String skuTitle;
	private String skuSubtitle;
	private List<Images> images;
	private List<String> descar;
	private Integer fullCount;
	private BigDecimal discount;
	private Integer countStatus;
	private BigDecimal fullPrice;
	private BigDecimal reducePrice;
	private Integer priceStatus;
	private List<MemberPrice> memberPrice;
}