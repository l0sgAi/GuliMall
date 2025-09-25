package com.losgai.gulimall.product;

import com.losgai.gulimall.product.entity.CategoryEntity;
import com.losgai.gulimall.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 分类缓存功能测试类
 */
@SpringBootTest
public class CategoryCacheTest {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    private static final String CATEGORY_CACHE_KEY = "product:category:tree";
    
    /**
     * 测试分类树形结构获取（第一次从数据库获取并缓存）
     */
    @Test
    public void testListWithTreeFirstTime() {
        // 清除缓存
        stringRedisTemplate.delete(CATEGORY_CACHE_KEY);
        
        // 第一次调用，应该从数据库获取并缓存
        List<CategoryEntity> treeList = categoryService.listWithTree();
        
        assertNotNull(treeList);
        System.out.println("第一次获取分类数据，数据条数：" + treeList.size());
        
        // 检查缓存是否存在
        String cacheData = stringRedisTemplate.opsForValue().get(CATEGORY_CACHE_KEY);
        assertNotNull(cacheData);
        System.out.println("缓存已创建");
    }
    
    /**
     * 测试从缓存获取分类数据
     */
    @Test
    public void testListWithTreeFromCache() {
        // 确保缓存存在
        categoryService.listWithTree();
        
        // 第二次调用，应该从缓存获取
        long startTime = System.currentTimeMillis();
        List<CategoryEntity> treeList = categoryService.listWithTree();
        long endTime = System.currentTimeMillis();
        
        assertNotNull(treeList);
        System.out.println("从缓存获取分类数据，耗时：" + (endTime - startTime) + "ms");
        System.out.println("数据条数：" + treeList.size());
    }
    
    /**
     * 测试缓存过期时间
     */
    @Test
    public void testCacheExpireTime() {
        // 清除缓存
        stringRedisTemplate.delete(CATEGORY_CACHE_KEY);
        
        // 获取数据，触发缓存
        categoryService.listWithTree();
        
        // 获取缓存的过期时间
        Long ttl = stringRedisTemplate.getExpire(CATEGORY_CACHE_KEY);
        assertNotNull(ttl);
        assertTrue(ttl > 0);
        System.out.println("缓存过期时间（秒）：" + ttl);
    }
}