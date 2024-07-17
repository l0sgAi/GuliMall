package com.losgai.gulimall.order.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单项信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
public class OrderItemExcel {
    @ExcelProperty(value = "id")
    private Long id;
    @ExcelProperty(value = "order_id")
    private Long orderId;
    @ExcelProperty(value = "order_sn")
    private String orderSn;
    @ExcelProperty(value = "spu_id")
    private Long spuId;
    @ExcelProperty(value = "spu_name")
    private String spuName;
    @ExcelProperty(value = "spu_pic")
    private String spuPic;
    @ExcelProperty(value = "品牌")
    private String spuBrand;
    @ExcelProperty(value = "商品分类id")
    private Long categoryId;
    @ExcelProperty(value = "商品sku编号")
    private Long skuId;
    @ExcelProperty(value = "商品sku名字")
    private String skuName;
    @ExcelProperty(value = "商品sku图片")
    private String skuPic;
    @ExcelProperty(value = "商品sku价格")
    private BigDecimal skuPrice;
    @ExcelProperty(value = "商品购买的数量")
    private Integer skuQuantity;
    @ExcelProperty(value = "商品销售属性组合（JSON）")
    private String skuAttrsVals;
    @ExcelProperty(value = "商品促销分解金额")
    private BigDecimal promotionAmount;
    @ExcelProperty(value = "优惠券优惠分解金额")
    private BigDecimal couponAmount;
    @ExcelProperty(value = "积分优惠分解金额")
    private BigDecimal integrationAmount;
    @ExcelProperty(value = "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;
    @ExcelProperty(value = "赠送积分")
    private Integer giftIntegration;
    @ExcelProperty(value = "赠送成长值")
    private Integer giftGrowth;

}