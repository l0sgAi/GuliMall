package com.losgai.gulimall.product.feign;

import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.product.dto.SkuReductionDTO;
import com.losgai.gulimall.product.vo.SkuStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-ware")
public interface WareFeignService {

    @PostMapping("ware/wmswaresku/hasStock")
    Result<List<SkuStockVo>> checkStock(@RequestBody List<Long> skuIds);

}
