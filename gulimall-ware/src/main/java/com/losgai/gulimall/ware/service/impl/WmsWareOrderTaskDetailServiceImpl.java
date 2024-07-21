package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsWareOrderTaskDetailDao;
import com.losgai.gulimall.ware.dto.WmsWareOrderTaskDetailDTO;
import com.losgai.gulimall.ware.entity.WmsWareOrderTaskDetailEntity;
import com.losgai.gulimall.ware.service.WmsWareOrderTaskDetailService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsWareOrderTaskDetailServiceImpl extends CrudServiceImpl<WmsWareOrderTaskDetailDao, WmsWareOrderTaskDetailEntity, WmsWareOrderTaskDetailDTO> implements WmsWareOrderTaskDetailService {

    @Override
    public QueryWrapper<WmsWareOrderTaskDetailEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsWareOrderTaskDetailEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}