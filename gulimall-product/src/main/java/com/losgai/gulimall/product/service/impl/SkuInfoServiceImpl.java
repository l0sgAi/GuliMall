package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.SkuInfoDao;
import com.losgai.gulimall.product.dto.SkuInfoDTO;
import com.losgai.gulimall.product.entity.SkuInfoEntity;
import com.losgai.gulimall.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class SkuInfoServiceImpl extends CrudServiceImpl<SkuInfoDao, SkuInfoEntity, SkuInfoDTO> implements SkuInfoService {

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Override
    public QueryWrapper<SkuInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    @Transactional(readOnly = true)
    public PageData<SkuInfoEntity> pageCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        wrapper.like(StrUtil.isNotBlank(key), "sku_name", key);

        long catalogId = 0L;
        try {catalogId = Long.parseLong((String) params.get("catelogId"));}
        catch (NumberFormatException ignored){}
        if (catalogId != 0 && ObjectUtil.isNotNull(catalogId)) {
            wrapper.eq("catalog_id", catalogId);
        }
        long brandId=0L;
        try{brandId = Long.parseLong((String) params.get("brandId"));}
        catch (NumberFormatException ignored){}
        if (brandId != 0 && ObjectUtil.isNotNull(brandId)) {
            wrapper.eq("brand_id", brandId);
        }

        int max = 0;
        int min = 0;
        try {
            max = Integer.parseInt((String) params.get("max"));
            min = Integer.parseInt((String) params.get("min"));
        }
        catch (NumberFormatException ignored) {}
        if (ObjectUtil.isNotNull(max) && ObjectUtil.isNotNull(min) && max > min) {
            wrapper.ge("price", min);
            wrapper.le("price", max);
        }

        List<SkuInfoEntity> list = skuInfoDao.selectList(wrapper);

        return new PageData<>(list, list.size());
    }
}