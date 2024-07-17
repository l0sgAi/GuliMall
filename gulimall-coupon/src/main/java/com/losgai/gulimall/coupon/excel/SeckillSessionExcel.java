package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀活动场次
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class SeckillSessionExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "场次名称")
    private String name;
    @ExcelProperty(value = "每日开始时间")
    private Date startTime;
    @ExcelProperty(value = "每日结束时间")
    private Date endTime;
    @ExcelProperty(value = "启用状态")
    private Integer status;
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}