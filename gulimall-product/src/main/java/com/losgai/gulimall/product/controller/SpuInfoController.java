package com.losgai.gulimall.product.controller;

import com.losgai.gulimall.common.common.annotation.LogOperation;
import com.losgai.gulimall.common.constant.Constant;
import com.losgai.gulimall.common.page.PageData;
import com.losgai.gulimall.common.common.utils.ExcelUtils;
import com.losgai.gulimall.common.utils.Result;
import com.losgai.gulimall.common.validator.AssertUtils;
import com.losgai.gulimall.common.validator.ValidatorUtils;
import com.losgai.gulimall.common.validator.group.AddGroup;
import com.losgai.gulimall.common.validator.group.DefaultGroup;
import com.losgai.gulimall.common.validator.group.UpdateGroup;
import com.losgai.gulimall.product.dto.SpuInfoDTO;
import com.losgai.gulimall.product.excel.SpuInfoExcel;
import com.losgai.gulimall.product.service.SpuInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * spu信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@RestController
@RequestMapping("product/spuinfo")
@Tag(name="spu信息")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("product:spuinfo:page")
    public Result<PageData<SpuInfoDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<SpuInfoDTO> page = spuInfoService.page(params);

        return new Result<PageData<SpuInfoDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("product:spuinfo:info")
    public Result<SpuInfoDTO> get(@PathVariable("id") Long id){
        SpuInfoDTO data = spuInfoService.get(id);

        return new Result<SpuInfoDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("product:spuinfo:save")
    public Result save(@RequestBody SpuInfoDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        spuInfoService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("product:spuinfo:update")
    public Result update(@RequestBody SpuInfoDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        spuInfoService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("product:spuinfo:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        spuInfoService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("product:spuinfo:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SpuInfoDTO> list = spuInfoService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "spu信息", list, SpuInfoExcel.class);
    }

}
