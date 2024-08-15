package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.losgai.gulimall.product.dao.AttrDao;
import com.losgai.gulimall.product.dao.AttrGroupDao;
import com.losgai.gulimall.product.dto.AttrAttrgroupRelationDTO;
import com.losgai.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.losgai.gulimall.product.entity.AttrEntity;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.service.AttrAttrgroupRelationService;
import com.losgai.gulimall.product.vo.AttrAttrGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 属性&属性分组关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class AttrAttrgroupRelationServiceImpl extends CrudServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity, AttrAttrgroupRelationDTO> implements AttrAttrgroupRelationService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrGroupDao attrGroupDao;
    @Override
    public QueryWrapper<AttrAttrgroupRelationEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }
    @Override
    @Transactional(readOnly = true)
    public List<AttrAttrGroupVo> getGroupRelation(Long attrGroupId) {
        QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_group_id", attrGroupId); //查出基本关系
        List<AttrAttrgroupRelationEntity> attrAttrGroupRelationEntities = attrAttrgroupRelationDao.selectList(wrapper);
        if(ObjectUtil.isNotNull(attrAttrGroupRelationEntities))
            return toVo(attrAttrGroupRelationEntities);
        return new ArrayList<>();
    }

    private List<AttrAttrGroupVo> toVo(List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities) {
        return attrAttrgroupRelationEntities.stream().map(relation -> {
            AttrEntity attrEntity = attrDao.selectById(relation.getAttrId());
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relation.getAttrGroupId());

            if (ObjectUtil.isNull(attrEntity) || ObjectUtil.isNull(attrGroupEntity)) {
                return null; // 如果任何一个实体为空，则返回null
            }

            AttrAttrGroupVo vo = new AttrAttrGroupVo();
            BeanUtils.copyProperties(relation, vo);
            vo.setAttrName(attrEntity.getAttrName());
            vo.setAttrGroupName(attrGroupEntity.getAttrGroupName());
            if(StrUtil.isNotBlank(attrEntity.getValueSelect())){
                vo.setValueSelect(attrEntity.getValueSelect());
                vo.setValueSelectArray(attrEntity.getValueSelect().split(";"));
            }
            return vo;
        }).filter(Objects::nonNull) // 过滤掉所有null值
                .collect(Collectors.toList());
    }
}