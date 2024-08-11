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
import com.losgai.gulimall.product.entity.AttrGroupEntity;
import com.losgai.gulimall.product.excel.AttrExcel;
import com.losgai.gulimall.product.service.AttrService;
import com.losgai.gulimall.product.vo.AttrVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletResponse;
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
@Tag(name="商品属性")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("product:attr:page")
    public Result<PageData<AttrVo>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<AttrVo> page = attrService.queryPageByCatId(params,0);
        return new Result<PageData<AttrVo>>().ok(page);
    }

    @GetMapping("page/{categoryId}")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String"),
            @Parameter(name = "key", description = "搜索字符串", in = ParameterIn.QUERY, ref="String") // 新增的查询参数
    })
    //@RequiresPermissions("product:attrgroup:page")
    public Result<PageData<AttrVo>> pageCategory(@Parameter(hidden = true) @RequestParam Map<String, Object> params,
                                                 @PathVariable("categoryId") long categoryId,
                                                 @RequestParam(value = "key", required = false) String key){
        if (StringUtils.isNotBlank(key)) {
            PageData<AttrVo> page = attrService.queryPageByCatIdAndQuery(params,categoryId,key);

            return new Result<PageData<AttrVo>>().ok(page);
        }

        PageData<AttrVo> page = attrService.queryPageByCatId(params,categoryId);
        return new Result<PageData<AttrVo>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("product:attr:info")
    public Result<AttrVo> get(@PathVariable("id") Long id){
        AttrVo data = attrService.getVoById(id);

        return new Result<AttrVo>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("product:attr:save")
    public Result save(@RequestBody AttrDTO dto){
        //TODO: 改造为多表插入
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        attrService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("product:attr:update")
    public Result update(@RequestBody AttrDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        attrService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("product:attr:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        attrService.delete(ids);

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
