package com.losgai.gulimall.product.feign;

import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.product.dto.SkuReductionDTO;
import com.losgai.gulimall.product.dto.SpuBoundsTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    /*@RequestMapping("coupon/coupon/member/list")
    public Result memberCoupon();*/
    @PostMapping("coupon/spubounds")
    Result save(@RequestBody SpuBoundsTO spuBoundsTO);

    @PostMapping("coupon/skufullreduction/saveReduction")
    Result saveReduction(@RequestBody SkuReductionDTO skuReductionDTO);
}
