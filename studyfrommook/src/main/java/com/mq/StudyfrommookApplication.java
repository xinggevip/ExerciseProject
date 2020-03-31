package com.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mq.dao")
public class StudyfrommookApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyfrommookApplication.class, args);
    }

}
