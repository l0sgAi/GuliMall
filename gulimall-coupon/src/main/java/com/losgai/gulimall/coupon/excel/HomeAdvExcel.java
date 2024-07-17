package com.losgai.gulimall.coupon.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 首页轮播广告
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class HomeAdvExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "名字")
    private String name;
    @ExcelProperty(value = "图片地址")
    private String pic;
    @ExcelProperty(value = "开始时间")
    private Date startTime;
    @ExcelProperty(value = "结束时间")
    private Date endTime;
    @ExcelProperty(value = "状态")
    private Integer status;
    @ExcelProperty(value = "点击数")
    private Integer clickCount;
    @ExcelProperty(value = "广告详情连接地址")
    private String url;
    @ExcelProperty(value = "备注")
    private String note;
    @ExcelProperty(value = "排序")
    private Integer sort;
    @ExcelProperty(value = "发布者")
    private Long publisherId;
    @ExcelProperty(value = "审核者")
    private Long authId;

}