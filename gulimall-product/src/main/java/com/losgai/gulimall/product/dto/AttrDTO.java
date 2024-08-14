package com.losgai.gulimall.product.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 商品属性
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "商品属性")
public class AttrDTO implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "属性id")
	@NotNull(message = "修改时id不能为空",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
	private Long attrId;

	@SchemaProperty(name = "属性名")
	@NotBlank(message = "属性名不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String attrName;

	@SchemaProperty(name = "是否需要检索[0-不需要，1-需要]")
	private Integer searchType;

	@SchemaProperty(name = "属性图标")
	private String icon;

	@SchemaProperty(name = "可选值列表[用逗号分隔]")
	private String valueSelect;

	@SchemaProperty(name = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
	@NotNull(message = "属性类型不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer attrType;

	@SchemaProperty(name = "启用状态[0 - 禁用，1 - 启用]")
	@NotNull(message = "启用状态不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Long enable;

	@SchemaProperty(name = "所属分类")
	@NotNull(message = "所属分类不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Long catelogId;

	@SchemaProperty(name = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
	private Integer showDesc;

	@TableLogic(value = "1",delval = "0")
	private Integer isShow;


}
