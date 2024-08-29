package com.losgai.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("wms_purchase_detail")
public class WmsPurchaseDetailEntity {

    /**
     * 
     */
	private Long id;
    /**
     * 采购单id
     */
	private Long purchaseId;
    /**
     * 采购商品id
     */
	private Long skuId;
    /**
     * 采购数量
     */
	private Integer skuNum;
    /**
     * 采购金额
     */
	private BigDecimal skuPrice;
    /**
     * 仓库id
     */
	private Long wareId;
    /**
     * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
     */
	private Integer status;

    @TableLogic(value = "1", delval = "0")
    private Integer isShow;
}