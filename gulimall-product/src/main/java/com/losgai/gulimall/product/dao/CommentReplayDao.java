package com.losgai.gulimall.product.dao;

import com.losgai.gulimall.common.dao.BaseDao;
import com.losgai.gulimall.product.entity.CommentReplayEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Mapper
public interface CommentReplayDao extends BaseDao<CommentReplayEntity> {
	
}