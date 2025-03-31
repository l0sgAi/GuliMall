package com.losgai.gulimall.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 1.导入依赖
* 2.编写配置
* 3.使用ElasticsearchClient
* */
@Configuration
public class GulimallESConfig {

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // 连接到 Elasticsearch 服务器
        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.200.132", 9200, "http")
        ).build();

        // 使用 Jackson 进行 JSON 处理
        RestClientTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper()
        );

        // 创建 ElasticsearchClient
        return new ElasticsearchClient(transport);
    }

}
