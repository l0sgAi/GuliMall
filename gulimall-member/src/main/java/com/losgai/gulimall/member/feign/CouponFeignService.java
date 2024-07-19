package com.losgai.gulimall.member.feign;

import com.losgai.gulimall.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    /*@RequestMapping("coupon/coupon/member/list")
    public Result memberCoupon();*/
}
