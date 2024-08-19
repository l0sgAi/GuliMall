package com.losgai.gulimall.product.vo;

import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import com.losgai.gulimall.product.entity.AttrEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class AttrGroupWithAttrVo {

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

    private List<AttrEntity> attrs; // 当前分组下的所有规格属性

}
