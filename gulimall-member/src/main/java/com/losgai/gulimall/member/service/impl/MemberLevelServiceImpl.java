package com.losgai.gulimall.member.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.MemberLevelDao;
import com.losgai.gulimall.member.dto.MemberLevelDTO;
import com.losgai.gulimall.member.entity.MemberLevelEntity;
import com.losgai.gulimall.member.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 会员等级
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class MemberLevelServiceImpl extends CrudServiceImpl<MemberLevelDao, MemberLevelEntity, MemberLevelDTO> implements MemberLevelService {

    @Autowired
    private MemberLevelDao memberLevelDao;
    @Override
    public QueryWrapper<MemberLevelEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MemberLevelEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<MemberLevelEntity> queryPage(Map<String, Object> params, String key) {
        List<MemberLevelEntity> list;

        QueryWrapper<MemberLevelEntity> andOrWrapper = new QueryWrapper<MemberLevelEntity>()
                .like(StrUtil.isNotBlank(key), "name", key);

        list = memberLevelDao.selectList(andOrWrapper);
        return new PageData<>(list, list.size());
    }
}