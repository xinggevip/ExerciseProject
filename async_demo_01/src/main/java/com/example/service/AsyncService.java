package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    // 告诉spring这是一个异步方法
    @Async
    public void getUser() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println("数据正在处理...");
    }
}
