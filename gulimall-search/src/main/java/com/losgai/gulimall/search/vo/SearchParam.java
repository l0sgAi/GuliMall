package com.losgai.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * 封装页面所有可能的查询条件
 * */
@Data
public class SearchParam {

    private String keyword; // 搜索关键字
    private Long catalogId; // 分类id

    /**
     * sort = "saleCount_asc/desc"
     * sort = "skuPrice_asc/desc"
     * sort = "hotScore_asc/desc" (默认)
     * */
    private String sort = "hotScore_desc"; // 排序条件

    /**
     * 过滤条件hasStock/skuPrice区间/brandId/catalogId/attrIds
     * */
    private Integer hasStock;
    private Integer minimumPrice = 0;
    private Integer maximumPrice = Integer.MAX_VALUE;
    private List<Long> brandIds;
    private List<Long> catalogIds;
    private List<String> attrs; // 属性名称查询

}


