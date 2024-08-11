package com.losgai.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.BrandDao;
import com.losgai.gulimall.product.dao.CategoryBrandRelationDao;
import com.losgai.gulimall.product.dto.BrandDTO;
import com.losgai.gulimall.product.entity.AttrEntity;
import com.losgai.gulimall.product.entity.BrandEntity;
import com.losgai.gulimall.product.entity.CategoryBrandRelationEntity;
import com.losgai.gulimall.product.service.BrandService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class BrandServiceImpl extends CrudServiceImpl<BrandDao, BrandEntity, BrandDTO> implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public QueryWrapper<BrandEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<BrandEntity> queryPage(Map<String, Object> params, String key) {
        List<BrandEntity> list = new ArrayList<>();

        QueryWrapper<BrandEntity> andOrWrapper = new QueryWrapper<BrandEntity>()
                .like(StrUtil.isNotBlank(key), "name", key);

        list = brandDao.selectList(andOrWrapper);
        return new PageData<>(list, list.size());
    }

    @Override
    @Transactional
    public void doBatchUpdate(BrandEntity dto) {
        brandDao.updateById(dto);

        Long brandId = dto.getBrandId();
        String brandName = dto.getName();
        //更新CategoryBrandRelationEntity的brand_id=brandId的brand_name为brandName

        // 创建UpdateWrapper实例
        UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件
        updateWrapper.eq("brand_id", brandId);
        // 设置要更新的字段
        updateWrapper.set("brand_name", brandName);

        categoryBrandRelationDao.update(updateWrapper);
    }

//    @Override
//    public void removeBrands(Long[] ids) {
//        baseDao.deleteBatchIds(Arrays.asList(ids));
//    }
}