package com.losgai.gulimall.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import com.alibaba.fastjson2.JSON;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    void getAggregation() throws IOException {
        // 定义索引名称（根据你的实际索引名称替换）
        String indexName = "bank";

        SearchResponse<Object> response = elasticsearchClient.search(b -> b
                        .index(indexName)
                        .query(q -> q.matchAll(m -> m)) // 查询所有文档
                        .aggregations("age_type", a -> a
                                .terms(t -> t
                                        .field("age") // 按年龄分组
                                        .size(100)    // 最大分组数
                                )
                                .aggregations("balance_avg", agg -> agg
                                        .avg(avg -> avg.field("balance")) // 计算每组的平均余额
                                )
                                .aggregations("gender_agg", agg -> agg
                                        .terms(t -> t.field("gender.keyword")) // 按性别分组
                                        .aggregations("gender_balance_avg", genderAgg -> genderAgg
                                                .avg(avg -> avg.field("balance")) // 计算每个性别的平均余额
                                        )
                                )
                        ),
                Object.class
        );

        Map<String, Aggregate> aggregations = response.aggregations();

        HitsMetadata<Object> hits = response.hits();
        hits.hits().forEach(hit -> {
            String string = hit.toString();
            System.out.println("String = " + string);
            String id = hit.id();
            System.out.println("Document ID: " + id);
            Object source = hit.source();
            // 将 source 转换为 JSON 字符串
            String jsonString = JSON.toJSONString(source);

            // 使用 FastJSON 将 JSON 字符串映射为实体类
            BankAccount bankAccount = JSON.parseObject(jsonString, BankAccount.class);
            System.out.println("bankAccount = " + bankAccount);
            String string2 = hit.toString();
            System.out.println("string2_hit = " + string2);
            if (source != null) {
                String string1 = source.toString();
                System.out.println("string_source = " + string1);
            }
            System.out.println("Document Source: " + source);
            Double score = hit.score();
            System.out.println("Score = " + score);
        });

        System.out.println("aggregations = " + aggregations);
        aggregations.forEach(
                (k,v)->{
                    System.out.println("aggregations_k = " + k);
                    System.out.println("aggregations_v = " + v);
                    Object o = v._get();
                    System.out.println("o = " + o);
                }
        );

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

    // 搜索数据
    @Test
    public void searchData() throws IOException {
        // 构建查询条件
        Query query = BoolQuery.of(b -> b
                .must(MatchQuery.of(m -> m.field("gender").query("M"))._toQuery()) // 必须匹配 gender=M
                .must(MatchQuery.of(m -> m.field("address").query("mill"))._toQuery()) // 必须匹配 address=mill
                .mustNot(MatchQuery.of(m -> m.field("age").query("18"))._toQuery()) // 必须不匹配 age=18
                .should(MatchQuery.of(m -> m.field("firstname").query("Parker"))._toQuery()) // 可选匹配 firstname=Parker
        )._toQuery();

        // 构建搜索请求
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("bank") // 替换为你的索引名称
                .query(query)
        );

        // 执行搜索请求
        SearchResponse<Object> searchResponse = elasticsearchClient.search(searchRequest, Object.class);

        // 打印查询结果
        if (searchResponse.hits().total() != null) {
            System.out.println("Total Hits: " + searchResponse.hits().total().value());
        }
        List<Hit<Object>> hits = searchResponse.hits().hits();
        for (Hit<Object> hit : hits) {
            System.out.println("Document ID: " + hit.id());
            System.out.println("Document Source: " + hit.source());
        }
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

    @Data
    public class BankAccount {

        private int accountNumber; // 账户编号
        private double balance;    // 余额
        private String firstname;  // 名字
        private String lastname;   // 姓氏
        private int age;           // 年龄
        private String gender;     // 性别
        private String address;    // 地址
        private String employer;   // 雇主
        private String email;      // 邮箱
        private String city;       // 城市
        private String state;      // 州
    }

}
