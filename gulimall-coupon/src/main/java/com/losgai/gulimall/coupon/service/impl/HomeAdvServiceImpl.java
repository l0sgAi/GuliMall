package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.HomeAdvDao;
import com.losgai.gulimall.coupon.dto.HomeAdvDTO;
import com.losgai.gulimall.coupon.entity.HomeAdvEntity;
import com.losgai.gulimall.coupon.service.HomeAdvService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class HomeAdvServiceImpl extends CrudServiceImpl<HomeAdvDao, HomeAdvEntity, HomeAdvDTO> implements HomeAdvService {

    @Override
    public QueryWrapper<HomeAdvEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<HomeAdvEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}