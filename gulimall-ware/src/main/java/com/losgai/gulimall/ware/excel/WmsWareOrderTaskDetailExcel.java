package com.losgai.gulimall.ware.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 库存工作单
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class WmsWareOrderTaskDetailExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "sku_id")
    private Long skuId;
    @ExcelProperty(value = "sku_name")
    private String skuName;
    @ExcelProperty(value = "购买个数")
    private Integer skuNum;
    @ExcelProperty(value = "工作单id")
    private Long taskId;
    @ExcelProperty(value = "仓库id")
    private Long wareId;
    @ExcelProperty(value = "1-已锁定  2-已解锁  3-扣减")
    private Integer lockStatus;

}