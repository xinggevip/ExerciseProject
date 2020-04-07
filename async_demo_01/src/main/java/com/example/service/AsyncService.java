package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    
    public void getUser() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println("数据正在处理...");
    }
}
