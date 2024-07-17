package com.losgai.gulimall.order.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 订单退货申请
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "订单退货申请")
public class OrderReturnApplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "order_id")
	private Long orderId;

	@SchemaProperty(name = "退货商品id")
	private Long skuId;

	@SchemaProperty(name = "订单编号")
	private String orderSn;

	@SchemaProperty(name = "申请时间")
	private Date createTime;

	@SchemaProperty(name = "会员用户名")
	private String memberUsername;

	@SchemaProperty(name = "退款金额")
	private BigDecimal returnAmount;

	@SchemaProperty(name = "退货人姓名")
	private String returnName;

	@SchemaProperty(name = "退货人电话")
	private String returnPhone;

	@SchemaProperty(name = "申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]")
	private Integer status;

	@SchemaProperty(name = "处理时间")
	private Date handleTime;

	@SchemaProperty(name = "商品图片")
	private String skuImg;

	@SchemaProperty(name = "商品名称")
	private String skuName;

	@SchemaProperty(name = "商品品牌")
	private String skuBrand;

	@SchemaProperty(name = "商品销售属性(JSON)")
	private String skuAttrsVals;

	@SchemaProperty(name = "退货数量")
	private Integer skuCount;

	@SchemaProperty(name = "商品单价")
	private BigDecimal skuPrice;

	@SchemaProperty(name = "商品实际支付单价")
	private BigDecimal skuRealPrice;

	@SchemaProperty(name = "原因")
	private String reason;

	@SchemaProperty(name = "描述")
	private String description述;

	@SchemaProperty(name = "凭证图片，以逗号隔开")
	private String descPics;

	@SchemaProperty(name = "处理备注")
	private String handleNote;

	@SchemaProperty(name = "处理人员")
	private String handleMan;

	@SchemaProperty(name = "收货人")
	private String receiveMan;

	@SchemaProperty(name = "收货时间")
	private Date receiveTime;

	@SchemaProperty(name = "收货备注")
	private String receiveNote;

	@SchemaProperty(name = "收货电话")
	private String receivePhone;

	@SchemaProperty(name = "公司收货地址")
	private String companyAddress;


}
