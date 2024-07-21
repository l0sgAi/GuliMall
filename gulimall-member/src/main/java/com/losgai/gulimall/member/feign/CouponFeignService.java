package com.losgai.gulimall.member.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    /*@RequestMapping("coupon/coupon/member/list")
    public Result memberCoupon();*/
}
