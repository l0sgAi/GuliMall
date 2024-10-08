package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.AttrAttrgroupRelationDTO;
import com.losgai.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.losgai.gulimall.product.vo.AttrAttrGroupVo;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface AttrAttrgroupRelationService extends CrudService<AttrAttrgroupRelationEntity, AttrAttrgroupRelationDTO> {

    List<AttrAttrGroupVo> getGroupRelation(Long attrGroupId);

}