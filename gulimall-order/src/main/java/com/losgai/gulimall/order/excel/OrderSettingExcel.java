package com.losgai.gulimall.order.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 订单配置信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class OrderSettingExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "秒杀订单超时关闭时间(分)")
    private Integer flashOrderOvertime;
    @ExcelProperty(value = "正常订单超时时间(分)")
    private Integer normalOrderOvertime;
    @ExcelProperty(value = "发货后自动确认收货时间（天）")
    private Integer confirmOvertime;
    @ExcelProperty(value = "自动完成交易时间，不能申请退货（天）")
    private Integer finishOvertime;
    @ExcelProperty(value = "订单完成后自动好评时间（天）")
    private Integer commentOvertime;
    @ExcelProperty(value = "会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
    private Integer memberLevel;

}