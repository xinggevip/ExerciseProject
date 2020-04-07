package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedTimeDoService {

    @Scheduled(cron = "0/2 * * * * ?")
    public void fixedTimeDo() {
        System.out.println("处理定时任务...");
    }
}
