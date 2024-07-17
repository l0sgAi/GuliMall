package com.losgai.gulimall.member.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员等级
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class MemberLevelExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "等级名称")
    private String name;
    @ExcelProperty(value = "等级需要的成长值")
    private Integer growthPoint;
    @ExcelProperty(value = "是否为默认等级[0->不是；1->是]")
    private Integer defaultStatus;
    @ExcelProperty(value = "免运费标准")
    private BigDecimal freeFreightPoint;
    @ExcelProperty(value = "每次评价获取的成长值")
    private Integer commentGrowthPoint;
    @ExcelProperty(value = "是否有免邮特权")
    private Integer priviledgeFreeFreight;
    @ExcelProperty(value = "是否有会员价格特权")
    private Integer priviledgeMemberPrice;
    @ExcelProperty(value = "是否有生日特权")
    private Integer priviledgeBirthday;
    @ExcelProperty(value = "备注")
    private String note;

}