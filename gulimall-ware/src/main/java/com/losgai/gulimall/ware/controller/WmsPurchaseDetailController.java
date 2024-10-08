package com.losgai.gulimall.ware.controller;

import com.losgai.gulimall.common.common.annotation.LogOperation;
import com.losgai.gulimall.common.common.constant.Constant;
import com.losgai.gulimall.common.common.page.PageData;
import com.losgai.gulimall.common.common.utils.ExcelUtils;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.common.common.validator.AssertUtils;
import com.losgai.gulimall.common.common.validator.ValidatorUtils;
import com.losgai.gulimall.common.common.validator.group.AddGroup;
import com.losgai.gulimall.common.common.validator.group.DefaultGroup;
import com.losgai.gulimall.common.common.validator.group.UpdateGroup;
import com.losgai.gulimall.ware.dto.WmsPurchaseDetailDTO;
import com.losgai.gulimall.ware.entity.WmsPurchaseDetailEntity;
import com.losgai.gulimall.ware.excel.WmsPurchaseDetailExcel;
import com.losgai.gulimall.ware.service.WmsPurchaseDetailService;
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
 * 
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@RestController
@RequestMapping("ware/wmspurchasedetail")
@Tag(name="")
public class WmsPurchaseDetailController {

    @Autowired
    private WmsPurchaseDetailService wmsPurchaseDetailService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("ware:wmspurchasedetail:page")
    public Result<PageData<WmsPurchaseDetailEntity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<WmsPurchaseDetailEntity> page = wmsPurchaseDetailService.pageQuery(params);
        page.setTotal(page.getList().size());
        return new Result<PageData<WmsPurchaseDetailEntity>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("ware:wmspurchasedetail:info")
    public Result<WmsPurchaseDetailDTO> get(@PathVariable("id") Long id){
        WmsPurchaseDetailDTO data = wmsPurchaseDetailService.get(id);

        return new Result<WmsPurchaseDetailDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("ware:wmspurchasedetail:save")
    public Result save(@RequestBody WmsPurchaseDetailDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wmsPurchaseDetailService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("ware:wmspurchasedetail:update")
    public Result update(@RequestBody WmsPurchaseDetailDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wmsPurchaseDetailService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("ware:wmspurchasedetail:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wmsPurchaseDetailService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("ware:wmspurchasedetail:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WmsPurchaseDetailDTO> list = wmsPurchaseDetailService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "", list, WmsPurchaseDetailExcel.class);
    }

}
