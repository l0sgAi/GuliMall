package com.losgai.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 秒杀商品通知订阅
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("sms_seckill_sku_notice")
public class SeckillSkuNoticeEntity {

    /**
     * id
     */
	private Long id;
    /**
     * member_id
     */
	private Long memberId;
    /**
     * sku_id
     */
	private Long skuId;
    /**
     * 活动场次id
     */
	private Long sessionId;
    /**
     * 订阅时间
     */
	private Date subcribeTime;
    /**
     * 发送时间
     */
	private Date sendTime;
    /**
     * 通知方式[0-短信，1-邮件]
     */
	private Integer noticeType;
}