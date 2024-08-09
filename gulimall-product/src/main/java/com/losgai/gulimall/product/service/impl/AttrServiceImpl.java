package com.losgai.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.AttrDao;
import com.losgai.gulimall.product.dto.AttrDTO;
import com.losgai.gulimall.product.entity.AttrEntity;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.service.AttrService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class AttrServiceImpl extends CrudServiceImpl<AttrDao, AttrEntity, AttrDTO> implements AttrService {

    @Autowired
    private AttrDao attrDao;
    @Override
    public QueryWrapper<AttrEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<AttrEntity> queryPageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key) {
        List<AttrEntity> list = new ArrayList<>();

        if(categoryId == 0){
            //获取所有attr_group_name like %key% 的分组记录
            QueryWrapper<AttrEntity> like = new QueryWrapper<AttrEntity>()
                    .like(StrUtil.isNotBlank(key), "attr_name", key);
            list = attrDao.selectList(like);
            return new PageData<>(list, list.size());
        }

        QueryWrapper<AttrEntity> andOrWrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", categoryId)
                .and(wrapper -> wrapper
                        .like(StrUtil.isNotBlank(key), "attr_name", key));

        list = attrDao.selectList(andOrWrapper);
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(list, list.size());
    }

    @Override
    public PageData<AttrEntity> queryPageByCatId(Map<String, Object> params, long categoryId) {
        List<AttrEntity> list = new ArrayList<>();

        if(categoryId == 0){
            list = attrDao.selectList(null);
            return new PageData<>(list, list.size());
        }

        list = attrDao.selectList(new QueryWrapper<AttrEntity>().eq("catelog_id", categoryId));
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(list, list.size());
    }
}