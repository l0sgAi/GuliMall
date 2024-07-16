package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 商品三级分类
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class CategoryExcel {
    @ExcelProperty(value = "分类id")
    private Long catId;
    @ExcelProperty(value = "分类名称")
    private String name;
    @ExcelProperty(value = "父分类id")
    private Long parentCid;
    @ExcelProperty(value = "层级")
    private Integer catLevel;
    @ExcelProperty(value = "是否显示[0-不显示，1显示]")
    private Integer showStatus;
    @ExcelProperty(value = "排序")
    private Integer sort;
    @ExcelProperty(value = "图标地址")
    private String icon;
    @ExcelProperty(value = "计量单位")
    private String productUnit;
    @ExcelProperty(value = "商品数量")
    private Integer productCount;

}