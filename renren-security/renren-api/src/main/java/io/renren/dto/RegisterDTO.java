/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(title = "注册表单")
public class RegisterDTO {
    @Schema(title = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @Schema(title = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

}
