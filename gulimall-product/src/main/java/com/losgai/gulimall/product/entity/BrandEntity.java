package com.losgai.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.losgai.gulimall.common.common.validator.annotation.ListValue;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 * 品牌
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@TableName("pms_brand")
public class BrandEntity {

    /**
     * 品牌id
     */
    @NotNull(message = "修改时品牌id不能为空",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotEmpty(message = "品牌logo地址不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @URL(message = "logo必须是一个合法的url地址",groups = {AddGroup.class, UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListValue(vals = {0,1},groups = {AddGroup.class, UpdateGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotEmpty(message = "检索首字母不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母",groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0, message = "排序必须大于等于0",groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

    @TableLogic(value = "1", delval = "0")
    private Integer isExist;
}