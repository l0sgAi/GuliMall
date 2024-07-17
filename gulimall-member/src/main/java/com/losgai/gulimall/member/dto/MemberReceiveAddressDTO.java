package com.losgai.gulimall.member.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 会员收货地址
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "会员收货地址")
public class MemberReceiveAddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "member_id")
	private Long memberId;

	@SchemaProperty(name = "收货人姓名")
	private String name;

	@SchemaProperty(name = "电话")
	private String phone;

	@SchemaProperty(name = "邮政编码")
	private String postCode;

	@SchemaProperty(name = "省份/直辖市")
	private String province;

	@SchemaProperty(name = "城市")
	private String city;

	@SchemaProperty(name = "区")
	private String region;

	@SchemaProperty(name = "详细地址(街道)")
	private String detailAddress;

	@SchemaProperty(name = "省市区代码")
	private String areacode;

	@SchemaProperty(name = "是否默认")
	private Integer defaultStatus;


}
