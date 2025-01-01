package com.losgai.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.product.dao.ProductAttrValueDao;
import com.losgai.gulimall.product.dto.ProductAttrValueDTO;
import com.losgai.gulimall.product.entity.ProductAttrValueEntity;
import com.losgai.gulimall.product.service.ProductAttrValueService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Service
public class ProductAttrValueServiceImpl extends CrudServiceImpl<ProductAttrValueDao, ProductAttrValueEntity, ProductAttrValueDTO> implements ProductAttrValueService {

    @Autowired
    private ProductAttrValueDao productAttrValueDao;
    @Override
    public QueryWrapper<ProductAttrValueEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ProductAttrValueEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<ProductAttrValueEntity> getListBySpuId(Long id) {
        return productAttrValueDao.selectList(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", id));
    }

    @Override
    @Transactional
    public void updateAttrValueBySpuId(List<ProductAttrValueEntity> values, Long spuId) {
        // 1.先把前面的数据删除，再重新插入
        productAttrValueDao.delete(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id",spuId));
        // 2. 插入新的数据
        values.forEach(item -> item.setSpuId(spuId));
        insertBatch(values);
    }
}