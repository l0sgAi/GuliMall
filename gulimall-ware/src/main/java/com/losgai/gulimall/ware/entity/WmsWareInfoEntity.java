package com.losgai.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 仓库信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@Data
@TableName("wms_ware_info")
public class WmsWareInfoEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 仓库名
     */
	private String name;
    /**
     * 仓库地址
     */
	private String address;
    /**
     * 区域编码
     */
	private String areacode;

    @TableLogic(value = "1", delval = "0")
    private Integer isShow;
}