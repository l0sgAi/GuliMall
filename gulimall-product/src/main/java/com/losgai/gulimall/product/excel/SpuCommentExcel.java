package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 商品评价
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class SpuCommentExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "sku_id")
    private Long skuId;
    @ExcelProperty(value = "spu_id")
    private Long spuId;
    @ExcelProperty(value = "商品名字")
    private String spuName;
    @ExcelProperty(value = "会员昵称")
    private String memberNickName;
    @ExcelProperty(value = "星级")
    private Integer star;
    @ExcelProperty(value = "会员ip")
    private String memberIp;
    @ExcelProperty(value = "创建时间")
    private Date createTime;
    @ExcelProperty(value = "显示状态[0-不显示，1-显示]")
    private Integer showStatus;
    @ExcelProperty(value = "购买时属性组合")
    private String spuAttributes;
    @ExcelProperty(value = "点赞数")
    private Integer likesCount;
    @ExcelProperty(value = "回复数")
    private Integer replyCount;
    @ExcelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    private String resources;
    @ExcelProperty(value = "内容")
    private String content;
    @ExcelProperty(value = "用户头像")
    private String memberIcon;
    @ExcelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    private Integer commentType;

}