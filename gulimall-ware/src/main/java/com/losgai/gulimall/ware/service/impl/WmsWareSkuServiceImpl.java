package com.losgai.gulimall.ware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.ware.dao.WmsWareSkuDao;
import com.losgai.gulimall.ware.dto.SkuInfoDTO;
import com.losgai.gulimall.ware.dto.WmsWareSkuDTO;
import com.losgai.gulimall.ware.entity.WmsWareSkuEntity;
import com.losgai.gulimall.ware.feign.ProductFeignService;
import com.losgai.gulimall.ware.service.WmsWareSkuService;
import cn.hutool.core.util.StrUtil;
import com.losgai.gulimall.ware.vo.SkuStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsWareSkuServiceImpl extends CrudServiceImpl<WmsWareSkuDao, WmsWareSkuEntity, WmsWareSkuDTO> implements WmsWareSkuService {

    @Autowired
    private WmsWareSkuDao wmsWareSkuDao;

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public QueryWrapper<WmsWareSkuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WmsWareSkuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<WmsWareSkuEntity> pageQuery(Map<String, Object> param,Long wareId,Long skuId) {
        List<WmsWareSkuEntity> list;

        QueryWrapper<WmsWareSkuEntity> wrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotNull(wareId)){
            wrapper.eq("ware_id",wareId);
        }
        if(ObjectUtil.isNotNull(skuId)){
            wrapper.eq("sku_id",skuId);
        }

        list = wmsWareSkuDao.selectList(wrapper);
        return new PageData<>(list, list.size());
    }

    @Override
    @Transactional
    public void addToStock(Long skuId, Long wareId, Integer stock) {
        List<WmsWareSkuEntity> wmsWareSkuEntities = wmsWareSkuDao.selectList(new QueryWrapper<WmsWareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if(wmsWareSkuEntities.isEmpty()){ // 没有库存记录，新增
            WmsWareSkuEntity wmsWareSkuEntity = new WmsWareSkuEntity();
            wmsWareSkuEntity.setSkuId(skuId);
            wmsWareSkuEntity.setWareId(wareId);
            wmsWareSkuEntity.setStock(stock);
            wmsWareSkuEntity.setStockLocked(0);
            try{ // 查询skuName，如果失败，事务无需回滚 使用try-catch
                Result<SkuInfoDTO> skuInfoDTOResult = productFeignService.get(skuId);
                if(skuInfoDTOResult.getCode() == 0){ // 远程调用请求成功
                    wmsWareSkuEntity.setSkuName(skuInfoDTOResult.getData().getSkuName());
                }
            }catch (Exception e){ // TODO: 还有别的方法保证失败不回滚
                System.out.println("RPC Process failed: " + e);
            }
            wmsWareSkuDao.insert(wmsWareSkuEntity);
        }else { // 有库存记录，更新
            wmsWareSkuDao.addToStock(skuId, wareId, stock);
        }
    }

    @Override
    public List<SkuStockVo> getSkuHasStock(List<Long> skuIds) {
        return wmsWareSkuDao.getSkuHasStock(skuIds);
    }
}