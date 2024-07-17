package com.losgai.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.MemberCollectSpuDao;
import com.losgai.gulimall.member.dto.MemberCollectSpuDTO;
import com.losgai.gulimall.member.entity.MemberCollectSpuEntity;
import com.losgai.gulimall.member.service.MemberCollectSpuService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberCollectSpuServiceImpl extends CrudServiceImpl<MemberCollectSpuDao, MemberCollectSpuEntity, MemberCollectSpuDTO> implements MemberCollectSpuService {

    @Override
    public QueryWrapper<MemberCollectSpuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberCollectSpuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}