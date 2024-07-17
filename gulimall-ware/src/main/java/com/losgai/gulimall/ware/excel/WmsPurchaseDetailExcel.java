package com.losgai.gulimall.ware.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class WmsPurchaseDetailExcel {
    @ExcelProperty(value = "")
    private Long id;
    @ExcelProperty(value = "采购单id")
    private Long purchaseId;
    @ExcelProperty(value = "采购商品id")
    private Long skuId;
    @ExcelProperty(value = "采购数量")
    private Integer skuNum;
    @ExcelProperty(value = "采购金额")
    private BigDecimal skuPrice;
    @ExcelProperty(value = "仓库id")
    private Long wareId;
    @ExcelProperty(value = "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    private Integer status;

}