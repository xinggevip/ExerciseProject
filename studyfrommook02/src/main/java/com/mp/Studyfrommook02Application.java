package com.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mp.dao")
public class Studyfrommook02Application {

    public static void main(String[] args) {
        SpringApplication.run(Studyfrommook02Application.class, args);
    }

}
