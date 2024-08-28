package com.losgai.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsWareInfoDao;
import com.losgai.gulimall.ware.dto.WmsWareInfoDTO;
import com.losgai.gulimall.ware.entity.WmsWareInfoEntity;
import com.losgai.gulimall.ware.service.WmsWareInfoService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 仓库信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsWareInfoServiceImpl extends CrudServiceImpl<WmsWareInfoDao, WmsWareInfoEntity, WmsWareInfoDTO> implements WmsWareInfoService {

    @Autowired
    private WmsWareInfoDao wmsWareInfoDao;
    @Override
    public QueryWrapper<WmsWareInfoEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsWareInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<WmsWareInfoEntity> pageQuery(Map<String, Object> params,String key) {
        List<WmsWareInfoEntity> list;

        QueryWrapper<WmsWareInfoEntity> andOrWrapper = new QueryWrapper<WmsWareInfoEntity>()
                .like(StrUtil.isNotBlank(key), "name", key);

        list = wmsWareInfoDao.selectList(andOrWrapper);

        return new PageData<>(list, list.size());
    }
}