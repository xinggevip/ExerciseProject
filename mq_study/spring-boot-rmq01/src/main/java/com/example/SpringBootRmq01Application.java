package com.example;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class SpringBootRmq01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRmq01Application.class, args);
    }

}
