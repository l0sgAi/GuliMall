package com.losgai.gulimall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.losgai.gulimall.common.common.validator.annotation.ListValue;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * 商品属性
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class AttrVo {

    /**
     * 属性id
     */
    @NotNull(message = "修改时id不能为空",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long attrId;
    /**
     * 属性名
     */
    @NotBlank(message = "属性名不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
	private Integer searchType;
    /**
     * 属性图标
     */
	private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
	private String valueSelect;
    /**
     * 可选值列表数组
     */
    private String[] valueSelectArray;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @ListValue(vals = {0,1,2},groups = {AddGroup.class, UpdateGroup.class})
	private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @ListValue(vals = {0,1},groups = {AddGroup.class, UpdateGroup.class})
	private Long enable;
    /**
     * 所属分类
     */
    @NotNull(message = "所属分类不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Long catelogId;
    /**
     * 所属分类对应的分组
     */
    private Long groupName;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
	private Integer showDesc;
}