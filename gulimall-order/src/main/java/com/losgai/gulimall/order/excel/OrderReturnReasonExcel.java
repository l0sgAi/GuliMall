package com.losgai.gulimall.order.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 退货原因
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class OrderReturnReasonExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "退货原因名")
    private String name;
    @ExcelProperty(value = "排序")
    private Integer sort;
    @ExcelProperty(value = "启用状态")
    private Integer status;
    @ExcelProperty(value = "create_time")
    private Date createTime;

}