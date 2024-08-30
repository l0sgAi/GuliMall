package com.losgai.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("wms_purchase")
public class WmsPurchaseEntity {

    /**
     * 
     */
	private Long id;
    /**
     * 
     */
	private Long assigneeId;
    /**
     * 
     */
	private String assigneeName;
    /**
     * 
     */
	private String phone;
    /**
     * 
     */
	private Integer priority;
    /**
     * 
     */
	private Integer status;
    /**
     * 
     */
	private Long wareId;
    /**
     * 
     */
	private BigDecimal amount;
    /**
     * 
     */
	private Date createTime;
    /**
     * 
     */
	private Date updateTime;

    @TableLogic(value = "1", delval = "0")
    private Integer isShow;
}