/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.sys.service;

import com.losgai.gulimall.common.modules.sys.entity.SysDictDataEntity;
import com.losgai.gulimall.common.page.PageData;
import com.losgai.gulimall.common.service.BaseService;
import com.losgai.gulimall.common.modules.sys.dto.SysDictDataDTO;

import java.util.Map;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(Map<String, Object> params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}