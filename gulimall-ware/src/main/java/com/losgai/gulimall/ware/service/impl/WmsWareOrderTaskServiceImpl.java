package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsWareOrderTaskDao;
import com.losgai.gulimall.ware.dto.WmsWareOrderTaskDTO;
import com.losgai.gulimall.ware.entity.WmsWareOrderTaskEntity;
import com.losgai.gulimall.ware.service.WmsWareOrderTaskService;
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
public class WmsWareOrderTaskServiceImpl extends CrudServiceImpl<WmsWareOrderTaskDao, WmsWareOrderTaskEntity, WmsWareOrderTaskDTO> implements WmsWareOrderTaskService {

    @Override
    public QueryWrapper<WmsWareOrderTaskEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsWareOrderTaskEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}