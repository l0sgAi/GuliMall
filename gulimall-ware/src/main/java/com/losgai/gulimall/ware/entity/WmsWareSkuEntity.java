package com.losgai.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("wms_ware_sku")
public class WmsWareSkuEntity {

    /**
     * id
     */
	private Long id;
    /**
     * sku_id
     */
	private Long skuId;
    /**
     * 仓库id
     */
	private Long wareId;
    /**
     * 库存数
     */
	private Integer stock;
    /**
     * sku_name
     */
	private String skuName;
    /**
     * 锁定库存
     */
	private Integer stockLocked;

    @TableLogic(value = "1", delval = "0")
    private Integer isShow;
}