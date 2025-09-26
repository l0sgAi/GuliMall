package com.losgai.gulimall.product.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.losgai.gulimall.product.utils.RandomRedisCacheWriter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory, CacheProperties cacheProperties) {
        // 1. 配置序列化器
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 不存储类型信息，更通用，但反序列化时需要明确类型
        objectMapper.deactivateDefaultTyping();

        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 2. 构建默认缓存配置
        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        // 设置 Key 的序列化方式
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer)
                        )
                        // 设置 Value 的序列化方式
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
                        )
                        // 从配置文件读取默认过期时间（毫秒），并转换为 Duration 对象
                        .entryTtl(Duration.ofMillis(cacheProperties.getTtl().getDefault()));

        // 3. 构建特定缓存的配置
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheProperties.getTtl().getSpecific().forEach((cacheName, ttlInMillis) -> {
            // 为每个特定的 cache name 创建一个配置，继承默认配置并覆盖其 TTL
            RedisCacheConfiguration config = defaultConfig.entryTtl(Duration.ofMillis(ttlInMillis));
            cacheConfigs.put(cacheName, config);
        });

        // 4. 构建 RedisCacheManager
        return RedisCacheManager.builder(new RandomRedisCacheWriter(factory, 0.85, 1.15))
                .cacheDefaults(defaultConfig) // 应用默认配置
                .withInitialCacheConfigurations(cacheConfigs) // 应用特定缓存的配置
                .build();
    }

}
