package com.losgai.gulimall.member.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 会员
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class MemberExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "会员等级id")
    private Long levelId;
    @ExcelProperty(value = "用户名")
    private String username;
    @ExcelProperty(value = "密码")
    private String password;
    @ExcelProperty(value = "昵称")
    private String nickname;
    @ExcelProperty(value = "手机号码")
    private String mobile;
    @ExcelProperty(value = "邮箱")
    private String email;
    @ExcelProperty(value = "头像")
    private String header;
    @ExcelProperty(value = "性别")
    private Integer gender;
    @ExcelProperty(value = "生日")
    private Date birth;
    @ExcelProperty(value = "所在城市")
    private String city;
    @ExcelProperty(value = "职业")
    private String job;
    @ExcelProperty(value = "个性签名")
    private String sign;
    @ExcelProperty(value = "用户来源")
    private Integer sourceType;
    @ExcelProperty(value = "积分")
    private Integer integration;
    @ExcelProperty(value = "成长值")
    private Integer growth;
    @ExcelProperty(value = "启用状态")
    private Integer status;
    @ExcelProperty(value = "注册时间")
    private Date createTime;

}