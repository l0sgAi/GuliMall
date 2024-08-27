package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.SkuInfoDTO;
import com.losgai.gulimall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface SkuInfoService extends CrudService<SkuInfoEntity, SkuInfoDTO> {

    PageData<SkuInfoEntity> pageCondition(Map<String, Object> params);
}