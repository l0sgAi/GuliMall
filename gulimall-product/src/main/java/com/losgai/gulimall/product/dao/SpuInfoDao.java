package com.losgai.gulimall.product.dao;

import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.product.entity.SpuInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Mapper
public interface SpuInfoDao extends BaseDao<SpuInfoEntity> {

    void updateUpStatusById(Long spuId, int code);
}