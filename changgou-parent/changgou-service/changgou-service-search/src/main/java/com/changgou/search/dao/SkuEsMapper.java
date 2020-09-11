package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/9 12:47
 */
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo, Long> {

}
