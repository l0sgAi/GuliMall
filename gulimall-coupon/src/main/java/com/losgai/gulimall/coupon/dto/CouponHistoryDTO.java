package com.losgai.gulimall.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 优惠券领取历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "优惠券领取历史记录")
public class CouponHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "优惠券id")
	private Long couponId;

	@SchemaProperty(name = "会员id")
	private Long memberId;

	@SchemaProperty(name = "会员名字")
	private String memberNickName;

	@SchemaProperty(name = "获取方式[0->后台赠送；1->主动领取]")
	private Integer getType;

	@SchemaProperty(name = "创建时间")
	private Date createTime;

	@SchemaProperty(name = "使用状态[0->未使用；1->已使用；2->已过期]")
	private Integer useType;

	@SchemaProperty(name = "使用时间")
	private Date useTime;

	@SchemaProperty(name = "订单id")
	private Long orderId;

	@SchemaProperty(name = "订单号")
	private Long orderSn;


}
