/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "操作日志")
public class SysLogOperationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	private Long id;

	@Schema(title = "用户操作")
	private String operation;

	@Schema(title = "请求URI")
	private String requestUri;

	@Schema(title = "请求方式")
	private String requestMethod;

	@Schema(title = "请求参数")
	private String requestParams;

	@Schema(title = "请求时长(毫秒)")
	private Integer requestTime;

	@Schema(title = "用户代理")
	private String userAgent;

	@Schema(title = "操作IP")
	private String ip;

	@Schema(title = "状态  0：失败   1：成功")
	private Integer status;

	@Schema(title = "用户名")
	private String creatorName;

	@Schema(title = "创建时间")
	private Date createDate;

}
