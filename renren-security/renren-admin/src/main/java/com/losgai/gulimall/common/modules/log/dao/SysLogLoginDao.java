/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.log.dao;

import com.losgai.gulimall.common.modules.log.entity.SysLogLoginEntity;
import com.losgai.gulimall.common.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {
	
}
