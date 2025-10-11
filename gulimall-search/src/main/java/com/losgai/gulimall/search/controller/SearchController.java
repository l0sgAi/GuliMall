package com.losgai.gulimall.search.controller;

import com.losgai.gulimall.common.common.es.SkuEsModel;
import com.losgai.gulimall.common.common.exception.BaseCodeEnume;
import com.losgai.gulimall.common.common.utils.Result;
import com.losgai.gulimall.search.service.ElasticSaveService;
import com.losgai.gulimall.search.service.MallSearchService;
import com.losgai.gulimall.search.vo.SearchParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/list")
@Tag(name="全局搜索")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final MallSearchService mallSearchService;
    // 上架商品
    @GetMapping("/get")
    public Result productStatUp(@RequestBody SearchParam searchParam){
        Object result = mallSearchService.search(searchParam);
        return new Result().ok(result);
    }
}
