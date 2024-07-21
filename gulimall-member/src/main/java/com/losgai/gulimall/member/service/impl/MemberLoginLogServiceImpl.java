package com.losgai.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.MemberLoginLogDao;
import com.losgai.gulimall.member.dto.MemberLoginLogDTO;
import com.losgai.gulimall.member.entity.MemberLoginLogEntity;
import com.losgai.gulimall.member.service.MemberLoginLogService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberLoginLogServiceImpl extends CrudServiceImpl<MemberLoginLogDao, MemberLoginLogEntity, MemberLoginLogDTO> implements MemberLoginLogService {

    @Override
    public QueryWrapper<MemberLoginLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberLoginLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}