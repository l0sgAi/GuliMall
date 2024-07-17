package com.losgai.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.losgai.gulimall.product.dto.BrandDTO;
import com.losgai.gulimall.product.entity.BrandEntity;
import com.losgai.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("Vivo");
//        brandService.insert(brandEntity);
//        System.out.println("保存成功...");
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "vivo");
        List<BrandDTO> list = brandService.list(params);
        System.out.println(list);
    }
}
