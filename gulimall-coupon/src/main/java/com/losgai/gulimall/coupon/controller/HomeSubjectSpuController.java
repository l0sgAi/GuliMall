package com.losgai.gulimall.coupon.controller;

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
import com.losgai.gulimall.coupon.dto.HomeSubjectSpuDTO;
import com.losgai.gulimall.coupon.excel.HomeSubjectSpuExcel;
import com.losgai.gulimall.coupon.service.HomeSubjectSpuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 专题商品
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@RestController
@RequestMapping("coupon/homesubjectspu")
@Tag(name="专题商品")
public class HomeSubjectSpuController {
    @Autowired
    private HomeSubjectSpuService homeSubjectSpuService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("coupon:homesubjectspu:page")
    public Result<PageData<HomeSubjectSpuDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<HomeSubjectSpuDTO> page = homeSubjectSpuService.page(params);

        return new Result<PageData<HomeSubjectSpuDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("coupon:homesubjectspu:info")
    public Result<HomeSubjectSpuDTO> get(@PathVariable("id") Long id){
        HomeSubjectSpuDTO data = homeSubjectSpuService.get(id);

        return new Result<HomeSubjectSpuDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("coupon:homesubjectspu:save")
    public Result save(@RequestBody HomeSubjectSpuDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        homeSubjectSpuService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("coupon:homesubjectspu:update")
    public Result update(@RequestBody HomeSubjectSpuDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        homeSubjectSpuService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("coupon:homesubjectspu:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        homeSubjectSpuService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("coupon:homesubjectspu:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<HomeSubjectSpuDTO> list = homeSubjectSpuService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "专题商品", list, HomeSubjectSpuExcel.class);
    }

}
