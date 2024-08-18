package com.losgai.gulimall.product.controller;

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
import com.losgai.gulimall.product.dto.BrandDTO;
import com.losgai.gulimall.product.entity.BrandEntity;
import com.losgai.gulimall.product.entity.CategoryBrandRelationEntity;
import com.losgai.gulimall.product.excel.BrandExcel;
import com.losgai.gulimall.product.service.BrandService;
import com.losgai.gulimall.product.service.CategoryBrandRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 品牌
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-16
 */
@RestController
@RequestMapping("product/brand")
@Tag(name = "品牌")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "key", description = "搜索字符串", in = ParameterIn.QUERY, ref = "String") // 新增的查询参数
    })
    //@RequiresPermissions("product:brand:page")
    public Result<PageData<BrandEntity>> pageQuery(@Parameter(hidden = true) @RequestParam Map<String, Object> params,
                                                   @RequestParam(value = "key", required = false) String key) {
        PageData<BrandEntity> page = brandService.queryPage(params, key);
        return new Result<PageData<BrandEntity>>().ok(page);
    }

    @GetMapping("/getCategoryRelation/{brandId}")
    @Operation(summary = "分类关系")
    public Result<List<CategoryBrandRelationEntity>> getCategoryRelation(@PathVariable("brandId") Long brandId) {
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.getCategoryRelation(brandId);
        return new Result<List<CategoryBrandRelationEntity>>().ok(list);
    }

    @GetMapping("/getCategoryBrand/{catId}")
    @Operation(summary = "分类品牌")
    public Result<List<BrandEntity>> getCategoryBrand(@PathVariable("catId") Long catId) {
        List<BrandEntity> list = brandService.getCategoryBrand(catId);
        return new Result<List<BrandEntity>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("product:brand:info")
    public Result<BrandDTO> get(@PathVariable("id") Long id) {
        BrandDTO data = brandService.get(id);

        return new Result<BrandDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("product:brand:save")
    public Result save(@Validated(value = {AddGroup.class}) @RequestBody BrandDTO dto) {
        //效验数据
        //ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        brandService.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("product:brand:update")
    public Result update(@Validated(value = {UpdateGroup.class}) @RequestBody BrandEntity brandEntity) {
        //效验数据
        ValidatorUtils.validateEntity(brandEntity, UpdateGroup.class, DefaultGroup.class);

//        brandService.update(dto);
        brandService.doBatchUpdate(brandEntity);

        return new Result();
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("product:brand:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        brandService.deleteBatchIds(Arrays.asList(ids));

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("product:brand:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BrandDTO> list = brandService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "品牌", list, BrandExcel.class);
    }

}
