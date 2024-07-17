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
public class WmsWareOrderTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "order_id")
	private Long orderId;

	@SchemaProperty(name = "order_sn")
	private String orderSn;

	@SchemaProperty(name = "收货人")
	private String consignee;

	@SchemaProperty(name = "收货人电话")
	private String consigneeTel;

	@SchemaProperty(name = "配送地址")
	private String deliveryAddress;

	@SchemaProperty(name = "订单备注")
	private String orderComment;

	@SchemaProperty(name = "付款方式【 1:在线付款 2:货到付款】")
	private Integer paymentWay;

	@SchemaProperty(name = "任务状态")
	private Integer taskStatus;

	@SchemaProperty(name = "订单描述")
	private String orderBody;

	@SchemaProperty(name = "物流单号")
	private String trackingNo;

	@SchemaProperty(name = "create_time")
	private Date createTime;

	@SchemaProperty(name = "仓库id")
	private Long wareId;

	@SchemaProperty(name = "工作单备注")
	private String taskComment;


}
