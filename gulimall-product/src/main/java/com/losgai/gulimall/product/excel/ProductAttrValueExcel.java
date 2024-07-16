package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * spu属性值
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class ProductAttrValueExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "商品id")
    private Long spuId;
    @ExcelProperty(value = "属性id")
    private Long attrId;
    @ExcelProperty(value = "属性名")
    private String attrName;
    @ExcelProperty(value = "属性值")
    private String attrValue;
    @ExcelProperty(value = "顺序")
    private Integer attrSort;
    @ExcelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
    private Integer quickShow;

}