/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.log.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.BaseService;
import com.losgai.gulimall.common.modules.log.dto.SysLogOperationDTO;
import com.losgai.gulimall.common.modules.log.entity.SysLogOperationEntity;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogOperationService extends BaseService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(SysLogOperationEntity entity);
}