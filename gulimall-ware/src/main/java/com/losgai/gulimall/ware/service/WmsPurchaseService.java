package com.losgai.gulimall.ware.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.ware.dto.WmsPurchaseDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseEntity;
import com.losgai.gulimall.ware.vo.MergeVo;

import java.util.Map;

/**
 * 采购信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
public interface WmsPurchaseService extends CrudService<WmsPurchaseEntity, WmsPurchaseDTO> {

    PageData<WmsPurchaseEntity> pageQuery(Map<String, Object> params);

    PageData<WmsPurchaseEntity> pageNoAssign(Map<String, Object> params);

    void merge(MergeVo vo);
}