/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(title = "登录表单")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "用户名", required = true)
    @NotBlank(message="{sysuser.username.require}")
    private String username;

    @Schema(title = "密码")
    @NotBlank(message="{sysuser.password.require}")
    private String password;

    @Schema(title = "验证码")
    @NotBlank(message="{sysuser.captcha.require}")
    private String captcha;

    @Schema(title = "唯一标识")
    @NotBlank(message="{sysuser.uuid.require}")
    private String uuid;

}
