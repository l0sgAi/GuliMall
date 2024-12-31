package com.losgai.gulimall.ware.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseDoneVO {

    @NotNull
    private Long id; // 采购单id

    private List<PurchaseItemDoneVo> items; // 采购项列表

}
