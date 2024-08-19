package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.losgai.gulimall.product.dao.AttrDao;
import com.losgai.gulimall.product.dao.AttrGroupDao;
import com.losgai.gulimall.product.dto.AttrGroupDTO;
import com.losgai.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.losgai.gulimall.product.entity.AttrEntity;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.service.AttrGroupService;
import com.losgai.gulimall.product.vo.AttrGroupWithAttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public QueryWrapper<AttrGroupEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<AttrGroupEntity> queryPageByCatId(Map<String, Object> params, long categoryId) {
        List<AttrGroupEntity> list;

        if (categoryId == 0) {
            list = attrGroupDao.selectList(null);
            return new PageData<>(list, list.size());
        }

        list = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", categoryId));
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(list, list.size());
    }

    @Override
    public PageData<AttrGroupEntity> queryPageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key) {
        List<AttrGroupEntity> list;

        if (categoryId == 0) {
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

    @Override
    public List<AttrGroupEntity> selectByCatId(long catId) {
        List<AttrGroupEntity> list;
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catId);

        list = attrGroupDao.selectList(wrapper);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttrGroupWithAttrVo> getCatAttrGroupAttrs(Long catId) {
        QueryWrapper<AttrGroupEntity> wrapperCatId = new QueryWrapper<>();
        wrapperCatId.eq("catelog_id", catId); //查出对应catId的属性组
        List<AttrGroupEntity> attrGroupEntities = attrGroupDao.selectList(wrapperCatId);
        if (attrGroupEntities.isEmpty()) {
            return Collections.emptyList();
        }

        // 对于attrGroupEntities中的每一个对象，复制属性至AttrGroupWithAttrVo
        // 查询对应的规格属性，放到attrGroupWithAttrVo的attrs属性中，流处理

        return attrGroupEntities.stream()
                .filter(Objects::nonNull) // 过滤掉null元素
                .map(attrGroupEntity -> {
                    AttrGroupWithAttrVo attrGroupWithAttrVo = new AttrGroupWithAttrVo();
                    BeanUtils.copyProperties(attrGroupEntity, attrGroupWithAttrVo);
                    QueryWrapper<AttrAttrgroupRelationEntity> wrapperAttrIds = new QueryWrapper<>();
                    wrapperAttrIds.eq("attr_group_id", attrGroupEntity.getAttrGroupId());
                    List<AttrAttrgroupRelationEntity> relationEntities = attrAttrgroupRelationDao.selectList(wrapperAttrIds);

                    if (relationEntities.isEmpty()) {
                        return attrGroupWithAttrVo;
                    }

                    List<Long> attrIds = relationEntities.stream()
                            .map(AttrAttrgroupRelationEntity::getAttrId)
                            .toList();
                    List<AttrEntity> attrEntities = attrDao.selectBatchIds(attrIds);

                    if (attrEntities.isEmpty()) {
                        return attrGroupWithAttrVo;
                    }

                    attrGroupWithAttrVo.setAttrs(attrEntities);
                    return attrGroupWithAttrVo;
                }).toList();
    }
}