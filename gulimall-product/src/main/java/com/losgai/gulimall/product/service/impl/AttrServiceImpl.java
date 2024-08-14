package com.losgai.gulimall.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.constant.ProductConstant;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public void saveBatch(AttrVo attrVo) {
        AttrDTO attrDTO = new AttrDTO();
        BeanUtils.copyProperties(attrVo, attrDTO);
        //1、保存基本数据
        if(attrVo.getAttrType()== ProductConstant.AttrEnum.BASE_ATTR.getCode()){
            attrDTO.setSearchType(null);
            attrDTO.setShowDesc(null);
        }
        this.save(attrDTO);

        //2、保存关联关系 (先删除现有数据)
        if(attrVo.getAttrType()!=ProductConstant.AttrEnum.BASE_ATTR.getCode()){
            QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", attrVo.getAttrId());
            attrAttrgroupRelationDao.delete(wrapper);

            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrId(attrDTO.getAttrId()); //这里只能用attrDTO，否则attrId取不到
            attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getGroupName());
            attrAttrgroupRelationEntity.setAttrSort(0);

            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }

    }

    @Override
    @Transactional
    public void updateBatch(AttrVo attrVo) {
        AttrDTO attrDTO = new AttrDTO();
        BeanUtils.copyProperties(attrVo, attrDTO);
        //1、更新基本数据
        if(attrVo.getAttrType()== ProductConstant.AttrEnum.BASE_ATTR.getCode()){
            attrDTO.setSearchType(null);
            attrDTO.setShowDesc(null);
        }
        this.update(attrDTO);

        //2、保存关联关系 (如果新关系不为空，先删除现有数据，为空就不新执行插入关联关系)
        if (ObjectUtil.isNotNull(attrVo.getGroupName())) {
            //删除关系
            QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", attrVo.getAttrId());
            attrAttrgroupRelationDao.delete(wrapper);

            //如果不是销售属性，插入关系
            if(attrVo.getAttrType()!= ProductConstant.AttrEnum.BASE_ATTR.getCode()){
                AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                attrAttrgroupRelationEntity.setAttrId(attrVo.getAttrId());
                attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getGroupName());
                attrAttrgroupRelationEntity.setAttrSort(0);

                attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
            }
        }
    }

    @Override
    public void deleteBatch(Long[] ids) {
        attrDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    @Transactional(readOnly = true)
    public PageData<AttrVo> querySalePageByCatId(Map<String, Object> params, long categoryId) {
        List<AttrEntity> list;
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();

        if (categoryId == 0) {
            wrapper.eq("attr_type", 0).or().eq("attr_type", 2);
            list = attrDao.selectList(wrapper);
            List<AttrVo> voList = toAttrVoList(list);
            return new PageData<>(voList, voList.size());
        }

        wrapper.eq("catelog_id", categoryId);
        wrapper.and(i -> i.eq("attr_type", 0).or().eq("attr_type", 2));
        list = attrDao.selectList(wrapper);
        List<AttrVo> voList = toAttrVoList(list);
        //select * from pms_attr_group where catelog_id = categoryId
        return new PageData<>(toAttrVoList(list), voList.size());
    }

    @Override
    @Transactional(readOnly = true)
    public PageData<AttrVo> querySalePageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key) {
        List<AttrEntity> list;
        //获取所有attr_group_name like %key% 的分组记录
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>()
                .like(StrUtil.isNotBlank(key), "attr_name", key)
                .and(i -> i.eq("attr_type", 0).or().eq("attr_type", 2));

        if (categoryId == 0) {
            list = attrDao.selectList(wrapper); //获取结果列表
            List<AttrVo> voList = toAttrVoList(list);
            return new PageData<>(voList, voList.size());
        }

        wrapper.and(wrapper1 -> wrapper1.eq("catelog_id", categoryId));
        list = attrDao.selectList(wrapper);
        List<AttrVo> voList = toAttrVoList(list);

        return new PageData<>(voList, voList.size());
    }

    private List<AttrVo> toAttrVoList(List<AttrEntity> list) {
        return list.stream()
                .map(this::entityToVo)
                .collect(Collectors.toList());
    }

    private AttrVo toAttrVo(AttrEntity entity) {
        return entityToVo(entity);
    }

    private AttrVo entityToVo(AttrEntity entity) {
        AttrVo attrVo = new AttrVo();
        BeanUtils.copyProperties(entity, attrVo);

        QueryWrapper<AttrAttrgroupRelationEntity> eqWrapper = new QueryWrapper<>();
        eqWrapper.eq("attr_id", entity.getAttrId());

        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(eqWrapper);
        if (ObjectUtil.isNotNull(attrAttrgroupRelationEntity)) { //判空
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
            if (ObjectUtil.isNotNull(attrGroupEntity)) {
                attrVo.setGroupName(attrGroupEntity.getAttrGroupId());
            }
        }

        if (StrUtil.isNotBlank(entity.getValueSelect())) {
            attrVo.setValueSelectArray(entity.getValueSelect().split(";"));
        }

        return attrVo;
    }

//    private AttrEntity toAttrEntity(AttrVo attrVO) {
//        AttrEntity attrEntity = new AttrEntity();
//        BeanUtils.copyProperties(attrVO, attrEntity);
//        return attrEntity;
//    }
}