package com.losgai.gulimall.product.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.product.dto.AttrGroupDTO;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.vo.AttrGroupWithAttrVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
public interface AttrGroupService extends CrudService<AttrGroupEntity, AttrGroupDTO> {

    PageData<AttrGroupEntity> queryPageByCatId(Map<String, Object> params, long categoryId);

    PageData<AttrGroupEntity> queryPageByCatIdAndQuery(Map<String, Object> params, long categoryId, String key);

    List<AttrGroupEntity> selectByCatId(long catId);

    List<AttrGroupWithAttrVo> getCatAttrGroupAttrs(Long catId);
}