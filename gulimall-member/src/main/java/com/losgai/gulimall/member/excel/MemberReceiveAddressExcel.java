package com.losgai.gulimall.member.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 会员收货地址
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class MemberReceiveAddressExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "member_id")
    private Long memberId;
    @ExcelProperty(value = "收货人姓名")
    private String name;
    @ExcelProperty(value = "电话")
    private String phone;
    @ExcelProperty(value = "邮政编码")
    private String postCode;
    @ExcelProperty(value = "省份/直辖市")
    private String province;
    @ExcelProperty(value = "城市")
    private String city;
    @ExcelProperty(value = "区")
    private String region;
    @ExcelProperty(value = "详细地址(街道)")
    private String detailAddress;
    @ExcelProperty(value = "省市区代码")
    private String areacode;
    @ExcelProperty(value = "是否默认")
    private Integer defaultStatus;

}