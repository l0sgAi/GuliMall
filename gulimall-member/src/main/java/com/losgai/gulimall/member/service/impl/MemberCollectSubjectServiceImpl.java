package com.losgai.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.MemberCollectSubjectDao;
import com.losgai.gulimall.member.dto.MemberCollectSubjectDTO;
import com.losgai.gulimall.member.entity.MemberCollectSubjectEntity;
import com.losgai.gulimall.member.service.MemberCollectSubjectService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberCollectSubjectServiceImpl extends CrudServiceImpl<MemberCollectSubjectDao, MemberCollectSubjectEntity, MemberCollectSubjectDTO> implements MemberCollectSubjectService {

    @Override
    public QueryWrapper<MemberCollectSubjectEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberCollectSubjectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}