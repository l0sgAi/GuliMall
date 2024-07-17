package com.losgai.gulimall.coupon.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 秒杀活动场次
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "秒杀活动场次")
public class SeckillSessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "场次名称")
	private String name;

	@SchemaProperty(name = "每日开始时间")
	private Date startTime;

	@SchemaProperty(name = "每日结束时间")
	private Date endTime;

	@SchemaProperty(name = "启用状态")
	private Integer status;

	@SchemaProperty(name = "创建时间")
	private Date createTime;


}
