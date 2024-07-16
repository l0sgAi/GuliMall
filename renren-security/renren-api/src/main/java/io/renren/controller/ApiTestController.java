/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.controller;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.utils.Result;
import io.renren.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
@Tag(name = "测试接口")
public class ApiTestController {

    @Login
    @GetMapping("userInfo")
    @Operation(summary = "获取用户信息")
    public Result<UserEntity> userInfo(@Parameter(hidden = true) @LoginUser UserEntity user) {
        return new Result<UserEntity>().ok(user);
    }

    @Login
    @GetMapping("userId")
    @Operation(summary = "获取用户ID")
    public Result<Long> userInfo(@Parameter(hidden = true) @RequestAttribute("userId") Long userId) {
        return new Result<Long>().ok(userId);
    }

    @GetMapping("notToken")
    @Operation(summary = "忽略Token验证测试")
    public Result<String> notToken() {
        return new Result<String>().ok("无需token也能访问。。。");
    }

}
