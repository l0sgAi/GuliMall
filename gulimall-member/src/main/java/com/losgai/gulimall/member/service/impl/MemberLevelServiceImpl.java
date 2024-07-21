package com.losgai.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.MemberLevelDao;
import com.losgai.gulimall.member.dto.MemberLevelDTO;
import com.losgai.gulimall.member.entity.MemberLevelEntity;
import com.losgai.gulimall.member.service.MemberLevelService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员等级
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberLevelServiceImpl extends CrudServiceImpl<MemberLevelDao, MemberLevelEntity, MemberLevelDTO> implements MemberLevelService {

    @Override
    public QueryWrapper<MemberLevelEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberLevelEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}