package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * sku信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class SkuInfoExcel {
    @ExcelProperty(value = "skuId")
    private Long skuId;
    @ExcelProperty(value = "spuId")
    private Long spuId;
    @ExcelProperty(value = "sku名称")
    private String skuName;
    @ExcelProperty(value = "sku介绍描述")
    private String skuDesc;
    @ExcelProperty(value = "所属分类id")
    private Long catalogId;
    @ExcelProperty(value = "品牌id")
    private Long brandId;
    @ExcelProperty(value = "默认图片")
    private String skuDefaultImg;
    @ExcelProperty(value = "标题")
    private String skuTitle;
    @ExcelProperty(value = "副标题")
    private String skuSubtitle;
    @ExcelProperty(value = "价格")
    private BigDecimal price;
    @ExcelProperty(value = "销量")
    private Long saleCount;

}