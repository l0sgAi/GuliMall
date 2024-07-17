package com.losgai.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("sms_home_subject")
public class HomeSubjectEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 专题名字
     */
	private String name;
    /**
     * 专题标题
     */
	private String title;
    /**
     * 专题副标题
     */
	private String subTitle;
    /**
     * 显示状态
     */
	private Integer status;
    /**
     * 详情连接
     */
	private String url;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 专题图片地址
     */
	private String img;
}