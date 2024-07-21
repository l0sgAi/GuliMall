/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.sys.controller;

import com.losgai.gulimall.common.common.annotation.LogOperation;
import com.losgai.gulimall.common.modules.security.service.ShiroService;
import com.losgai.gulimall.common.modules.security.user.SecurityUser;
import com.losgai.gulimall.common.modules.security.user.UserDetail;
import com.losgai.gulimall.common.modules.sys.service.SysMenuService;
import com.losgai.gulimall.common.exception.ErrorCode;
import com.losgai.gulimall.common.utils.Result;
import com.losgai.gulimall.common.validator.AssertUtils;
import com.losgai.gulimall.common.validator.ValidatorUtils;
import com.losgai.gulimall.common.validator.group.DefaultGroup;
import com.losgai.gulimall.common.modules.sys.dto.SysMenuDTO;
import com.losgai.gulimall.common.modules.sys.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/menu")
@Tag(name = "菜单管理")
@AllArgsConstructor
public class SysMenuController {
    private final SysMenuService sysMenuService;
    private final ShiroService shiroService;

    @GetMapping("nav")
    @Operation(summary = "导航")
    public Result<List<SysMenuDTO>> nav() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());

        return new Result<List<SysMenuDTO>>().ok(list);
    }

    @GetMapping("permissions")
    @Operation(summary = "权限标识")
    public Result<Set<String>> permissions() {
        UserDetail user = SecurityUser.getUser();
        Set<String> set = shiroService.getUserPermissions(user);

        return new Result<Set<String>>().ok(set);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @Parameter(name = "type", description = "菜单类型 0：菜单 1：按钮  null：全部", in = ParameterIn.QUERY, ref = "int")
    @RequiresPermissions("sys:menu:list")
    public Result<List<SysMenuDTO>> list(Integer type) {
        List<SysMenuDTO> list = sysMenuService.getAllMenuList(type);

        return new Result<List<SysMenuDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:menu:info")
    public Result<SysMenuDTO> get(@PathVariable("id") Long id) {
        SysMenuDTO data = sysMenuService.get(id);

        return new Result<SysMenuDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        //判断是否有子菜单或按钮
        List<SysMenuDTO> list = sysMenuService.getListPid(id);
        if (list.size() > 0) {
            return new Result().error(ErrorCode.SUB_MENU_EXIST);
        }

        sysMenuService.delete(id);

        return new Result();
    }

    @GetMapping("select")
    @Operation(summary = "角色菜单权限")
    @RequiresPermissions("sys:menu:select")
    public Result<List<SysMenuDTO>> select() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, null);

        return new Result<List<SysMenuDTO>>().ok(list);
    }
}
