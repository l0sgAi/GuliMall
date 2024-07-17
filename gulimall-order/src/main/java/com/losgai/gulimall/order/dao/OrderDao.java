package com.losgai.gulimall.order.dao;

import com.losgai.gulimall.common.dao.BaseDao;
import com.losgai.gulimall.order.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {
	
}