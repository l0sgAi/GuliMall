package com.losgai.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 首页轮播广告
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("sms_home_adv")
public class HomeAdvEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 名字
     */
	private String name;
    /**
     * 图片地址
     */
	private String pic;
    /**
     * 开始时间
     */
	private Date startTime;
    /**
     * 结束时间
     */
	private Date endTime;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 点击数
     */
	private Integer clickCount;
    /**
     * 广告详情连接地址
     */
	private String url;
    /**
     * 备注
     */
	private String note;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 发布者
     */
	private Long publisherId;
    /**
     * 审核者
     */
	private Long authId;
}