package com.losgai.gulimall.member.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 会员统计信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "会员统计信息")
public class MemberStatisticsInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "会员id")
	private Long memberId;

	@SchemaProperty(name = "累计消费金额")
	private BigDecimal consumeAmount;

	@SchemaProperty(name = "累计优惠金额")
	private BigDecimal couponAmount;

	@SchemaProperty(name = "订单数量")
	private Integer orderCount;

	@SchemaProperty(name = "优惠券数量")
	private Integer couponCount;

	@SchemaProperty(name = "评价数")
	private Integer commentCount;

	@SchemaProperty(name = "退货数量")
	private Integer returnOrderCount;

	@SchemaProperty(name = "登录次数")
	private Integer loginCount;

	@SchemaProperty(name = "关注数量")
	private Integer attendCount;

	@SchemaProperty(name = "粉丝数量")
	private Integer fansCount;

	@SchemaProperty(name = "收藏的商品数量")
	private Integer collectProductCount;

	@SchemaProperty(name = "收藏的专题活动数量")
	private Integer collectSubjectCount;

	@SchemaProperty(name = "收藏的评论数量")
	private Integer collectCommentCount;

	@SchemaProperty(name = "邀请的朋友数量")
	private Integer inviteFriendCount;


}
