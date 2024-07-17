package com.losgai.gulimall.member.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 会员收藏的商品
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class MemberCollectSpuExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "会员id")
    private Long memberId;
    @ExcelProperty(value = "spu_id")
    private Long spuId;
    @ExcelProperty(value = "spu_name")
    private String spuName;
    @ExcelProperty(value = "spu_img")
    private String spuImg;
    @ExcelProperty(value = "create_time")
    private Date createTime;

}