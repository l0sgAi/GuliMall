/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.job.service;

import com.losgai.gulimall.common.modules.job.dto.ScheduleJobLogDTO;
import com.losgai.gulimall.common.modules.job.entity.ScheduleJobLogEntity;
import com.losgai.gulimall.common.page.PageData;
import com.losgai.gulimall.common.service.BaseService;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

	PageData<ScheduleJobLogDTO> page(Map<String, Object> params);

	ScheduleJobLogDTO get(Long id);
}
