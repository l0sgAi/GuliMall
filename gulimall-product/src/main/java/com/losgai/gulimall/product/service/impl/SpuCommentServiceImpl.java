package com.losgai.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.SpuCommentDao;
import com.losgai.gulimall.product.dto.SpuCommentDTO;
import com.losgai.gulimall.product.entity.SpuCommentEntity;
import com.losgai.gulimall.product.service.SpuCommentService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品评价
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class SpuCommentServiceImpl extends CrudServiceImpl<SpuCommentDao, SpuCommentEntity, SpuCommentDTO> implements SpuCommentService {

    @Override
    public QueryWrapper<SpuCommentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SpuCommentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}