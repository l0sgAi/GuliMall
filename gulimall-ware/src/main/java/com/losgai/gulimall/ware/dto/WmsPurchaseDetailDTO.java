package com.losgai.gulimall.ware.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema()
public class WmsPurchaseDetailDTO implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;

	@SchemaProperty()
	private Long id;

	@SchemaProperty(name = "采购单id")
	private Long purchaseId;

	@SchemaProperty(name = "采购商品id")
	private Long skuId;

	@SchemaProperty(name = "采购数量")
	private Integer skuNum;

	@SchemaProperty(name = "采购金额")
	private BigDecimal skuPrice;

	@SchemaProperty(name = "仓库id")
	private Long wareId;

	@SchemaProperty(name = "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
	private Integer status;

	@TableLogic(value = "1", delval = "0")
	private Integer isShow;


}
