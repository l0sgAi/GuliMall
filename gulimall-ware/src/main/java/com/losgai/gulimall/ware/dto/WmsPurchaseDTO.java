package com.losgai.gulimall.ware.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;

import java.io.Serial;
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
    @Serial
	private static final long serialVersionUID = 1L;

	@SchemaProperty()
	private Long id;

	@SchemaProperty()
	private Long assigneeId;

	@SchemaProperty()
	private String assigneeName;

	@SchemaProperty()
	private String phone;

	@SchemaProperty()
	private Integer priority;

	@SchemaProperty()
	private Integer status;

	@SchemaProperty()
	private Long wareId;

	@SchemaProperty()
	private BigDecimal amount;

	@SchemaProperty()
	private Date createTime;

	@SchemaProperty()
	private Date updateTime;

	@TableLogic(value = "1", delval = "0")
	private Integer isShow;
}
