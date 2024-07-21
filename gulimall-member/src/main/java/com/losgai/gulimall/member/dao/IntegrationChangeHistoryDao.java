package com.losgai.gulimall.member.dao;

import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.member.entity.IntegrationChangeHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分变化历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface IntegrationChangeHistoryDao extends BaseDao<IntegrationChangeHistoryEntity> {
	
}