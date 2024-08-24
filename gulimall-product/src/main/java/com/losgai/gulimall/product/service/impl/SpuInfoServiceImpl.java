package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.SkuInfoDao;
import com.losgai.gulimall.product.dao.SpuInfoDao;
import com.losgai.gulimall.product.dao.SpuInfoDescDao;
import com.losgai.gulimall.product.dto.SpuInfoDTO;
import com.losgai.gulimall.product.entity.*;
import com.losgai.gulimall.product.service.*;
import com.losgai.gulimall.product.vo.spus.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Override
    public QueryWrapper<SpuInfoEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


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

        //5. 保存spu对应的所有sku信息
        List<Skus> skus = vo.getSkus();
        if (!skus.isEmpty()) {
            skus.forEach(item -> {
                String defaultImg = "";
                for(Images img : item.getImages()){
                    if(img.getDefaultImg() == 1){
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
                //5.1 sku基本信息;pms_sku_info
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
                ).collect(Collectors.toList());
                //5.2 sku图片信息;pms_sku_images
                skuImagesService.insertBatch(skuImagesEntities);

                //5.3 sku销售属性信息;pms_sku_sale_attr_value
                List<Attr> attrList = item.getAttr();
                List<SkuSaleAttrValueEntity> attrValueEntities = attrList.stream().map(attr -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(attr, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.insertBatch(attrValueEntities);

                //TODO: 5.4 sku优惠信息;gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
            });
        }

        //TODO: 6. 保存spu的积分信息 gulimall_sms->sms_spu_bounds
    }
}