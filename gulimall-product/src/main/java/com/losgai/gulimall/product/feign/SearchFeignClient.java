package com.losgai.gulimall.product.feign;

import com.losgai.gulimall.common.common.es.SkuEsModel;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.product.vo.SkuStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-search")
public interface SearchFeignClient {

    @PostMapping("/search/save/product")
    Result productStatUp(@RequestBody List<SkuEsModel> skuEsModels);

}
