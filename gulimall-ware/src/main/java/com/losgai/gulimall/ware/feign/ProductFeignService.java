package com.losgai.gulimall.ware.feign;

import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.ware.dto.SkuInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gulimall-product")
public interface ProductFeignService {
    @GetMapping("product/skuinfo/{id}")
    Result<SkuInfoDTO> get(@PathVariable("id") Long id);

}
