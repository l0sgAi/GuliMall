package com.losgai.gulimall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.order.dao.OrderReturnReasonDao;
import com.losgai.gulimall.order.dto.OrderReturnReasonDTO;
import com.losgai.gulimall.order.entity.OrderReturnReasonEntity;
import com.losgai.gulimall.order.service.OrderReturnReasonService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 退货原因
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class OrderReturnReasonServiceImpl extends CrudServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity, OrderReturnReasonDTO> implements OrderReturnReasonService {

    @Override
    public QueryWrapper<OrderReturnReasonEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<OrderReturnReasonEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}