/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.log.service;


import com.losgai.gulimall.common.modules.log.entity.SysLogErrorEntity;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.BaseService;
import com.losgai.gulimall.common.modules.log.dto.SysLogErrorDTO;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}