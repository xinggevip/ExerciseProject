package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.dao")
@EnableCaching  // 开启缓存
public class SpringBootCache01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCache01Application.class, args);
    }

}
