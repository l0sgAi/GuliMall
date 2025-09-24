package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.constant.ProductConstant;
import com.losgai.gulimall.common.common.es.ESAttr;
import com.losgai.gulimall.common.common.es.SkuEsModel;
import com.losgai.gulimall.common.common.exception.BaseCodeEnume;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.product.dao.*;
import com.losgai.gulimall.product.dto.SkuReductionDTO;
import com.losgai.gulimall.product.dto.SpuBoundsTO;
import com.losgai.gulimall.product.dto.SpuInfoDTO;
import com.losgai.gulimall.product.entity.*;
import com.losgai.gulimall.product.feign.CouponFeignService;
import com.losgai.gulimall.product.feign.SearchFeignClient;
import com.losgai.gulimall.product.feign.WareFeignService;
import com.losgai.gulimall.product.service.*;
import com.losgai.gulimall.product.vo.SkuStockVo;
import com.losgai.gulimall.product.vo.SpuInfoVo;

import com.losgai.gulimall.product.vo.spus.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * spu信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
@RequiredArgsConstructor
public class SpuInfoServiceImpl extends CrudServiceImpl<SpuInfoDao, SpuInfoEntity, SpuInfoDTO> implements SpuInfoService {

    private final SpuInfoDao spuInfoDao;

    private final SpuInfoDescDao spuInfoDescDao;

    private final SpuImagesService spuImagesService;

    private final ProductAttrValueService productAttrValueService;

    private final AttrDao attrDao;

    private final SkuInfoDao skuInfoDao;

    private final BrandDao brandDao;

    private final CategoryDao categoryDao;

    private final SkuImagesService skuImagesService;

    private final SkuSaleAttrValueService skuSaleAttrValueService;

    private final CouponFeignService couponFeignService;

    private final WareFeignService wareFeignService;

    private final SearchFeignClient searchFeignClient;

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
        try {
            catalogId = Long.parseLong((String) params.get("catelogId"));
        } catch (NumberFormatException ignored) {
        }
        if (catalogId != 0 && ObjectUtil.isNotNull(catalogId)) {
            wrapper.eq("catalog_id", catalogId);
        }
        long brandId = 0L;
        try {
            brandId = Long.parseLong((String) params.get("brandId"));
        } catch (NumberFormatException ignored) {
        }
        if (brandId != 0 && ObjectUtil.isNotNull(brandId)) {
            wrapper.eq("brand_id", brandId);
        }

        Integer status = null;
        try {
            status = Integer.parseInt((String) params.get("status"));
        } catch (NumberFormatException ignored) {
        }
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

    /**
     * 商品上架
     */
    @Override
    public void spuUp(Long spuId) {
        // 需要上架的所有商品
        List<SkuEsModel> upProducts = new ArrayList<>();

        // 查询spu下的属性id列表
        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.getListBySpuId(spuId);
        // 过滤出对应的id列表
        List<Long> attrValueIds = baseAttrs
                .stream()
                .map(ProductAttrValueEntity::getAttrId)
                .toList();

        // 根据id列表获取属性信息，过滤出需要检索的属性值id
        List<Long> attrIds = attrDao.selectBatchIds(attrValueIds)
                .stream()
                .filter(i-> i.getSearchType() == 1) // 是否需要检索，0-不需要 1-需要
                .map(AttrEntity::getAttrId)
                .toList();

        // 将spu下的属性id列表根据attrIds进行过滤
        Set<Long> attrValueIdsSet = new HashSet<>(attrIds);
        List<ESAttr> baseESAttrValues = baseAttrs
                .stream()
                .map(i -> {
                    ESAttr ESAttr = new ESAttr();
                    ESAttr.setAttrId(i.getAttrId());
                    ESAttr.setAttrName(i.getAttrName());
                    ESAttr.setAttrValue(i.getAttrValue());
                    return ESAttr;
                })
                .filter(i -> attrValueIdsSet.contains(i.getAttrId()))
                .toList();

        // 查下传入的spuId对应的所有skuId信息 包括品牌名等
        List<SkuInfoEntity> skuInfoEntities = skuInfoDao
                .selectList(new QueryWrapper<SkuInfoEntity>()
                        .eq("spu_id", spuId));

        // 获取对应的skuId列表和库存信息映射
        List<Long> skuIds = skuInfoEntities
                .stream()
                .map(SkuInfoEntity::getSkuId)
                .toList();

        // TODO 后期改进？
        // 封装每个sku的信息
        // 提前查询品牌名称和分类名
        if (CollectionUtil.isNotEmpty(skuInfoEntities)) {
            Long brandId = skuInfoEntities.get(0).getBrandId();
            Long catalogId = skuInfoEntities.get(0).getCatalogId();

            // 只查一次品牌和分类
            BrandEntity brandEntity = brandDao.selectById(brandId);
            CategoryEntity categoryEntity = categoryDao.selectById(catalogId);

            // 获取库存信息映射Map
            Map<Long,Boolean> stockMap = null;
            try {
                List<SkuStockVo> data = wareFeignService.checkStock(skuIds).getData();
                // 转化为Map
                stockMap = data.stream()
                        .collect(Collectors
                                .toMap(SkuStockVo::getSkuId
                                        , SkuStockVo::getHasStock));
            } catch (Exception e) {
                log.error("库存服务查询异常:{}", e);
            }

            Map<Long, Boolean> finalStockMap = stockMap;
            List<SkuEsModel> collect = skuInfoEntities.stream().map(item -> {
                SkuEsModel esModel = new SkuEsModel();
                BeanUtils.copyProperties(item, esModel);
                // 设置价格和图片
                esModel.setSkuPrice(item.getPrice());
                esModel.setSkuImg(item.getSkuDefaultImg());
                // 设置hasStock、hotScore
                // 发送远程调用查询是否有库存（不能直接用item.getSaleCount）
                if(CollectionUtil.isNotEmpty(finalStockMap)){
                    esModel.setHasStock(finalStockMap.get(item.getSkuId()));
                }
                // TODO 热度评分，初始为0 后面再改
                esModel.setHotScore(0L);
                esModel.setBrandName(brandEntity.getName());
                esModel.setBrandImg(brandEntity.getLogo());
                esModel.setCatalogName(categoryEntity.getName());
                // 查询当前sku的所有可以被检索的属性，并赋值
                esModel.setESAttrs(baseESAttrValues);
                return esModel;
            }).toList();

            // 发送给ES保存
            Result result = searchFeignClient.productStatUp(collect);
            if(result.getCode() == 0){
                // 远程调用成功
                // 修改spu状态为已上架
                spuInfoDao.updateUpStatusById(spuId, ProductConstant.StatsEnum.UP_SPU.getCode());
            }else {
                // TODO: 远程调用失败解决方案？重试策略等。。。
                log.error("ES保存操作远程调用失败：{}");
            }
        }

    }
}