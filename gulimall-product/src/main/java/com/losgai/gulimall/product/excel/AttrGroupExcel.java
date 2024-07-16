package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 属性分组
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class AttrGroupExcel {
    @ExcelProperty(value = "分组id")
    private Long attrGroupId;
    @ExcelProperty(value = "组名")
    private String attrGroupName;
    @ExcelProperty(value = "排序")
    private Integer sort;
    @ExcelProperty(value = "描述")
    private String descript;
    @ExcelProperty(value = "组图标")
    private String icon;
    @ExcelProperty(value = "所属分类id")
    private Long catelogId;

}