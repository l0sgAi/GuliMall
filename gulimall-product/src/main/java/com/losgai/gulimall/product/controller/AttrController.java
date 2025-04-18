package com.losgai.gulimall.product.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
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
import com.losgai.gulimall.product.dto.AttrDTO;
import com.losgai.gulimall.product.entity.AttrEntity;
import com.losgai.gulimall.product.entity.ProductAttrValueEntity;
import com.losgai.gulimall.product.excel.AttrExcel;
import com.losgai.gulimall.product.service.AttrService;
import com.losgai.gulimall.product.service.ProductAttrValueService;
import com.losgai.gulimall.product.vo.AttrVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@RestController
@RequestMapping("product/attr")
@Tag(name = "商品属性")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @GetMapping("/base/noRelation/page/{categoryId}")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "key", description = "搜索字符串", in = ParameterIn.QUERY, ref = "String") // 新增的查询参数
    })
    //@RequiresPermissions("product:attrgroup:page")
    public Result<PageData<AttrEntity>> pageBase(@Parameter(hidden = true) @RequestParam Map<String, Object> params,
                                                 @PathVariable("categoryId") long categoryId,
                                                 @RequestParam(value = "key", required = false) String key) {
        PageData<AttrEntity> page = attrService.queryBasePageByCatIdAndQuery(params, categoryId, key);
        return new Result<PageData<AttrEntity>>().ok(page);
    }

    @GetMapping("/sale/page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("product:attr:page")
    public Result<PageData<AttrVo>> pageSale(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<AttrVo> page = attrService.querySalePageByCatId(params, 0);
        return new Result<PageData<AttrVo>>().ok(page);
    }

    @GetMapping("/sale/page/{categoryId}")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "key", description = "搜索字符串", in = ParameterIn.QUERY, ref = "String") // 新增的查询参数
    })
    //@RequiresPermissions("product:attrgroup:page")
    public Result<PageData<AttrVo>> pageSaleCategory(@Parameter(hidden = true) @RequestParam Map<String, Object> params,
                                                     @PathVariable("categoryId") long categoryId,
                                                     @RequestParam(value = "key", required = false) String key) {
        if (StringUtils.isNotBlank(key)) {
            PageData<AttrVo> page = attrService.querySalePageByCatIdAndQuery(params, categoryId, key);

            return new Result<PageData<AttrVo>>().ok(page);
        }

        PageData<AttrVo> page = attrService.querySalePageByCatId(params, categoryId);
        return new Result<PageData<AttrVo>>().ok(page);
    }

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("product:attr:page")
    public Result<PageData<AttrVo>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<AttrVo> page = attrService.queryPageByCatId(params, 0);
        return new Result<PageData<AttrVo>>().ok(page);
    }

    @GetMapping("page/{categoryId}")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "key", description = "搜索字符串", in = ParameterIn.QUERY, ref = "String") // 新增的查询参数
    })
    //@RequiresPermissions("product:attrgroup:page")
    public Result<PageData<AttrVo>> pageCategory(@Parameter(hidden = true) @RequestParam Map<String, Object> params,
                                                 @PathVariable("categoryId") long categoryId,
                                                 @RequestParam(value = "key", required = false) String key) {
        if (StringUtils.isNotBlank(key)) {
            PageData<AttrVo> page = attrService.queryPageByCatIdAndQuery(params, categoryId, key);

            return new Result<PageData<AttrVo>>().ok(page);
        }

        PageData<AttrVo> page = attrService.queryPageByCatId(params, categoryId);
        return new Result<PageData<AttrVo>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("product:attr:info")
    public Result<AttrVo> get(@PathVariable("id") Long id) {
        AttrVo data = attrService.getVoById(id);

        return new Result<AttrVo>().ok(data);
    }

    @GetMapping("base/list/{spuId}")
    @Operation(summary = "获取spu对应的规格属性值")
    public Result<List<ProductAttrValueEntity>> getSpuBaseAttrs(@PathVariable("spuId") Long spuId) {
        List<ProductAttrValueEntity> data = productAttrValueService.getListBySpuId(spuId);

        return new Result<List<ProductAttrValueEntity>>().ok(data);
    }


    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("product:attr:save")
    public Result save(@Validated(value = {AddGroup.class}) @RequestBody AttrVo attrVo) {
        //效验数据
        ValidatorUtils.validateEntity(attrVo, AddGroup.class, DefaultGroup.class);

        attrService.saveBatch(attrVo);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("product:attr:update")
    public Result update(@Validated(value = {UpdateGroup.class}) @RequestBody AttrVo attrVo) {
        //效验数据
        ValidatorUtils.validateEntity(attrVo, UpdateGroup.class, DefaultGroup.class);

        attrService.updateBatch(attrVo);

        return new Result();
    }

    @PostMapping("/update/{spuId}")
    @Operation(summary = "修改spu规格参数")
    @LogOperation("修改spu规格")
    //@RequiresPermissions("product:attr:update")
    public Result updateAttrValueBySpuId(@PathVariable("spuId") Long spuId,
                                         @Validated(value = {UpdateGroup.class})
                                         @RequestBody List<ProductAttrValueEntity> values) {
        //效验数据
        ValidatorUtils.validateEntity(values, UpdateGroup.class, DefaultGroup.class);

        productAttrValueService.updateAttrValueBySpuId(values,spuId);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("product:attr:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        attrService.deleteBatch(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("product:attr:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AttrDTO> list = attrService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "商品属性", list, AttrExcel.class);
    }

}
