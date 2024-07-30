package com.losgai.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.BrandDao;
import com.losgai.gulimall.product.dto.BrandDTO;
import com.losgai.gulimall.product.entity.BrandEntity;
import com.losgai.gulimall.product.service.BrandService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * 品牌
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<BrandDao, BrandEntity, BrandDTO> implements BrandService {

    @Override
    public QueryWrapper<BrandEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


//    @Override
//    public void removeBrands(Long[] ids) {
//        baseDao.deleteBatchIds(Arrays.asList(ids));
//    }
}