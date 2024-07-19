package com.losgai.gulimall.member.controller;

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
import com.losgai.gulimall.coupon.entity.CouponEntity;
import com.losgai.gulimall.member.dto.MemberDTO;
import com.losgai.gulimall.member.entity.MemberEntity;
import com.losgai.gulimall.member.excel.MemberExcel;
import com.losgai.gulimall.member.feign.CouponFeignService;
import com.losgai.gulimall.member.service.MemberService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 会员
 *
 * @author losgai 356138661@qq.com
 * @since 1.0.0 2024-07-17
 */
@RestController
@RequestMapping("member/member")
@Tag(name="会员")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CouponFeignService couponFeignService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
        @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true, ref="int") ,
        @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY,required = true, ref="int") ,
        @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY, ref="String") ,
        @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY, ref="String")
    })
    @RequiresPermissions("member:member:page")
    public Result<PageData<MemberDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<MemberDTO> page = memberService.page(params);

        return new Result<PageData<MemberDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    //@RequiresPermissions("member:member:info")
    public Result<MemberDTO> get(@PathVariable("id") Long id){
        MemberDTO data = memberService.get(id);

        return new Result<MemberDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    //@RequiresPermissions("member:member:save")
    public Result save(@RequestBody MemberDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        memberService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("member:member:update")
    public Result update(@RequestBody MemberDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        memberService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    //@RequiresPermissions("member:member:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        memberService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    //@RequiresPermissions("member:member:export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<MemberDTO> list = memberService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "会员", list, MemberExcel.class);
    }

    // TODO： 测试Feign

    /*@RequestMapping("feign/coupons")
    public Result test(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("张叁");
        Result memberCoupons = couponFeignService.memberCoupon();
        List<CouponEntity> coupons = (List<CouponEntity>)memberCoupons.getData();
        HashMap<MemberEntity,List<CouponEntity>> resultHashMap= new HashMap<>();
        resultHashMap.put(memberEntity,coupons);
        return new Result().ok(resultHashMap);
    }*/

}
