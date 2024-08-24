/**
 * Copyright 2024 lzltool.com
 */

package com.losgai.gulimall.product.vo.spus;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpuSaveVo {
	private String spuName;
	private String spuDescription;
	private Long catalogId;
	private Long brandId;
	private BigDecimal weight;
	private Integer publishStatus;
	private List<String> decript;
	private List<String> images;
	private Bounds bounds;
	private List<BaseAttrs> baseAttrs;
	private List<Skus> skus;
}