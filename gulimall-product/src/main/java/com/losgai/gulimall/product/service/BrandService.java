package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.BrandDTO;
import com.losgai.gulimall.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface BrandService extends CrudService<BrandEntity, BrandDTO> {
    PageData<BrandEntity> queryPage(Map<String, Object> params, String key);

    void doBatchUpdate(BrandEntity dto);

    List<BrandEntity> getCategoryBrand(Long catId);

//    void removeBrands(Long[] ids);
}