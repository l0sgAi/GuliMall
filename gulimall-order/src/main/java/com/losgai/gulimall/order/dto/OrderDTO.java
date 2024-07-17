package com.losgai.gulimall.order.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 订单
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "订单")
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "member_id")
	private Long memberId;

	@SchemaProperty(name = "订单号")
	private String orderSn;

	@SchemaProperty(name = "使用的优惠券")
	private Long couponId;

	@SchemaProperty(name = "create_time")
	private Date createTime;

	@SchemaProperty(name = "用户名")
	private String memberUsername;

	@SchemaProperty(name = "订单总额")
	private BigDecimal totalAmount;

	@SchemaProperty(name = "应付总额")
	private BigDecimal payAmount;

	@SchemaProperty(name = "运费金额")
	private BigDecimal freightAmount;

	@SchemaProperty(name = "促销优化金额（促销价、满减、阶梯价）")
	private BigDecimal promotionAmount;

	@SchemaProperty(name = "积分抵扣金额")
	private BigDecimal integrationAmount;

	@SchemaProperty(name = "优惠券抵扣金额")
	private BigDecimal couponAmount;

	@SchemaProperty(name = "后台调整订单使用的折扣金额")
	private BigDecimal discountAmount;

	@SchemaProperty(name = "支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】")
	private Integer payType;

	@SchemaProperty(name = "订单来源[0->PC订单；1->app订单]")
	private Integer sourceType;

	@SchemaProperty(name = "订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
	private Integer status;

	@SchemaProperty(name = "物流公司(配送方式)")
	private String deliveryCompany;

	@SchemaProperty(name = "物流单号")
	private String deliverySn;

	@SchemaProperty(name = "自动确认时间（天）")
	private Integer autoConfirmDay;

	@SchemaProperty(name = "可以获得的积分")
	private Integer integration;

	@SchemaProperty(name = "可以获得的成长值")
	private Integer growth;

	@SchemaProperty(name = "发票类型[0->不开发票；1->电子发票；2->纸质发票]")
	private Integer billType;

	@SchemaProperty(name = "发票抬头")
	private String billHeader;

	@SchemaProperty(name = "发票内容")
	private String billContent;

	@SchemaProperty(name = "收票人电话")
	private String billReceiverPhone;

	@SchemaProperty(name = "收票人邮箱")
	private String billReceiverEmail;

	@SchemaProperty(name = "收货人姓名")
	private String receiverName;

	@SchemaProperty(name = "收货人电话")
	private String receiverPhone;

	@SchemaProperty(name = "收货人邮编")
	private String receiverPostCode;

	@SchemaProperty(name = "省份/直辖市")
	private String receiverProvince;

	@SchemaProperty(name = "城市")
	private String receiverCity;

	@SchemaProperty(name = "区")
	private String receiverRegion;

	@SchemaProperty(name = "详细地址")
	private String receiverDetailAddress;

	@SchemaProperty(name = "订单备注")
	private String note;

	@SchemaProperty(name = "确认收货状态[0->未确认；1->已确认]")
	private Integer confirmStatus;

	@SchemaProperty(name = "删除状态【0->未删除；1->已删除】")
	private Integer deleteStatus;

	@SchemaProperty(name = "下单时使用的积分")
	private Integer useIntegration;

	@SchemaProperty(name = "支付时间")
	private Date paymentTime;

	@SchemaProperty(name = "发货时间")
	private Date deliveryTime;

	@SchemaProperty(name = "确认收货时间")
	private Date receiveTime;

	@SchemaProperty(name = "评价时间")
	private Date commentTime;

	@SchemaProperty(name = "修改时间")
	private Date modifyTime;


}
