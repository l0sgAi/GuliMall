package com.losgai.gulimall.member.dao;

import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.member.entity.GrowthChangeHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成长值变化历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface GrowthChangeHistoryDao extends BaseDao<GrowthChangeHistoryEntity> {
	
}