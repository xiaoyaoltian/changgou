package com.changgou.search.service;

import java.util.Map;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/9 11:32
 */
public interface SkuService {

    /***
     * 搜索
     * @param searchMap
     * @return
     */
//    Map search(Map<String, String> searchMap);

    /**
     * 导入数据到索引库中
     */
    void importData();
}
