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
import com.losgai.gulimall.ware.dto.WmsWareSkuDTO;
import com.losgai.gulimall.ware.entity.WmsWareSkuEntity;
import com.losgai.gulimall.ware.excel.WmsWareSkuExcel;
import com.losgai.gulimall.ware.service.WmsWareSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 商品库存
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@RestController
@RequestMapping("ware/wmswaresku")
@Tag(name = "商品库存")
public class WmsWareSkuController {
    @Autowired
    private WmsWareSkuService wmsWareSkuService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "wareId", description = "仓库", in = ParameterIn.QUERY, ref = "Long"),
            @Parameter(name = "skuId", description = "skuId", in = ParameterIn.QUERY, ref = "Long")

    })
    @RequiresPermissions("ware:wmswaresku:page")
    public Result<PageData<WmsWareSkuEntity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params,
                                                   @RequestParam(required = false) Long wareId,
                                                   @RequestParam(required = false) Long skuId) {
        PageData<WmsWareSkuEntity> page = wmsWareSkuService.pageQuery(params, wareId, skuId);
        page.setTotal(page.getList().size());
        return new Result<PageData<WmsWareSkuEntity>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("ware:wmswaresku:info")
    public Result<WmsWareSkuDTO> get(@PathVariable("id") Long id) {
        WmsWareSkuDTO data = wmsWareSkuService.get(id);

        return new Result<WmsWareSkuDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("ware:wmswaresku:save")
    public Result save(@RequestBody WmsWareSkuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wmsWareSkuService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("ware:wmswaresku:update")
    public Result update(@RequestBody WmsWareSkuDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wmsWareSkuService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("ware:wmswaresku:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wmsWareSkuService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("ware:wmswaresku:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WmsWareSkuDTO> list = wmsWareSkuService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "商品库存", list, WmsWareSkuExcel.class);
    }

}
