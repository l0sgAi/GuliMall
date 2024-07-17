package com.losgai.gulimall.member.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 会员登录记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "会员登录记录")
public class MemberLoginLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "member_id")
	private Long memberId;

	@SchemaProperty(name = "创建时间")
	private Date createTime;

	@SchemaProperty(name = "ip")
	private String ip;

	@SchemaProperty(name = "city")
	private String city;

	@SchemaProperty(name = "登录类型[1-web，2-app]")
	private Integer loginType;


}
