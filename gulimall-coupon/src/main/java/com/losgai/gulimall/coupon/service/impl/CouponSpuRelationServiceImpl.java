package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.CouponSpuRelationDao;
import com.losgai.gulimall.coupon.dto.CouponSpuRelationDTO;
import com.losgai.gulimall.coupon.entity.CouponSpuRelationEntity;
import com.losgai.gulimall.coupon.service.CouponSpuRelationService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class CouponSpuRelationServiceImpl extends CrudServiceImpl<CouponSpuRelationDao, CouponSpuRelationEntity, CouponSpuRelationDTO> implements CouponSpuRelationService {

    @Override
    public QueryWrapper<CouponSpuRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CouponSpuRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}