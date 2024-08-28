package com.losgai.gulimall.ware.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.ware.dto.WmsWareInfoDTO;
import com.losgai.gulimall.ware.entity.WmsWareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
public interface WmsWareInfoService extends CrudService<WmsWareInfoEntity, WmsWareInfoDTO> {

    PageData<WmsWareInfoEntity> pageQuery(Map<String, Object> params,String key);
}