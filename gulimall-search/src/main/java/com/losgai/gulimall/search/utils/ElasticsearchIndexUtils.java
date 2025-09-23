package com.losgai.gulimall.search.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ElasticsearchIndexUtils {

    public static void createIndex(String indexName, ElasticsearchClient esClient) throws IOException {
        // 1.判断是否有索引
        ExistsRequest existsRequest = new ExistsRequest.Builder()
                .index(indexName)
                .build();

        boolean value = esClient.indices().exists(existsRequest).value();
        if (!value) {
            log.info("索引不存在，创建索引:{}", indexName);
            CreateIndexRequest createIndexRequest = CreateIndexRequest.of(builder ->
                    builder.index(indexName)
            );
            esClient.indices().create(createIndexRequest);
        }
    }

}