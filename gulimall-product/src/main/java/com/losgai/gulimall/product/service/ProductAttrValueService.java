package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.ProductAttrValueDTO;
import com.losgai.gulimall.product.entity.ProductAttrValueEntity;

import java.util.List;

/**
 * spu属性值
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface ProductAttrValueService extends CrudService<ProductAttrValueEntity, ProductAttrValueDTO> {

    List<ProductAttrValueEntity> getListBySpuId(Long id);

    void updateAttrValueBySpuId(List<ProductAttrValueEntity> values, Long spuId);
}