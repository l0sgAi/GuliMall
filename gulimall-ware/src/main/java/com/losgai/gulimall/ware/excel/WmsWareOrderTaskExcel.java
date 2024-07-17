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
public class WmsWareOrderTaskExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "order_id")
    private Long orderId;
    @ExcelProperty(value = "order_sn")
    private String orderSn;
    @ExcelProperty(value = "收货人")
    private String consignee;
    @ExcelProperty(value = "收货人电话")
    private String consigneeTel;
    @ExcelProperty(value = "配送地址")
    private String deliveryAddress;
    @ExcelProperty(value = "订单备注")
    private String orderComment;
    @ExcelProperty(value = "付款方式【 1:在线付款 2:货到付款】")
    private Integer paymentWay;
    @ExcelProperty(value = "任务状态")
    private Integer taskStatus;
    @ExcelProperty(value = "订单描述")
    private String orderBody;
    @ExcelProperty(value = "物流单号")
    private String trackingNo;
    @ExcelProperty(value = "create_time")
    private Date createTime;
    @ExcelProperty(value = "仓库id")
    private Long wareId;
    @ExcelProperty(value = "工作单备注")
    private String taskComment;

}