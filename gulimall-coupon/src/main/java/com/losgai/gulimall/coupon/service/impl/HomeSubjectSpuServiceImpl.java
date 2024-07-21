package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.HomeSubjectSpuDao;
import com.losgai.gulimall.coupon.dto.HomeSubjectSpuDTO;
import com.losgai.gulimall.coupon.entity.HomeSubjectSpuEntity;
import com.losgai.gulimall.coupon.service.HomeSubjectSpuService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 专题商品
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class HomeSubjectSpuServiceImpl extends CrudServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity, HomeSubjectSpuDTO> implements HomeSubjectSpuService {

    @Override
    public QueryWrapper<HomeSubjectSpuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<HomeSubjectSpuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}