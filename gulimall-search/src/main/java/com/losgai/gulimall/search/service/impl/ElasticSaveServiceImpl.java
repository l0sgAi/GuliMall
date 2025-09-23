package com.losgai.gulimall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ErrorCause;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.losgai.gulimall.common.common.es.SkuEsModel;
import com.losgai.gulimall.search.service.ElasticSaveService;
import com.losgai.gulimall.search.utils.ElasticsearchIndexUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.losgai.gulimall.search.constant.EsConstants.INDEX_NAME_PRODUCT;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElasticSaveServiceImpl implements ElasticSaveService {

    private final ElasticsearchClient esClient;

    @Override
    public boolean saveToEs(List<SkuEsModel> skuEsModels) throws IOException {

        // 1.在es中建立对应的索引和映射关系(见resource/product_mapping)
        // 2.保存数据至ES

        // 2.1.工具类判断是否有索引，没有则创建
        ElasticsearchIndexUtils.createIndex(INDEX_NAME_PRODUCT, esClient);

        // 2.2.插入一系列文档
        // 封装成BulkOperation对象
        List<BulkOperation> bulkOperations = skuEsModels.stream()
                .map(item ->
                        BulkOperation.of(builder ->
                                builder.index(i -> i.index(INDEX_NAME_PRODUCT)
                                        .document(item)
                                        .id(String.valueOf(item.getSkuId())))))
                .toList();

        // 使用bulk方法执行批量操作并获得响应
        BulkResponse response = esClient.bulk(e ->
                e.index(INDEX_NAME_PRODUCT)
                        .operations(bulkOperations));

        if (response.errors()) {
            List<ErrorCause> list = response.items().stream().map(BulkResponseItem::error).toList();
            log.error("ES批量操作-有错误发生:{}", list);
            return false;
        }

        log.info("ES批量插入操作完成，耗时{}毫秒", response.took());
        return true;
    }
}
