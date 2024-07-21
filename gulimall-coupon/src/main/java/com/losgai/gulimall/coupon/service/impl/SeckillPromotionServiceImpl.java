package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.SeckillPromotionDao;
import com.losgai.gulimall.coupon.dto.SeckillPromotionDTO;
import com.losgai.gulimall.coupon.entity.SeckillPromotionEntity;
import com.losgai.gulimall.coupon.service.SeckillPromotionService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class SeckillPromotionServiceImpl extends CrudServiceImpl<SeckillPromotionDao, SeckillPromotionEntity, SeckillPromotionDTO> implements SeckillPromotionService {

    @Override
    public QueryWrapper<SeckillPromotionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SeckillPromotionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}