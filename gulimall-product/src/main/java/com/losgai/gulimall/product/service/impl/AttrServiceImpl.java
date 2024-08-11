package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.losgai.gulimall.product.dao.AttrDao;
import com.losgai.gulimall.product.dao.AttrGroupDao;
import com.losgai.gulimall.product.dto.AttrDTO;
import com.losgai.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.losgai.gulimall.product.entity.AttrEntity;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.service.AttrService;
import com.losgai.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Override
    public QueryWrapper<AttrEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    @Transactional(readOnly = true)
    public PageData<AttrVo> queryPageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key) {
        List<AttrEntity> list;

        if (categoryId == 0) {
            //获取所有attr_group_name like %key% 的分组记录
            QueryWrapper<AttrEntity> like = new QueryWrapper<AttrEntity>()
                    .like(StrUtil.isNotBlank(key), "attr_name", key);
            list = attrDao.selectList(like); //获取结果列表
            List<AttrVo> voList = toAttrVoList(list);

            return new PageData<>(voList, voList.size());
        }

        QueryWrapper<AttrEntity> andOrWrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", categoryId)
                .and(wrapper -> wrapper
                        .like(StrUtil.isNotBlank(key), "attr_name", key));

        list = attrDao.selectList(andOrWrapper);
        List<AttrVo> voList = toAttrVoList(list);
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(voList, voList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public PageData<AttrVo> queryPageByCatId(Map<String, Object> params, long categoryId) {
        List<AttrEntity> list;

        if (categoryId == 0) {
            list = attrDao.selectList(null);
            List<AttrVo> voList = toAttrVoList(list);
            return new PageData<>(voList, voList.size());
        }

        list = attrDao.selectList(new QueryWrapper<AttrEntity>().eq("catelog_id", categoryId));
        List<AttrVo> voList = toAttrVoList(list);
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(toAttrVoList(list), voList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public AttrVo getVoById(Long id) {
        AttrEntity entity = attrDao.selectById(id);
        return toAttrVo(entity);
    }

    private List<AttrVo> toAttrVoList(List<AttrEntity> list) {
        List<AttrVo> voList = new ArrayList<>();
        //将list中所有AttrEntity转成AttrVo
        for (AttrEntity entity : list) { //构建VO
            AttrVo attrVo = new AttrVo();
            attrVo.setAttrId(entity.getAttrId());
            attrVo.setAttrName(entity.getAttrName());
            attrVo.setSearchType(entity.getSearchType());
            attrVo.setIcon(entity.getIcon());
            attrVo.setValueSelect(entity.getValueSelect());
            attrVo.setAttrType(entity.getAttrType());
            attrVo.setEnable(entity.getEnable());
            attrVo.setCatelogId(entity.getCatelogId());
            attrVo.setShowDesc(entity.getShowDesc());

            QueryWrapper<AttrAttrgroupRelationEntity> eqWrapper = new QueryWrapper<>();
            eqWrapper.eq("attr_id", entity.getAttrId());

            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(eqWrapper);
            if(ObjectUtil.isNotNull(attrAttrgroupRelationEntity)){ //判空
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if(ObjectUtil.isNotNull(attrGroupEntity)){
                    attrVo.setGroupName(attrGroupEntity.getAttrGroupId());
                }
            }

            voList.add(attrVo);
        }

        return voList;
    }

    private AttrVo toAttrVo(AttrEntity entity) {
        AttrVo attrVo = new AttrVo();

        attrVo.setAttrId(entity.getAttrId());
        attrVo.setAttrName(entity.getAttrName());
        attrVo.setSearchType(entity.getSearchType());
        attrVo.setIcon(entity.getIcon());
        attrVo.setValueSelect(entity.getValueSelect());
        attrVo.setAttrType(entity.getAttrType());
        attrVo.setEnable(entity.getEnable());
        attrVo.setCatelogId(entity.getCatelogId());
        attrVo.setShowDesc(entity.getShowDesc());

        QueryWrapper<AttrAttrgroupRelationEntity> eqWrapper = new QueryWrapper<>();
        eqWrapper.eq("attr_id", entity.getAttrId());

        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(eqWrapper);
        if(ObjectUtil.isNotNull(attrAttrgroupRelationEntity)){ //判空
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
            if(ObjectUtil.isNotNull(attrGroupEntity)){
                attrVo.setGroupName(attrGroupEntity.getAttrGroupId());
            }
        }

        attrVo.setValueSelectArray(entity.getValueSelect().split(";"));
        return attrVo;
    }
}