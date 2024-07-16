package com.losgai.gulimall.product.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * spu图片
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
public class SpuImagesExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "spu_id")
    private Long spuId;
    @ExcelProperty(value = "图片名")
    private String imgName;
    @ExcelProperty(value = "图片地址")
    private String imgUrl;
    @ExcelProperty(value = "顺序")
    private Integer imgSort;
    @ExcelProperty(value = "是否默认图")
    private Integer defaultImg;

}