package com.losgai.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.MemberDao;
import com.losgai.gulimall.member.dto.MemberDTO;
import com.losgai.gulimall.member.entity.MemberEntity;
import com.losgai.gulimall.member.service.MemberService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberServiceImpl extends CrudServiceImpl<MemberDao, MemberEntity, MemberDTO> implements MemberService {

    @Override
    public QueryWrapper<MemberEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}