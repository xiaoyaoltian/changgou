package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import com.sun.org.apache.bcel.internal.generic.Select;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/9 11:26
 */
@FeignClient(value="goods")
@RequestMapping("/sku")
public interface SkuFeign {

    @GetMapping
    Result<List<Sku>> findAll();
    /**
     * 查询符合条件的状态的SKU的列表
     * @param status
     * @return
     *//*
    @GetMapping("/status/{status}")
    public Result<List<Sku>> findByStatus(@PathVariable(name="status") String status);*/
}
