package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.SpuInfoDTO;
import com.losgai.gulimall.product.entity.SpuInfoEntity;
import com.losgai.gulimall.product.vo.SpuInfoVo;
import com.losgai.gulimall.product.vo.spus.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface SpuInfoService extends CrudService<SpuInfoEntity, SpuInfoDTO> {

    void saveSpuVo(SpuSaveVo vo);

    PageData<SpuInfoVo> pageWithCondition(Map<String, Object> params);

    /** 商品上架*/
    void spuUp(Long spuId);
}