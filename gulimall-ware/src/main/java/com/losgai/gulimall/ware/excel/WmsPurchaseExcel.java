package com.losgai.gulimall.ware.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class WmsPurchaseExcel {
    @ExcelProperty(value = "")
    private Long id;
    @ExcelProperty(value = "")
    private Long assigneeId;
    @ExcelProperty(value = "")
    private String assigneeName;
    @ExcelProperty(value = "")
    private String phone;
    @ExcelProperty(value = "")
    private Integer priority;
    @ExcelProperty(value = "")
    private Integer status;
    @ExcelProperty(value = "")
    private Long wareId;
    @ExcelProperty(value = "")
    private BigDecimal amount;
    @ExcelProperty(value = "")
    private Date createTime;
    @ExcelProperty(value = "")
    private Date updateTime;

}