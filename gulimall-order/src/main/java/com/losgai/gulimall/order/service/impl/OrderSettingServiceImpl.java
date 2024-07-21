package com.losgai.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.order.dao.OrderSettingDao;
import com.losgai.gulimall.order.dto.OrderSettingDTO;
import com.losgai.gulimall.order.entity.OrderSettingEntity;
import com.losgai.gulimall.order.service.OrderSettingService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class OrderSettingServiceImpl extends CrudServiceImpl<OrderSettingDao, OrderSettingEntity, OrderSettingDTO> implements OrderSettingService {

    @Override
    public QueryWrapper<OrderSettingEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderSettingEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}