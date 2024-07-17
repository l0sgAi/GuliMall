package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 优惠券领取历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class CouponHistoryExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "优惠券id")
    private Long couponId;
    @ExcelProperty(value = "会员id")
    private Long memberId;
    @ExcelProperty(value = "会员名字")
    private String memberNickName;
    @ExcelProperty(value = "获取方式[0->后台赠送；1->主动领取]")
    private Integer getType;
    @ExcelProperty(value = "创建时间")
    private Date createTime;
    @ExcelProperty(value = "使用状态[0->未使用；1->已使用；2->已过期]")
    private Integer useType;
    @ExcelProperty(value = "使用时间")
    private Date useTime;
    @ExcelProperty(value = "订单id")
    private Long orderId;
    @ExcelProperty(value = "订单号")
    private Long orderSn;

}