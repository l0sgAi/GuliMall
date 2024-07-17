package com.losgai.gulimall.ware.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * 仓库信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@Schema(name = "仓库信息")
public class WmsWareInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "id")
	private Long id;

	@SchemaProperty(name = "仓库名")
	private String name;

	@SchemaProperty(name = "仓库地址")
	private String address;

	@SchemaProperty(name = "区域编码")
	private String areacode;


}
