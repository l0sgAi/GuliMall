package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsWareSkuDao;
import com.losgai.gulimall.ware.dto.WmsWareSkuDTO;
import com.losgai.gulimall.ware.entity.WmsWareSkuEntity;
import com.losgai.gulimall.ware.service.WmsWareSkuService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsWareSkuServiceImpl extends CrudServiceImpl<WmsWareSkuDao, WmsWareSkuEntity, WmsWareSkuDTO> implements WmsWareSkuService {

    @Override
    public QueryWrapper<WmsWareSkuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsWareSkuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}