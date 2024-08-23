/**
 * Copyright 2024 lzltool.com
 */

package com.losgai.gulimall.product.vo.spus;

import lombok.Data;
import java.util.List;

@Data
public class Skus {

	private List<Attr> attr;
	private String skuName;
	private Integer price;
	private String skuTitle;
	private String skuSubtitle;
	private List<String> images;
	private List<String> descar;
	private Integer fullCount;
	private Integer discount;
	private Integer countStatus;
	private Integer fullPrice;
	private Integer reducePrice;
	private Integer priceStatus;
	private List<MemberPrice> memberPrice;
}