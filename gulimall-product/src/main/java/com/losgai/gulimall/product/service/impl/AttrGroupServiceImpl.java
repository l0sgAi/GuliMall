package com.losgai.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.AttrGroupDao;
import com.losgai.gulimall.product.dto.AttrGroupDTO;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.service.AttrGroupService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class AttrGroupServiceImpl extends CrudServiceImpl<AttrGroupDao, AttrGroupEntity, AttrGroupDTO> implements AttrGroupService {

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Override
    public QueryWrapper<AttrGroupEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<AttrGroupEntity> queryPageByCatId(Map<String, Object> params, long categoryId) {
        List<AttrGroupEntity> list = new ArrayList<>();

        if(categoryId == 0){
            list = attrGroupDao.selectList(null);
            return new PageData<>(list, list.size());
        }

        list = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", categoryId));
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(list, list.size());
    }

    @Override
    public PageData<AttrGroupEntity> queryPageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key) {
        List<AttrGroupEntity> list = new ArrayList<>();

        if(categoryId == 0){
            //获取所有attr_group_name like %key% 的分组记录
            QueryWrapper<AttrGroupEntity> like = new QueryWrapper<AttrGroupEntity>()
                    .like(StrUtil.isNotBlank(key), "attr_group_name", key);
            list = attrGroupDao.selectList(like);
            return new PageData<>(list, list.size());
        }

        QueryWrapper<AttrGroupEntity> andOrWrapper = new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", categoryId)
                .and(wrapper -> wrapper
                        .like(StrUtil.isNotBlank(key), "attr_group_name", key));

        list = attrGroupDao.selectList(andOrWrapper);
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(list, list.size());
    }
}