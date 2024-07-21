/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.sys.controller;

import com.losgai.gulimall.common.common.annotation.LogOperation;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.common.common.validator.AssertUtils;
import com.losgai.gulimall.common.common.validator.ValidatorUtils;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.DefaultGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import com.losgai.gulimall.common.modules.sys.dto.SysDeptDTO;
import com.losgai.gulimall.common.modules.sys.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/dept")
@Tag(name = "部门管理")
@AllArgsConstructor
public class SysDeptController {
    private final SysDeptService sysDeptService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:dept:list")
    public Result<List<SysDeptDTO>> list() {
        List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));

        return new Result<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dept:info")
    public Result<SysDeptDTO> get(@PathVariable("id") Long id) {
        SysDeptDTO data = sysDeptService.get(id);

        return new Result<SysDeptDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dept:save")
    public Result save(@RequestBody SysDeptDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysDeptService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dept:update")
    public Result update(@RequestBody SysDeptDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDeptService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dept:delete")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        sysDeptService.delete(id);

        return new Result();
    }

}
