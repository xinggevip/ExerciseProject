package com.homework.task;

import com.homework.utils.FixedTimeDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling  // 标识这是一个定时任务类
public class MyTask {
    @Autowired
    private FixedTimeDo fixedTimeDo;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void getDate() {
        try {
            // 刷新持久化全国概览数据
            fixedTimeDo.getStatisticsService();
            // 刷新持久化全国详情数据
            fixedTimeDo.getAreaStat();
            // 刷新持久化国内资讯
            fixedTimeDo.getTimeline();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

}
