package com.losgai.gulimall.ware.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.ware.dto.WmsPurchaseDetailDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
public interface WmsPurchaseDetailService extends CrudService<WmsPurchaseDetailEntity, WmsPurchaseDetailDTO> {

    PageData<WmsPurchaseDetailEntity> pageQuery(Map<String, Object> params);

    List<WmsPurchaseDetailEntity> listDetailByPurchaseId(Long id);
}