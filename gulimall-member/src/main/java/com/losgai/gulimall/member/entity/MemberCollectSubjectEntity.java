package com.losgai.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 会员收藏的专题活动
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("ums_member_collect_subject")
public class MemberCollectSubjectEntity {

    /**
     * id
     */
	private Long id;
    /**
     * subject_id
     */
	private Long subjectId;
    /**
     * subject_name
     */
	private String subjectName;
    /**
     * subject_img
     */
	private String subjectImg;
    /**
     * 活动url
     */
	private String subjectUrll;
}