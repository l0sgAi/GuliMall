package com.losgai.gulimall.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存配置属性类
 * <p>
 * 从 application.yml 文件中读取 app.cache 前缀的配置
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.cache")
public class CacheProperties {

    private Ttl ttl = new Ttl();

    public static class Ttl {
        /**
         * 默认缓存过期时间（单位：毫秒）
         */
        private Long Default = 3600000L; // 默认为 1 小时

        /**
         * 特定缓存的过期时间配置，Key 为 cache name, Value 为过期时间（单位：毫秒）
         */
        private Map<String, Long> specific = new HashMap<>();

        // --- Getters and Setters ---

        public Long getDefault() {
            return Default;
        }

        public void setDefault(Long aDefault) {
            Default = aDefault;
        }

        public Map<String, Long> getSpecific() {
            return specific;
        }

        public void setSpecific(Map<String, Long> specific) {
            this.specific = specific;
        }
    }
}