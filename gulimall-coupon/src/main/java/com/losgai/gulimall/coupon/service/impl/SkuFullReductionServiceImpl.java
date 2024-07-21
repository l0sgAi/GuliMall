package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.SkuFullReductionDao;
import com.losgai.gulimall.coupon.dto.SkuFullReductionDTO;
import com.losgai.gulimall.coupon.entity.SkuFullReductionEntity;
import com.losgai.gulimall.coupon.service.SkuFullReductionService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class SkuFullReductionServiceImpl extends CrudServiceImpl<SkuFullReductionDao, SkuFullReductionEntity, SkuFullReductionDTO> implements SkuFullReductionService {

    @Override
    public QueryWrapper<SkuFullReductionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SkuFullReductionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}