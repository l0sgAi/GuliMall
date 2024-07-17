package com.losgai.gulimall.ware.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 库存工作单
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "库存工作单")
public class WmsWareOrderTaskDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "sku_id")
	private Long skuId;

	@SchemaProperty(name = "sku_name")
	private String skuName;

	@SchemaProperty(name = "购买个数")
	private Integer skuNum;

	@SchemaProperty(name = "工作单id")
	private Long taskId;

	@SchemaProperty(name = "仓库id")
	private Long wareId;

	@SchemaProperty(name = "1-已锁定  2-已解锁  3-扣减")
	private Integer lockStatus;


}
