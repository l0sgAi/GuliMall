package com.losgai.gulimall.coupon.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
public class HomeSubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "专题名字")
	private String name;

	@SchemaProperty(name = "专题标题")
	private String title;

	@SchemaProperty(name = "专题副标题")
	private String subTitle;

	@SchemaProperty(name = "显示状态")
	private Integer status;

	@SchemaProperty(name = "详情连接")
	private String url;

	@SchemaProperty(name = "排序")
	private Integer sort;

	@SchemaProperty(name = "专题图片地址")
	private String img;


}
