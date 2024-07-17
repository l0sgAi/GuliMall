package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsPurchaseDetailDao;
import com.losgai.gulimall.ware.dto.WmsPurchaseDetailDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseDetailEntity;
import com.losgai.gulimall.ware.service.WmsPurchaseDetailService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsPurchaseDetailServiceImpl extends CrudServiceImpl<WmsPurchaseDetailDao, WmsPurchaseDetailEntity, WmsPurchaseDetailDTO> implements WmsPurchaseDetailService {

    @Override
    public QueryWrapper<WmsPurchaseDetailEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsPurchaseDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}