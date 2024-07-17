package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsWareInfoDao;
import com.losgai.gulimall.ware.dto.WmsWareInfoDTO;
import com.losgai.gulimall.ware.entity.WmsWareInfoEntity;
import com.losgai.gulimall.ware.service.WmsWareInfoService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsWareInfoServiceImpl extends CrudServiceImpl<WmsWareInfoDao, WmsWareInfoEntity, WmsWareInfoDTO> implements WmsWareInfoService {

    @Override
    public QueryWrapper<WmsWareInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsWareInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}