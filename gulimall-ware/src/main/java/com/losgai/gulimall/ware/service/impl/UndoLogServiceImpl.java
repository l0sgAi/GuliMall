package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.UndoLogDao;
import com.losgai.gulimall.ware.dto.UndoLogDTO;
import com.losgai.gulimall.ware.entity.UndoLogEntity;
import com.losgai.gulimall.ware.service.UndoLogService;
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
public class UndoLogServiceImpl extends CrudServiceImpl<UndoLogDao, UndoLogEntity, UndoLogDTO> implements UndoLogService {

    @Override
    public QueryWrapper<UndoLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<UndoLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}