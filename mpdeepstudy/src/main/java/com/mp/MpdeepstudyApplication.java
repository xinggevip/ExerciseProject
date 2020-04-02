package com.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mp.dao")
public class MpdeepstudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpdeepstudyApplication.class, args);
    }

}
