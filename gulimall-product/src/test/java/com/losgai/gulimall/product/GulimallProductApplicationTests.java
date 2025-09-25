package com.losgai.gulimall.product;

import com.losgai.gulimall.product.dto.BrandDTO;
import com.losgai.gulimall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    // 测试Redis
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 测试Redisson
    @Autowired
    private RedissonClient redissonClient;

    @Test
    void testRedisson() {
        // 1. 获取一把锁
        RLock lock = redissonClient.getLock("my-lock");

        // 2. 加锁
        lock.lock();
        try{
            log.info("加锁成功，执行业务");
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 3. 解锁
            lock.unlock();
        }
    }

    @Test
    void testRedisConnection() {
        try {
            // 测试Redis连接，设置一个键值对
            redisTemplate.opsForValue().set("test_key", "test_value");

            // 获取刚才设置的值
            String value = (String) redisTemplate.opsForValue().get("test_key");

            Assertions.assertNotNull(value);
            if(value.equals("test_value")){
                log.info("Redis连接测试成功");
            }

            // 删除测试键
            redisTemplate.delete("test_key");


            log.info("Redis连接测试成功");
        } catch (Exception e) {
            log.error("Redis连接测试失败: {}", e.getMessage());
        }
    }

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

    @Test
    void aliOss(){

    }
}

