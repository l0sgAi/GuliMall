package com.losgai.gulimall.search.service;

import com.losgai.gulimall.common.common.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ElasticSaveService {
    boolean saveToEs(List<SkuEsModel> skuEsModels) throws IOException;
}
