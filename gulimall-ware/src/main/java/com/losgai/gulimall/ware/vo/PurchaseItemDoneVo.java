package com.losgai.gulimall.ware.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseItemDoneVo {
    @NotNull
    private Long itemId; // 物品id

    @NotNull
    private Integer status; // 物品状态

    private String reason; // 订单完成描述
}
