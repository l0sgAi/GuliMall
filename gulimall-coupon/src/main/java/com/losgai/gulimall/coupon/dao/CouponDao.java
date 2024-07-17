package com.losgai.gulimall.coupon.dao;

import com.losgai.gulimall.common.dao.BaseDao;
import com.losgai.gulimall.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface CouponDao extends BaseDao<CouponEntity> {
	
}