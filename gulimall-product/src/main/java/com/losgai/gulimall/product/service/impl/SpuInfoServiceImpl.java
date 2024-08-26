package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.product.dao.*;
import com.losgai.gulimall.product.dto.SkuReductionDTO;
import com.losgai.gulimall.product.dto.SpuBoundsTO;
import com.losgai.gulimall.product.dto.SpuInfoDTO;
import com.losgai.gulimall.product.entity.*;
import com.losgai.gulimall.product.feign.CouponFeignService;
import com.losgai.gulimall.product.service.*;
import com.losgai.gulimall.product.vo.SpuInfoVo;
import com.losgai.gulimall.product.vo.spus.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * spu信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class SpuInfoServiceImpl extends CrudServiceImpl<SpuInfoDao, SpuInfoEntity, SpuInfoDTO> implements SpuInfoService {

    @Autowired
    private SpuInfoDao spuInfoDao;

    @Autowired
    private SpuInfoDescDao spuInfoDescDao;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Override
    public QueryWrapper<SpuInfoEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    //TODO: 异常处理、稳定性、性能优化...
    @Override
    @Transactional
    public void saveSpuVo(SpuSaveVo vo) {
        //1. 保存基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        spuInfoDao.insert(spuInfoEntity);
        Long spuId = spuInfoEntity.getId();

        //2. 保存描述图片 pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(spuId);
        descEntity.setDecript(String.join(";", decript));
        spuInfoDescDao.insert(descEntity);

        //3. 保存spu图片集 pms_spu_images
        List<String> images = vo.getImages();
        if (!images.isEmpty()) {
            List<SpuImagesEntity> entities = images.stream().map(item -> {
                SpuImagesEntity imagesEntity = new SpuImagesEntity();
                imagesEntity.setSpuId(spuId);
                imagesEntity.setImgUrl(item);
                imagesEntity.setImgSort(0);
                imagesEntity.setDefaultImg(0);
                return imagesEntity;
            }).collect(Collectors.toList());
            spuImagesService.insertBatch(entities);
        }

        //4. 保存规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        if (!baseAttrs.isEmpty()) {
            List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(item -> {
                ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
                attrValueEntity.setAttrId(item.getAttrId());
                attrValueEntity.setAttrName("");
                attrValueEntity.setAttrValue(item.getValues());
                attrValueEntity.setQuickShow(item.getShowDesc());
                attrValueEntity.setSpuId(spuId);
                return attrValueEntity;
            }).collect(Collectors.toList());
            productAttrValueService.insertBatch(productAttrValueEntities);
        }
        // 5. 保存spu的积分信息 gulimall_sms->sms_spu_bounds
        SpuBoundsTO spuBoundsTO = new SpuBoundsTO();
        Bounds bounds = vo.getBounds();
        BeanUtils.copyProperties(bounds, spuBoundsTO);
        spuBoundsTO.setSpuId(spuId);
        couponFeignService.save(spuBoundsTO);


        //6. 保存spu对应的所有sku信息
        List<Skus> skus = vo.getSkus();
        if (!skus.isEmpty()) {
            skus.forEach(item -> {
                String defaultImg = "";
                for (Images img : item.getImages()) {
                    if (img.getDefaultImg() == 1) {
                        defaultImg = img.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSpuId(spuId);
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //6.1 sku基本信息;pms_sku_info
                skuInfoDao.insert(skuInfoEntity);
                Long skuId = skuInfoEntity.getSkuId();

                List<SkuImagesEntity> skuImagesEntities = item.getImages().stream().map(
                                img -> {
                                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                                    skuImagesEntity.setSkuId(skuId);
                                    skuImagesEntity.setImgUrl(img.getImgUrl());
                                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                                    return skuImagesEntity;
                                }
                        )
                        .filter(entity -> StrUtil.isNotBlank(entity.getImgUrl()))
                        .collect(Collectors.toList());
                //6.2 sku图片信息;pms_sku_images;没有图片信息的无需保存

                skuImagesService.insertBatch(skuImagesEntities);

                //6.3 sku销售属性信息;pms_sku_sale_attr_value
                List<Attr> attrList = item.getAttr();
                List<SkuSaleAttrValueEntity> attrValueEntities = attrList.stream().map(attr -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(attr, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.insertBatch(attrValueEntities);

                //6.4 sku优惠信息;gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
                SkuReductionDTO skuReductionTO = new SkuReductionDTO();
                BeanUtils.copyProperties(item, skuReductionTO);
                skuReductionTO.setSkuId(skuId);
                if (skuReductionTO.getFullCount() > 0 || skuReductionTO.getFullPrice().compareTo(new BigDecimal("0")) > 0) {
                    Result result = couponFeignService.saveReduction(skuReductionTO);
                    if (result.getCode() != 0) {
                        log.error("远程保存sku优惠信息失败");
                    }
                }
            });
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageData<SpuInfoVo> pageWithCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        wrapper.like(StrUtil.isNotBlank(key), "spu_name", key);

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

        Integer status = null;
        try {status = Integer.parseInt((String) params.get("status"));}
        catch (NumberFormatException ignored) {}
        if (ObjectUtil.isNotNull(status)) {
            wrapper.eq("publish_status", status);
        }

        List<SpuInfoEntity> list = spuInfoDao.selectList(wrapper);

        List<SpuInfoVo> voList = list.stream()
                .map(item -> {
                    SpuInfoVo vo = new SpuInfoVo();
                    BeanUtils.copyProperties(item, vo);
                    vo.setCatalogName(categoryDao.selectById(item.getCatalogId()).getName());
                    vo.setBrandName(brandDao.selectById(item.getBrandId()).getName());
                    return vo;
                })
                .collect(Collectors.toList());

        return new PageData<>(voList, voList.size());
    }
}