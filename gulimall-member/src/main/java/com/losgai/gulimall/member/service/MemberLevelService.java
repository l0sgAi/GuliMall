package com.losgai.gulimall.member.service;

import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.service.CrudService;
import com.losgai.gulimall.member.dto.MemberLevelDTO;
import com.losgai.gulimall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
public interface MemberLevelService extends CrudService<MemberLevelEntity, MemberLevelDTO> {

    PageData<MemberLevelEntity> queryPage(Map<String, Object> params, String key);
}