package com.losgai.gulimall.ware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsPurchaseDetailDao;
import com.losgai.gulimall.ware.dto.WmsPurchaseDetailDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseDetailEntity;
import com.losgai.gulimall.ware.service.WmsPurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsPurchaseDetailServiceImpl extends CrudServiceImpl<WmsPurchaseDetailDao, WmsPurchaseDetailEntity, WmsPurchaseDetailDTO> implements WmsPurchaseDetailService {

    @Autowired
    private WmsPurchaseDetailDao baseDao;

    @Override
    public QueryWrapper<WmsPurchaseDetailEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<WmsPurchaseDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<WmsPurchaseDetailEntity> pageQuery(Map<String, Object> params) {
        List<WmsPurchaseDetailEntity> list;
        QueryWrapper<WmsPurchaseDetailEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (StrUtil.isNotBlank(key)) {
            wrapper.and(w -> {
                w.eq("purchase_id", key).or().eq("sku_id", key);
            });
        }

        Long wareId = null;
        try {
            wareId = Long.parseLong((String) params.get("wareId"));
        } catch (Exception ignored) {
        }
        if (ObjectUtil.isNotNull(wareId) && wareId > 0) {
            wrapper.eq("ware_id", wareId);
        }

        Long status = null;
        try {
            status = Long.parseLong((String) params.get("status"));
        } catch (Exception ignored) {
        }
        if (ObjectUtil.isNotNull(status)) {
            wrapper.eq("status", status);
        }

        list = baseDao.selectList(wrapper);
        return new PageData<>(list, list.size());
    }
}