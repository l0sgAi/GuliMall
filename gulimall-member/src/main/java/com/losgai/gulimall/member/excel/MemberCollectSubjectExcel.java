package com.losgai.gulimall.member.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 会员收藏的专题活动
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class MemberCollectSubjectExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "subject_id")
    private Long subjectId;
    @ExcelProperty(value = "subject_name")
    private String subjectName;
    @ExcelProperty(value = "subject_img")
    private String subjectImg;
    @ExcelProperty(value = "活动url")
    private String subjectUrll;

}