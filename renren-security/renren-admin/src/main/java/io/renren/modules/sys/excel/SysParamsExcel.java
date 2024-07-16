/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 参数管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
public class SysParamsExcel {
    @ExcelProperty("参数编码")
    private String paramCode;
    @ExcelProperty("参数值")
    private String paramValue;
    @ExcelProperty("备注")
    private String remark;

}