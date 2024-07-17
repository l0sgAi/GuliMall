package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class CouponExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    private Integer couponType;
    @ExcelProperty(value = "优惠券图片")
    private String couponImg;
    @ExcelProperty(value = "优惠卷名字")
    private String couponName;
    @ExcelProperty(value = "数量")
    private Integer num;
    @ExcelProperty(value = "金额")
    private BigDecimal amount;
    @ExcelProperty(value = "每人限领张数")
    private Integer perLimit;
    @ExcelProperty(value = "使用门槛")
    private BigDecimal minPoint;
    @ExcelProperty(value = "开始时间")
    private Date startTime;
    @ExcelProperty(value = "结束时间")
    private Date endTime;
    @ExcelProperty(value = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
    private Integer useType;
    @ExcelProperty(value = "备注")
    private String note;
    @ExcelProperty(value = "发行数量")
    private Integer publishCount;
    @ExcelProperty(value = "已使用数量")
    private Integer useCount;
    @ExcelProperty(value = "领取数量")
    private Integer receiveCount;
    @ExcelProperty(value = "可以领取的开始日期")
    private Date enableStartTime;
    @ExcelProperty(value = "可以领取的结束日期")
    private Date enableEndTime;
    @ExcelProperty(value = "优惠码")
    private String code;
    @ExcelProperty(value = "可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Integer memberLevel;
    @ExcelProperty(value = "发布状态[0-未发布，1-已发布]")
    private Integer publish;

}