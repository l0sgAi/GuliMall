package com.losgai.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 会员收货地址
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("ums_member_receive_address")
public class MemberReceiveAddressEntity {

    /**
     * id
     */
	private Long id;
    /**
     * member_id
     */
	private Long memberId;
    /**
     * 收货人姓名
     */
	private String name;
    /**
     * 电话
     */
	private String phone;
    /**
     * 邮政编码
     */
	private String postCode;
    /**
     * 省份/直辖市
     */
	private String province;
    /**
     * 城市
     */
	private String city;
    /**
     * 区
     */
	private String region;
    /**
     * 详细地址(街道)
     */
	private String detailAddress;
    /**
     * 省市区代码
     */
	private String areacode;
    /**
     * 是否默认
     */
	private Integer defaultStatus;
}