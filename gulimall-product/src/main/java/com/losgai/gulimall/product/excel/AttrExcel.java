package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 商品属性
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class AttrExcel {
    @ExcelProperty(value = "属性id")
    private Long attrId;
    @ExcelProperty(value = "属性名")
    private String attrName;
    @ExcelProperty(value = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;
    @ExcelProperty(value = "属性图标")
    private String icon;
    @ExcelProperty(value = "可选值列表[用逗号分隔]")
    private String valueSelect;
    @ExcelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;
    @ExcelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
    private Long enable;
    @ExcelProperty(value = "所属分类")
    private Long catelogId;
    @ExcelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Integer showDesc;

}