package com.losgai.gulimall.order.dao;

import com.losgai.gulimall.common.dao.BaseDao;
import com.losgai.gulimall.order.entity.OrderSettingEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface OrderSettingDao extends BaseDao<OrderSettingEntity> {
	
}