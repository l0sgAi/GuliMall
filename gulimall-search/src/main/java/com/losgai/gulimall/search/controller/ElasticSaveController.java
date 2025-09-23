package com.losgai.gulimall.search.controller;

import com.losgai.gulimall.common.common.es.SkuEsModel;
import com.losgai.gulimall.common.common.exception.BaseCodeEnume;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.search.service.ElasticSaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search/save")
@Tag(name="ES文档保存")
@RequiredArgsConstructor
@Slf4j
public class ElasticSaveController {

    private final ElasticSaveService elasticSaveService;
    // 上架商品
    @PostMapping("/product")
    public Result productStatUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean b = false;
        try {
            b = elasticSaveService.saveToEs(skuEsModels);
        }catch (Exception e){
            log.error("商品上架错误：{}", e);
            return new Result().error(BaseCodeEnume.ES_PRODUCT_UP_EXCEPTION.getCode(),
                    BaseCodeEnume.ES_PRODUCT_UP_EXCEPTION.getMsg());
        }

        return b ? new Result() : new Result().error(BaseCodeEnume.ES_PRODUCT_UP_EXCEPTION.getCode());
    }
}
