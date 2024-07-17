package com.losgai.gulimall.order.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class OrderReturnApplyExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "order_id")
    private Long orderId;
    @ExcelProperty(value = "退货商品id")
    private Long skuId;
    @ExcelProperty(value = "订单编号")
    private String orderSn;
    @ExcelProperty(value = "申请时间")
    private Date createTime;
    @ExcelProperty(value = "会员用户名")
    private String memberUsername;
    @ExcelProperty(value = "退款金额")
    private BigDecimal returnAmount;
    @ExcelProperty(value = "退货人姓名")
    private String returnName;
    @ExcelProperty(value = "退货人电话")
    private String returnPhone;
    @ExcelProperty(value = "申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]")
    private Integer status;
    @ExcelProperty(value = "处理时间")
    private Date handleTime;
    @ExcelProperty(value = "商品图片")
    private String skuImg;
    @ExcelProperty(value = "商品名称")
    private String skuName;
    @ExcelProperty(value = "商品品牌")
    private String skuBrand;
    @ExcelProperty(value = "商品销售属性(JSON)")
    private String skuAttrsVals;
    @ExcelProperty(value = "退货数量")
    private Integer skuCount;
    @ExcelProperty(value = "商品单价")
    private BigDecimal skuPrice;
    @ExcelProperty(value = "商品实际支付单价")
    private BigDecimal skuRealPrice;
    @ExcelProperty(value = "原因")
    private String reason;
    @ExcelProperty(value = "描述")
    private String description述;
    @ExcelProperty(value = "凭证图片，以逗号隔开")
    private String descPics;
    @ExcelProperty(value = "处理备注")
    private String handleNote;
    @ExcelProperty(value = "处理人员")
    private String handleMan;
    @ExcelProperty(value = "收货人")
    private String receiveMan;
    @ExcelProperty(value = "收货时间")
    private Date receiveTime;
    @ExcelProperty(value = "收货备注")
    private String receiveNote;
    @ExcelProperty(value = "收货电话")
    private String receivePhone;
    @ExcelProperty(value = "公司收货地址")
    private String companyAddress;

}