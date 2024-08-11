package com.losgai.gulimall.product.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 属性&属性分组关联
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "属性&属性分组关联")
public class AttrAttrgroupRelationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "属性id")
	private Long attrId;

	@SchemaProperty(name = "属性分组id")
	private Long attrGroupId;

	@SchemaProperty(name = "属性组内排序")
	private Integer attrSort;

	@TableLogic(value = "1",delval = "0")
	private Integer isShow;
}
