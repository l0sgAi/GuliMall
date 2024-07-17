package com.losgai.gulimall.ware.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 采购信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "采购信息")
public class WmsPurchaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "")
	private Long id;

	@SchemaProperty(name = "")
	private Long assigneeId;

	@SchemaProperty(name = "")
	private String assigneeName;

	@SchemaProperty(name = "")
	private String phone;

	@SchemaProperty(name = "")
	private Integer priority;

	@SchemaProperty(name = "")
	private Integer status;

	@SchemaProperty(name = "")
	private Long wareId;

	@SchemaProperty(name = "")
	private BigDecimal amount;

	@SchemaProperty(name = "")
	private Date createTime;

	@SchemaProperty(name = "")
	private Date updateTime;


}
