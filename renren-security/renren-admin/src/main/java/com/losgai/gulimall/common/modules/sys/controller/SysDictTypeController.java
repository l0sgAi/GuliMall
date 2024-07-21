/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.losgai.gulimall.common.modules.sys.controller;

import com.losgai.gulimall.common.common.annotation.LogOperation;
import com.losgai.gulimall.common.common.constant.Constant;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.common.common.validator.AssertUtils;
import com.losgai.gulimall.common.common.validator.ValidatorUtils;
import com.losgai.gulimall.common.common.validator.group.DefaultGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import com.losgai.gulimall.common.modules.sys.dto.SysDictTypeDTO;
import com.losgai.gulimall.common.modules.sys.entity.DictType;
import com.losgai.gulimall.common.modules.sys.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典类型
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/dict/type")
@Tag(name = "字典类型")
@AllArgsConstructor
public class SysDictTypeController {
    private final SysDictTypeService sysDictTypeService;

    @GetMapping("page")
    @Operation(summary = "字典类型")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictType", description = "字典类型", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictName", description = "字典名称", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:dict:page")
    public Result<PageData<SysDictTypeDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //字典类型
        PageData<SysDictTypeDTO> page = sysDictTypeService.page(params);

        return new Result<PageData<SysDictTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dict:info")
    public Result<SysDictTypeDTO> get(@PathVariable("id") Long id) {
        SysDictTypeDTO data = sysDictTypeService.get(id);

        return new Result<SysDictTypeDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dict:save")
    public Result save(@RequestBody SysDictTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dict:update")
    public Result update(@RequestBody SysDictTypeDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dict:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysDictTypeService.delete(ids);

        return new Result();
    }

    @GetMapping("all")
    @Operation(summary = "所有字典数据")
    public Result<List<DictType>> all() {
        List<DictType> list = sysDictTypeService.getAllList();

        return new Result<List<DictType>>().ok(list);
    }

}
