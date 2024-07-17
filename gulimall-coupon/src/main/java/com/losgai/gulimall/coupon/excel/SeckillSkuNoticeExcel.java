package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀商品通知订阅
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class SeckillSkuNoticeExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "member_id")
    private Long memberId;
    @ExcelProperty(value = "sku_id")
    private Long skuId;
    @ExcelProperty(value = "活动场次id")
    private Long sessionId;
    @ExcelProperty(value = "订阅时间")
    private Date subcribeTime;
    @ExcelProperty(value = "发送时间")
    private Date sendTime;
    @ExcelProperty(value = "通知方式[0-短信，1-邮件]")
    private Integer noticeType;

}