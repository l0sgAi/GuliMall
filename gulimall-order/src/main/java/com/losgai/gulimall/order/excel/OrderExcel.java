package com.losgai.gulimall.order.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class OrderExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "member_id")
    private Long memberId;
    @ExcelProperty(value = "订单号")
    private String orderSn;
    @ExcelProperty(value = "使用的优惠券")
    private Long couponId;
    @ExcelProperty(value = "create_time")
    private Date createTime;
    @ExcelProperty(value = "用户名")
    private String memberUsername;
    @ExcelProperty(value = "订单总额")
    private BigDecimal totalAmount;
    @ExcelProperty(value = "应付总额")
    private BigDecimal payAmount;
    @ExcelProperty(value = "运费金额")
    private BigDecimal freightAmount;
    @ExcelProperty(value = "促销优化金额（促销价、满减、阶梯价）")
    private BigDecimal promotionAmount;
    @ExcelProperty(value = "积分抵扣金额")
    private BigDecimal integrationAmount;
    @ExcelProperty(value = "优惠券抵扣金额")
    private BigDecimal couponAmount;
    @ExcelProperty(value = "后台调整订单使用的折扣金额")
    private BigDecimal discountAmount;
    @ExcelProperty(value = "支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】")
    private Integer payType;
    @ExcelProperty(value = "订单来源[0->PC订单；1->app订单]")
    private Integer sourceType;
    @ExcelProperty(value = "订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    private Integer status;
    @ExcelProperty(value = "物流公司(配送方式)")
    private String deliveryCompany;
    @ExcelProperty(value = "物流单号")
    private String deliverySn;
    @ExcelProperty(value = "自动确认时间（天）")
    private Integer autoConfirmDay;
    @ExcelProperty(value = "可以获得的积分")
    private Integer integration;
    @ExcelProperty(value = "可以获得的成长值")
    private Integer growth;
    @ExcelProperty(value = "发票类型[0->不开发票；1->电子发票；2->纸质发票]")
    private Integer billType;
    @ExcelProperty(value = "发票抬头")
    private String billHeader;
    @ExcelProperty(value = "发票内容")
    private String billContent;
    @ExcelProperty(value = "收票人电话")
    private String billReceiverPhone;
    @ExcelProperty(value = "收票人邮箱")
    private String billReceiverEmail;
    @ExcelProperty(value = "收货人姓名")
    private String receiverName;
    @ExcelProperty(value = "收货人电话")
    private String receiverPhone;
    @ExcelProperty(value = "收货人邮编")
    private String receiverPostCode;
    @ExcelProperty(value = "省份/直辖市")
    private String receiverProvince;
    @ExcelProperty(value = "城市")
    private String receiverCity;
    @ExcelProperty(value = "区")
    private String receiverRegion;
    @ExcelProperty(value = "详细地址")
    private String receiverDetailAddress;
    @ExcelProperty(value = "订单备注")
    private String note;
    @ExcelProperty(value = "确认收货状态[0->未确认；1->已确认]")
    private Integer confirmStatus;
    @ExcelProperty(value = "删除状态【0->未删除；1->已删除】")
    private Integer deleteStatus;
    @ExcelProperty(value = "下单时使用的积分")
    private Integer useIntegration;
    @ExcelProperty(value = "支付时间")
    private Date paymentTime;
    @ExcelProperty(value = "发货时间")
    private Date deliveryTime;
    @ExcelProperty(value = "确认收货时间")
    private Date receiveTime;
    @ExcelProperty(value = "评价时间")
    private Date commentTime;
    @ExcelProperty(value = "修改时间")
    private Date modifyTime;

}