package com.losgai.gulimall.member.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 会员收藏的专题活动
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "会员收藏的专题活动")
public class MemberCollectSubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "subject_id")
	private Long subjectId;

	@SchemaProperty(name = "subject_name")
	private String subjectName;

	@SchemaProperty(name = "subject_img")
	private String subjectImg;

	@SchemaProperty(name = "活动url")
	private String subjectUrll;


}
