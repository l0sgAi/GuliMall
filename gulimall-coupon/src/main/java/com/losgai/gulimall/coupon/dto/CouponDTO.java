package com.losgai.gulimall.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 优惠券信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "优惠券信息")
public class CouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
	private Integer couponType;

	@SchemaProperty(name = "优惠券图片")
	private String couponImg;

	@SchemaProperty(name = "优惠卷名字")
	private String couponName;

	@SchemaProperty(name = "数量")
	private Integer num;

	@SchemaProperty(name = "金额")
	private BigDecimal amount;

	@SchemaProperty(name = "每人限领张数")
	private Integer perLimit;

	@SchemaProperty(name = "使用门槛")
	private BigDecimal minPoint;

	@SchemaProperty(name = "开始时间")
	private Date startTime;

	@SchemaProperty(name = "结束时间")
	private Date endTime;

	@SchemaProperty(name = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
	private Integer useType;

	@SchemaProperty(name = "备注")
	private String note;

	@SchemaProperty(name = "发行数量")
	private Integer publishCount;

	@SchemaProperty(name = "已使用数量")
	private Integer useCount;

	@SchemaProperty(name = "领取数量")
	private Integer receiveCount;

	@SchemaProperty(name = "可以领取的开始日期")
	private Date enableStartTime;

	@SchemaProperty(name = "可以领取的结束日期")
	private Date enableEndTime;

	@SchemaProperty(name = "优惠码")
	private String code;

	@SchemaProperty(name = "可以领取的会员等级[0->不限等级，其他-对应等级]")
	private Integer memberLevel;

	@SchemaProperty(name = "发布状态[0-未发布，1-已发布]")
	private Integer publish;


}
