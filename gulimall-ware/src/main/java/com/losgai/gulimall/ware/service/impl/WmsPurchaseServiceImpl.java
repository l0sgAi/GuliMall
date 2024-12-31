package com.losgai.gulimall.ware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.constant.WareConstant;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.ware.dao.WmsPurchaseDao;
import com.losgai.gulimall.ware.dto.WmsPurchaseDTO;
import com.losgai.gulimall.ware.dto.WmsPurchaseDetailDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseDetailEntity;
import com.losgai.gulimall.ware.entity.WmsPurchaseEntity;
import com.losgai.gulimall.ware.service.WmsPurchaseDetailService;
import com.losgai.gulimall.ware.service.WmsPurchaseService;
import com.losgai.gulimall.ware.service.WmsWareSkuService;
import com.losgai.gulimall.ware.vo.MergeVo;
import com.losgai.gulimall.ware.vo.PurchaseDoneVO;
import com.losgai.gulimall.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采购信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class WmsPurchaseServiceImpl extends CrudServiceImpl<WmsPurchaseDao, WmsPurchaseEntity, WmsPurchaseDTO> implements WmsPurchaseService {

    @Autowired
    private WmsPurchaseDao wmsPurchaseDao;

    @Autowired
    private WmsPurchaseDetailService wmsPurchaseDetailService;

    @Autowired
    private WmsWareSkuService wmsWareSkuService;

    @Override
    public QueryWrapper<WmsPurchaseEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<WmsPurchaseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<WmsPurchaseEntity> pageQuery(Map<String, Object> params) {
        QueryWrapper<WmsPurchaseEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (StrUtil.isNotBlank(key)) {
            wrapper.and(w -> w.eq("id", key).or().like("assignee_name", key));
        }
        Integer status = null;
        try {
            status = Integer.parseInt((String) params.get("status"));
        } catch (Exception ignored) {
        }
        if (ObjectUtil.isNotNull(status)) {
            wrapper.eq("status", status);
        }

        List<WmsPurchaseEntity> list = wmsPurchaseDao.selectList(wrapper);
        return new PageData<>(list, list.size());
    }

    @Override
    public PageData<WmsPurchaseEntity> pageNoAssign(Map<String, Object> params) {
        QueryWrapper<WmsPurchaseEntity> wrapper = new QueryWrapper<>();

        wrapper.eq("status", 0).or().eq("status", 1);

        List<WmsPurchaseEntity> list = wmsPurchaseDao.selectList(wrapper);
        return new PageData<>(list, list.size());
    }

    @Override
    @Transactional
    public void merge(MergeVo vo) {
        Long purchaseId = vo.getPurchaseId();
        if (ObjectUtil.isNull(purchaseId)) { //无purchaseId，新建
            WmsPurchaseEntity wmsPurchaseEntity = new WmsPurchaseEntity();
            wmsPurchaseEntity.setStatus(WareConstant.PurchaseEnum.CREATED.getCode());
            wmsPurchaseEntity.setCreateTime(new Date());
            wmsPurchaseEntity.setUpdateTime(new Date());
            wmsPurchaseDao.insert(wmsPurchaseEntity);
            purchaseId = wmsPurchaseEntity.getId();
        }

        List<Long> items = vo.getItems();
        Long finalPurchaseId = purchaseId;
        List<WmsPurchaseDetailEntity> detailEntities = items.stream().map(i -> {
            WmsPurchaseDetailEntity detailEntity = new WmsPurchaseDetailEntity();
            detailEntity.setId(i);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(WareConstant.PurchaseDetailEnum.ASSIGNED.getCode());
            return detailEntity;
        }).collect(Collectors.toList());

        wmsPurchaseDetailService.updateBatchById(detailEntities);

        WmsPurchaseEntity wmsPurchaseEntity = new WmsPurchaseEntity();
        wmsPurchaseEntity.setId(purchaseId);
        wmsPurchaseEntity.setUpdateTime(new Date());
        wmsPurchaseDao.updateById(wmsPurchaseEntity);
    }

    /**
    * &#064;param:  receivedIds 采购单id列表
    *  */
    @Override
    @Transactional
    public void receivePurchase(List<Long> receivedIds) {
        // 1.确认当前采购单是新建或者已分配状态 ps:应该还要确认采购单属于该用户
        List<WmsPurchaseEntity> list = receivedIds.stream().map(id -> {
            // 根据id获取对应的采购单
            return wmsPurchaseDao.selectById(id);
        }).filter(wmsPurchaseEntity ->
                wmsPurchaseEntity.getStatus() == WareConstant.PurchaseEnum.CREATED.getCode() ||
                        wmsPurchaseEntity.getStatus() == WareConstant.PurchaseEnum.ASSIGNED.getCode())
                .peek(wmsPurchaseEntity -> {
                    wmsPurchaseEntity.setStatus(WareConstant.PurchaseEnum.RECEIVED.getCode());
                    wmsPurchaseEntity.setUpdateTime(new Date());
                }).collect(Collectors.toList());

        // 2.改变采购单状态至"已领取"
        if(!list.isEmpty())
            updateBatchById(list);

        // 3. 改变采购项目的状态
        list.forEach(wmsPurchaseEntity -> {
            // 获取每个采购单的对应的采购项目
            List<WmsPurchaseDetailEntity> detailList =
                    wmsPurchaseDetailService.listDetailByPurchaseId(wmsPurchaseEntity.getId());
            // 改变每个采购项目的状态至 "购买中"
            List<WmsPurchaseDetailEntity> detailListCollect = detailList.stream().map(item -> {
                WmsPurchaseDetailEntity detailEntity = new WmsPurchaseDetailEntity();
                detailEntity.setId(item.getId());
                detailEntity.setStatus(WareConstant.PurchaseDetailEnum.PURCHASING.getCode());
                return detailEntity;
            }).collect(Collectors.toList());
            // 批量修改采购项目状态
            wmsPurchaseDetailService.updateBatchById(detailListCollect);
        });
    }

    /*TODO: 完善数据库，在wms_purchase_detail表中添加
     *  采购项目的细节状态、未成功原因等 */
    @Override
    @Transactional
    public void done(PurchaseDoneVO itemVo) {
        Long id = itemVo.getId();

        //1.改变采购项目的状态
        boolean isSuccess = true; // 采购是否成功的标志
        List<PurchaseItemDoneVo> items = itemVo.getItems();
        List<WmsPurchaseDetailEntity> shouldUpdateItems = new ArrayList<>(); // 需要更新的采购项目
        for(PurchaseItemDoneVo item : items){
            if(item.getStatus() == WareConstant.PurchaseDetailEnum.REJECTED.getCode())
                isSuccess = false;
            else { // 采购项目成功
                //3.将采购的物品入库，skuId，wareId和增加的stock库存数量
                // 商品采购详细信息,查出采购项目的skuId，wareId和增加的stock库存数量
                WmsPurchaseDetailDTO purchaseDetail = wmsPurchaseDetailService.get(item.getItemId());
                // 执行库存更新
                wmsWareSkuService.addToStock(purchaseDetail.getSkuId(), purchaseDetail.getWareId(), purchaseDetail.getSkuNum());
            }
            WmsPurchaseDetailEntity detailEntity = new WmsPurchaseDetailEntity();
            detailEntity.setId(item.getItemId());
            detailEntity.setStatus(item.getStatus());
            shouldUpdateItems.add(detailEntity);
        }
        wmsPurchaseDetailService.updateBatchById(shouldUpdateItems);

        //2. 改变采购单状态
        WmsPurchaseEntity purchaseEntity = new WmsPurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(isSuccess ? WareConstant.PurchaseEnum.FINISHED.getCode() : WareConstant.PurchaseEnum.ERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        updateById(purchaseEntity);
    }
}