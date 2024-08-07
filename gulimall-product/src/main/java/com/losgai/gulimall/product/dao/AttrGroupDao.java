package com.losgai.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.losgai.gulimall.common.common.dao.BaseDao;
import com.losgai.gulimall.product.dto.AttrGroupDTO;
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 属性分组
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Mapper
public interface AttrGroupDao extends BaseDao<AttrGroupEntity>, BaseMapper<AttrGroupEntity> {

	
}