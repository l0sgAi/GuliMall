package com.losgai.gulimall.member.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 会员等级
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "会员等级")
public class MemberLevelDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "等级名称")
	private String name;

	@SchemaProperty(name = "等级需要的成长值")
	private Integer growthPoint;

	@SchemaProperty(name = "是否为默认等级[0->不是；1->是]")
	private Integer defaultStatus;

	@SchemaProperty(name = "免运费标准")
	private BigDecimal freeFreightPoint;

	@SchemaProperty(name = "每次评价获取的成长值")
	private Integer commentGrowthPoint;

	@SchemaProperty(name = "是否有免邮特权")
	private Integer priviledgeFreeFreight;

	@SchemaProperty(name = "是否有会员价格特权")
	private Integer priviledgeMemberPrice;

	@SchemaProperty(name = "是否有生日特权")
	private Integer priviledgeBirthday;

	@SchemaProperty(name = "备注")
	private String note;


}
