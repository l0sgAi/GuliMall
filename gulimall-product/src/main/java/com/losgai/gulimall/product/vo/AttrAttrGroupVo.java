package com.losgai.gulimall.product.vo;

import lombok.Data;

@Data
public class AttrAttrGroupVo {
    private Long id;
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性分组id
     */
    private Long attrGroupId;
    /**
     * 属性分组名称
     */
    private String attrGroupName;
    /**
     * 属性组内排序
     */
    private Integer attrSort;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 可选值列表数组
     */
    private String[] valueSelectArray;

}
