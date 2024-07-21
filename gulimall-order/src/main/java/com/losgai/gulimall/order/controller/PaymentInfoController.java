package com.losgai.gulimall.order.controller;

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
import com.losgai.gulimall.order.dto.PaymentInfoDTO;
import com.losgai.gulimall.order.excel.PaymentInfoExcel;
import com.losgai.gulimall.order.service.PaymentInfoService;
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
 * 支付信息表
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@RestController
@RequestMapping("order/paymentinfo")
@Tag(name="支付信息表")
public class PaymentInfoController {
    @Autowired
    private PaymentInfoService paymentInfoService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("order:paymentinfo:page")
    public Result<PageData<PaymentInfoDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<PaymentInfoDTO> page = paymentInfoService.page(params);

        return new Result<PageData<PaymentInfoDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("order:paymentinfo:info")
    public Result<PaymentInfoDTO> get(@PathVariable("id") Long id){
        PaymentInfoDTO data = paymentInfoService.get(id);

        return new Result<PaymentInfoDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("order:paymentinfo:save")
    public Result save(@RequestBody PaymentInfoDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        paymentInfoService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("order:paymentinfo:update")
    public Result update(@RequestBody PaymentInfoDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        paymentInfoService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("order:paymentinfo:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        paymentInfoService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("order:paymentinfo:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PaymentInfoDTO> list = paymentInfoService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "支付信息表", list, PaymentInfoExcel.class);
    }

}
