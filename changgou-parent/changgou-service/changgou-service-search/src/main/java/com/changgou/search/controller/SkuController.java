package com.changgou.search.controller;

import com.changgou.search.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/9 12:58
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/search")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 搜索
     * @param searchMap
     * @return
     */
   /* @GetMapping
    public Map search(@RequestParam(required = false) Map<String,String> searchMap)throws  Exception{
        return  skuService.search(searchMap);
    }*/
    /**
     * 导入数据
     * @return
     */
    @GetMapping(value = "/import")
    public Result importData(){
        skuService.importData();
        return new Result(true, StatusCode.OK,"导入数据到索引库中成功！");
    }
}
