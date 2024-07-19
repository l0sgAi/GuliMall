package com.losgai.gulimall.coupon.controller;

import com.losgai.gulimall.common.annotation.LogOperation;
import com.losgai.gulimall.common.constant.Constant;
import com.losgai.gulimall.common.page.PageData;
import com.losgai.gulimall.common.utils.ExcelUtils;
import com.losgai.gulimall.common.utils.Result;
import com.losgai.gulimall.common.validator.AssertUtils;
import com.losgai.gulimall.common.validator.ValidatorUtils;
import com.losgai.gulimall.common.validator.group.AddGroup;
import com.losgai.gulimall.common.validator.group.DefaultGroup;
import com.losgai.gulimall.common.validator.group.UpdateGroup;
import com.losgai.gulimall.coupon.dto.CouponDTO;
import com.losgai.gulimall.coupon.entity.CouponEntity;
import com.losgai.gulimall.coupon.excel.CouponExcel;
import com.losgai.gulimall.coupon.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 优惠券信息
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon")
@Tag(name="优惠券信息")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("coupon:coupon:page")
    public Result<PageData<CouponDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<CouponDTO> page = couponService.page(params);

        return new Result<PageData<CouponDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("coupon:coupon:info")
    public Result<CouponDTO> get(@PathVariable("id") Long id){
        CouponDTO data = couponService.get(id);

        return new Result<CouponDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("coupon:coupon:save")
    public Result save(@RequestBody CouponDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        couponService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("coupon:coupon:update")
    public Result update(@RequestBody CouponDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        couponService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("coupon:coupon:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        couponService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("coupon:coupon:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CouponDTO> list = couponService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "优惠券信息", list, CouponExcel.class);
    }

    // TODO: 配置中心与Feign测试

    /*@RequestMapping("/member/list")
    public Result memberCoupon(){
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100减10");
        return new Result().ok(Arrays.asList(couponEntity));
    }*/

    /*@Value("${test_user.name}")
    String username;
    @Value("${test_user.age}")
    Integer age;
    @GetMapping("test")
    public Result test(){
        return new Result().ok(username + ":" + age);
    }*/

}
