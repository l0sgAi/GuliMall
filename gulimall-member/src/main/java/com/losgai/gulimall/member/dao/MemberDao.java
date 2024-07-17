package com.losgai.gulimall.member.dao;

import com.losgai.gulimall.common.dao.BaseDao;
import com.losgai.gulimall.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {
	
}