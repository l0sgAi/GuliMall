package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.AttrDTO;
import com.losgai.gulimall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface AttrService extends CrudService<AttrEntity, AttrDTO> {

    PageData<AttrEntity> queryPageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key);

    PageData<AttrEntity> queryPageByCatId(Map<String, Object> params, long categoryId);
}