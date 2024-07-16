package com.losgai.gulimall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * spu图片
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@Data
@Schema(name = "spu图片")
public class SpuImagesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "spu_id")
	private Long spuId;

	@SchemaProperty(name = "图片名")
	private String imgName;

	@SchemaProperty(name = "图片地址")
	private String imgUrl;

	@SchemaProperty(name = "顺序")
	private Integer imgSort;

	@SchemaProperty(name = "是否默认图")
	private Integer defaultImg;


}
