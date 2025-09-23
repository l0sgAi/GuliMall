package com.losgai.gulimall.ware.dao;

import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.ware.entity.WmsWareSkuEntity;
import com.losgai.gulimall.ware.vo.SkuStockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Mapper
public interface WmsWareSkuDao extends BaseDao<WmsWareSkuEntity> {
    // 自定义SQL语句，如果数据库内没有记录就插入，有就更新库存信息，过程中考虑加写锁
    void addToStock(@Param("skuId") Long skuId,@Param("wareId") Long wareId,@Param("stock") Integer stock);

    List<SkuStockVo> getSkuHasStock(List<Long> skuIds);
}