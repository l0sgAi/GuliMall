package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsPurchaseDao;
import com.losgai.gulimall.ware.dto.WmsPurchaseDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseEntity;
import com.losgai.gulimall.ware.service.WmsPurchaseService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 采购信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsPurchaseServiceImpl extends CrudServiceImpl<WmsPurchaseDao, WmsPurchaseEntity, WmsPurchaseDTO> implements WmsPurchaseService {

    @Override
    public QueryWrapper<WmsPurchaseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsPurchaseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}