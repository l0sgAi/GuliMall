package com.losgai.gulimall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.common.common.service.impl.CrudServiceImpl;
import com.losgai.gulimall.member.dao.IntegrationChangeHistoryDao;
import com.losgai.gulimall.member.dto.IntegrationChangeHistoryDTO;
import com.losgai.gulimall.member.entity.IntegrationChangeHistoryEntity;
import com.losgai.gulimall.member.service.IntegrationChangeHistoryService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Service
public class IntegrationChangeHistoryServiceImpl extends CrudServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity, IntegrationChangeHistoryDTO> implements IntegrationChangeHistoryService {

    @Override
    public QueryWrapper<IntegrationChangeHistoryEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<IntegrationChangeHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}