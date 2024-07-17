package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品阶梯价格
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class SkuLadderExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "spu_id")
    private Long skuId;
    @ExcelProperty(value = "满几件")
    private Integer fullCount;
    @ExcelProperty(value = "打几折")
    private BigDecimal discount;
    @ExcelProperty(value = "折后价")
    private BigDecimal price;
    @ExcelProperty(value = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
    private Integer addOther;

}