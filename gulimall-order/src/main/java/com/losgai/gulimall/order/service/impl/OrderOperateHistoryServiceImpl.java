package com.losgai.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.order.dao.OrderOperateHistoryDao;
import com.losgai.gulimall.order.dto.OrderOperateHistoryDTO;
import com.losgai.gulimall.order.entity.OrderOperateHistoryEntity;
import com.losgai.gulimall.order.service.OrderOperateHistoryService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class OrderOperateHistoryServiceImpl extends CrudServiceImpl<OrderOperateHistoryDao, OrderOperateHistoryEntity, OrderOperateHistoryDTO> implements OrderOperateHistoryService {

    @Override
    public QueryWrapper<OrderOperateHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderOperateHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}