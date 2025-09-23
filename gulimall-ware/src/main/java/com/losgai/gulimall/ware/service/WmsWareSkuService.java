package com.losgai.gulimall.ware.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.ware.dto.WmsWareSkuDTO;
import com.losgai.gulimall.ware.entity.WmsWareSkuEntity;
import com.losgai.gulimall.ware.vo.SkuStockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
public interface WmsWareSkuService extends CrudService<WmsWareSkuEntity, WmsWareSkuDTO> {

    PageData<WmsWareSkuEntity> pageQuery(Map<String, Object> params,Long wareId,Long skuId);

    void addToStock(Long skuId, Long wareId, Integer stock);

    List<SkuStockVo> getSkuHasStock(List<Long> skuIds);
}