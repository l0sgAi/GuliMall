package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.SkuLadderDao;
import com.losgai.gulimall.coupon.dto.SkuLadderDTO;
import com.losgai.gulimall.coupon.entity.SkuLadderEntity;
import com.losgai.gulimall.coupon.service.SkuLadderService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class SkuLadderServiceImpl extends CrudServiceImpl<SkuLadderDao, SkuLadderEntity, SkuLadderDTO> implements SkuLadderService {

    @Override
    public QueryWrapper<SkuLadderEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SkuLadderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}