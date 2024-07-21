package com.losgai.gulimall.order.dao;

import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.order.entity.OrderOperateHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseDao<OrderOperateHistoryEntity> {
	
}