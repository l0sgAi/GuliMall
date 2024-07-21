/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.oss.dao;

import com.losgai.gulimall.common.modules.oss.entity.SysOssEntity;
import com.losgai.gulimall.common.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
	
}
