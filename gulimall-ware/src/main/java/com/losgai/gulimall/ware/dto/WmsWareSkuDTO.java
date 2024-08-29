package com.losgai.gulimall.ware.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "商品库存")
public class WmsWareSkuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "sku_id")
	private Long skuId;

	@SchemaProperty(name = "仓库id")
	private Long wareId;

	@SchemaProperty(name = "库存数")
	private Integer stock;

	@SchemaProperty(name = "sku_name")
	private String skuName;

	@SchemaProperty(name = "锁定库存")
	private Integer stockLocked;

	@TableLogic(value = "1", delval = "0")
	private Integer isShow;
}
