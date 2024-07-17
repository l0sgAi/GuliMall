package com.losgai.gulimall.order.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 订单项信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "订单项信息")
public class OrderItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "order_id")
	private Long orderId;

	@SchemaProperty(name = "order_sn")
	private String orderSn;

	@SchemaProperty(name = "spu_id")
	private Long spuId;

	@SchemaProperty(name = "spu_name")
	private String spuName;

	@SchemaProperty(name = "spu_pic")
	private String spuPic;

	@SchemaProperty(name = "品牌")
	private String spuBrand;

	@SchemaProperty(name = "商品分类id")
	private Long categoryId;

	@SchemaProperty(name = "商品sku编号")
	private Long skuId;

	@SchemaProperty(name = "商品sku名字")
	private String skuName;

	@SchemaProperty(name = "商品sku图片")
	private String skuPic;

	@SchemaProperty(name = "商品sku价格")
	private BigDecimal skuPrice;

	@SchemaProperty(name = "商品购买的数量")
	private Integer skuQuantity;

	@SchemaProperty(name = "商品销售属性组合（JSON）")
	private String skuAttrsVals;

	@SchemaProperty(name = "商品促销分解金额")
	private BigDecimal promotionAmount;

	@SchemaProperty(name = "优惠券优惠分解金额")
	private BigDecimal couponAmount;

	@SchemaProperty(name = "积分优惠分解金额")
	private BigDecimal integrationAmount;

	@SchemaProperty(name = "该商品经过优惠后的分解金额")
	private BigDecimal realAmount;

	@SchemaProperty(name = "赠送积分")
	private Integer giftIntegration;

	@SchemaProperty(name = "赠送成长值")
	private Integer giftGrowth;


}
