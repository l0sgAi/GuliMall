package com.losgai.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.coupon.dao.MemberPriceDao;
import com.losgai.gulimall.coupon.dto.MemberPriceDTO;
import com.losgai.gulimall.coupon.entity.MemberPriceEntity;
import com.losgai.gulimall.coupon.service.MemberPriceService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberPriceServiceImpl extends CrudServiceImpl<MemberPriceDao, MemberPriceEntity, MemberPriceDTO> implements MemberPriceService {

    @Override
    public QueryWrapper<MemberPriceEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberPriceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}