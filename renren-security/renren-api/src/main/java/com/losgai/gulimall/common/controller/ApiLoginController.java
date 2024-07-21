/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.controller;


import com.losgai.gulimall.common.annotation.Login;
import com.losgai.gulimall.common.dto.LoginDTO;
import com.losgai.gulimall.common.service.TokenService;
import com.losgai.gulimall.common.service.UserService;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.common.common.validator.ValidatorUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登录接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
@Tag(name = "登录接口")
@AllArgsConstructor
public class ApiLoginController {
    private final UserService userService;
    private final TokenService tokenService;


    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO dto) {
        //表单校验
        ValidatorUtils.validateEntity(dto);

        //用户登录
        Map<String, Object> map = userService.login(dto);

        return new Result().ok(map);
    }

    @Login
    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result logout(@Parameter(hidden = true) @RequestAttribute("userId") Long userId) {
        tokenService.expireToken(userId);
        return new Result();
    }

}
