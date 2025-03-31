package com.losgai.gulimall.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GulimallSearchApplicationTests {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Test
    void contextLoads() {
        try {
            elasticsearchClient.info();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchQuery() throws IOException {
        // 定义索引名称（根据你的实际索引名称替换）
        String indexName = "bank";

        // 构造查询条件 (Match Query 示例)
        Query query = MatchQuery.of(m -> m
                .field("address") // 替换为你要查询的字段名
                .query("mill") // 替换为你要搜索的值
        )._toQuery();

        // 创建 SearchRequest
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(indexName)
                .query(query)
                .size(10)
        );

        // 执行查询并获取响应
        SearchResponse<Object> response = elasticsearchClient.search(searchRequest, Object.class);

        // 输出查询结果
        System.out.println("\n***Search Response:***");
        response.hits().hits().forEach(hit -> {
            System.out.println("Document ID: " + hit.id());
            System.out.println("Document Source: " + hit.source());
        });
    }

    // 定义索引名称
    private static final String INDEX_NAME = "student";

    // 创建索引
    @Test
    public void createIndex() throws IOException {
        // 检查索引是否存在
        boolean exists = elasticsearchClient.indices().exists(ExistsRequest.of(e -> e.index(INDEX_NAME))).value();
        if (!exists) {
            CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(c -> c.index(INDEX_NAME));
            Assertions.assertTrue(createIndexResponse.acknowledged(), "Failed to create index");
            System.out.println("Index created: " + true);
        } else {
            System.out.println("Index already exists.");
        }
    }

    // 插入数据
    @Test
    public void insertData() throws IOException {
        // 准备数据
        Student student1 = new Student("001", "Alice", "Female", 20);
        Student student2 = new Student("002", "Bob", "Male", 22);
        Student student3 = new Student("003", "Charlie", "Male", 21);

        // 插入数据
        IndexResponse response1 = elasticsearchClient.index(i -> i.index(INDEX_NAME).id(student1.getId()).document(student1));
        IndexResponse response2 = elasticsearchClient.index(i -> i.index(INDEX_NAME).id(student2.getId()).document(student2));
        IndexResponse response3 = elasticsearchClient.index(i -> i.index(INDEX_NAME).id(student3.getId()).document(student3));

        // 验证插入结果
        Assertions.assertEquals("created", response1.result().jsonValue());
        Assertions.assertEquals("created", response2.result().jsonValue());
        Assertions.assertEquals("created", response3.result().jsonValue());

        System.out.println("Inserted documents with IDs: " + response1.id() + ", " + response2.id() + ", " + response3.id());
    }

    // 定义学生实体类
    @Data
    public static class Student {
        // Getters and Setters
        private String id;       // 学号
        private String name;     // 姓名
        private String gender;   // 性别
        private int age;         // 年龄
        public Student(String id, String name, String gender, int age) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.age = age;
        }
    }

}
