package com.changgou;

import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/4 9:40
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * 开启通用Mapper的包扫描
 * 包名：tk.mybatis.spring.annotation.MapperScan;
 */
@MapperScan(basePackages="com.changgou.goods.dao")
public class GoodsApplication {
    public static void main(String[] args)  {
        SpringApplication.run(GoodsApplication.class,args);
    }

    /***
     * IdWorker
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,0);
    }

}

