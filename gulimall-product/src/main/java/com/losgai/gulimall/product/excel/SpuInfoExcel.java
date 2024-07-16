package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class SpuInfoExcel {
    @ExcelProperty(value = "商品id")
    private Long id;
    @ExcelProperty(value = "商品名称")
    private String spuName;
    @ExcelProperty(value = "商品描述")
    private String spuDescription;
    @ExcelProperty(value = "所属分类id")
    private Long catalogId;
    @ExcelProperty(value = "品牌id")
    private Long brandId;
    @ExcelProperty(value = "")
    private BigDecimal weight;
    @ExcelProperty(value = "上架状态[0 - 下架，1 - 上架]")
    private Integer publishStatus;
    @ExcelProperty(value = "")
    private Date createTime;
    @ExcelProperty(value = "")
    private Date updateTime;

}