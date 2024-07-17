package com.losgai.gulimall.order.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 订单操作历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "订单操作历史记录")
public class OrderOperateHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "订单id")
	private Long orderId;

	@SchemaProperty(name = "操作人[用户；系统；后台管理员]")
	private String operateMan;

	@SchemaProperty(name = "操作时间")
	private Date createTime;

	@SchemaProperty(name = "订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
	private Integer orderStatus;

	@SchemaProperty(name = "备注")
	private String note;


}
