package com.losgai.gulimall.product.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.losgai.gulimall.common.common.validator.annotation.ListValue;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;


/**
 * 品牌
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "品牌")
public class BrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "品牌id")
	@NotNull(message = "修改时品牌id不能为空",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
	private Long brandId;

	@SchemaProperty(name = "品牌名")
	@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class})
	private String name;

	@NotEmpty(message = "品牌logo地址不能为空",groups = {AddGroup.class})
	@SchemaProperty(name = "品牌logo地址")
	@URL(message = "logo必须是一个合法的url地址",groups = {AddGroup.class, UpdateGroup.class})
	private String logo;

	@SchemaProperty(name = "介绍")
	private String descript;

	@SchemaProperty(name = "显示状态[0-不显示；1-显示]")
	@ListValue(vals = {0,1},groups = {AddGroup.class, UpdateGroup.class})
	private Integer showStatus;

	@NotEmpty(message = "检索首字母不能为空",groups = {AddGroup.class})
	@SchemaProperty(name = "检索首字母")
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母",groups = {AddGroup.class, UpdateGroup.class})
	private String firstLetter;

	@NotNull(message = "排序不能为空",groups = {AddGroup.class})
	@SchemaProperty(name = "排序")
	@Min(value = 0, message = "排序必须大于等于0",groups = {AddGroup.class, UpdateGroup.class})
	private Integer sort;

	@TableLogic(value = "1",delval = "0")
	private Integer isExist;
}
