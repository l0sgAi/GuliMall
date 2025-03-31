package com.losgai.gulimall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GulimallSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallSearchApplication.class, args);
    }

}
