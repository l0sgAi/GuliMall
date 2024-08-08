package com.losgai.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

/**
 * 属性分组
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity {

    /**
     * 分组id
     */
	@TableId
    @NotNull(message = "修改时分组id不能为空",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long attrGroupId;
    /**
     * 组名
     */
    @NotBlank(message = "组名不能为空",groups = {AddGroup.class})
	private String attrGroupName;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空",groups = {AddGroup.class})
    @Min(value = 0, message = "排序序号必须大于等于0",groups = {AddGroup.class, UpdateGroup.class})
	private Integer sort;
    /**
     * 描述
     */
	private String descript;
    /**
     * 组图标
     */
    @URL(message = "图标必须是一个合法的url地址",groups = {AddGroup.class, UpdateGroup.class})
	private String icon;
    /**
     * 所属分类id
     */
    @NotNull(message = "所属分类不能为空",groups = {AddGroup.class})
    @Min(value = 1, message = "分类id必须大于0",groups = {AddGroup.class, UpdateGroup.class})
	private Long catelogId;

    @TableLogic(value = "1",delval = "0")
    private Integer isShow;
}