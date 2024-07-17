package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀活动
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class SeckillPromotionExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "活动标题")
    private String title;
    @ExcelProperty(value = "开始日期")
    private Date startTime;
    @ExcelProperty(value = "结束日期")
    private Date endTime;
    @ExcelProperty(value = "上下线状态")
    private Integer status;
    @ExcelProperty(value = "创建时间")
    private Date createTime;
    @ExcelProperty(value = "创建人")
    private Long userId;

}