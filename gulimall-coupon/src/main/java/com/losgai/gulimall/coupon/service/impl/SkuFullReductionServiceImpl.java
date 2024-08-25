package com.losgai.gulimall.coupon.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.SkuFullReductionDao;
import com.losgai.gulimall.coupon.dto.MemberPrice;
import com.losgai.gulimall.coupon.dto.SkuFullReductionDTO;
import com.losgai.gulimall.coupon.dto.SkuLadderDTO;
import com.losgai.gulimall.coupon.dto.SkuReductionDTO;
import com.losgai.gulimall.coupon.entity.MemberPriceEntity;
import com.losgai.gulimall.coupon.entity.SkuFullReductionEntity;
import com.losgai.gulimall.coupon.service.MemberPriceService;
import com.losgai.gulimall.coupon.service.SkuFullReductionService;
import com.losgai.gulimall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品满减信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class SkuFullReductionServiceImpl extends CrudServiceImpl<SkuFullReductionDao, SkuFullReductionEntity, SkuFullReductionDTO> implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;

    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public QueryWrapper<SkuFullReductionEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<SkuFullReductionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    @Transactional
    public void saveReduction(SkuReductionDTO dto) {
        //1. 保存满减打折和会员价 sms_sku_ladder
        SkuLadderDTO skuLadder = new SkuLadderDTO();
        BeanUtils.copyProperties(dto, skuLadder);
        skuLadder.setAddOther(dto.getCountStatus());
        //TODO: skuLadder.setPrice(); 后续计算折扣价
        if (skuLadder.getFullCount() > 0) {
            skuLadderService.save(skuLadder);
        }

        //2.sms_sku_full_reduction
        SkuFullReductionDTO skuFullReductionDTO = new SkuFullReductionDTO();
        BeanUtils.copyProperties(dto, skuFullReductionDTO);
        if (skuFullReductionDTO.getFullPrice().compareTo(new BigDecimal("0")) > 0) {
            this.save(skuFullReductionDTO);
        }


        //3.sms_member_price
        List<MemberPrice> memberPrice = dto.getMemberPrice();
        if (memberPrice != null && !memberPrice.isEmpty()) {
            List<MemberPriceEntity> memberPrices = memberPrice.stream().map(item -> {
                        MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
                        memberPriceEntity.setSkuId(dto.getSkuId());
                        memberPriceEntity.setMemberLevelId(item.getId());
                        memberPriceEntity.setMemberLevelName(item.getName());
                        memberPriceEntity.setMemberPrice(item.getPrice());
                        memberPriceEntity.setAddOther(1);
                        return memberPriceEntity;
                    })
                    .filter(i -> i.getMemberPrice().compareTo(new BigDecimal("0")) > 0)
                    .collect(Collectors.toList());
            memberPriceService.insertBatch(memberPrices);
        }
    }
}