package com.homework.utils;

import com.homework.dao.StatisticsMapper;
import com.homework.domain.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class FixedTimeDoTest {

    @Autowired
    private FixedTimeDo fixedTimeDo;

    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 全国疫情数据
     * @throws IOException
     */
    @Test
    void jsoupTest() throws IOException {
        fixedTimeDo.getAreaStat();
    }

    /**
     * 概览数据
     * @throws IOException
     */
    @Test
    void test2() throws IOException {
        fixedTimeDo.getStatisticsService();
    }

    /**
     * 国内资讯
     * @throws IOException
     */
    @Test
    void test3() throws IOException {
        fixedTimeDo.getTimeline();
    }

    /**
     * 数组遍历删除测试
     */
    @Test
    void listTest() {
        ArrayList<String> list = new ArrayList<String>(3);
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("C");
        list.add("D");
        list.add("E");

        for (String s : list) {

        }

    }

    /**
     * 时间处理
     */

    @Test
    void thanTimeTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("计算两个时间的差：");

        Statistics statistics = statisticsMapper.selectLastRow();
        LocalDateTime end = statistics.getModifyTime();
//        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(now,end);
        long days = duration.toDays(); //相差的天数
        log.info("相差的天数 = {}",days);
        long hours = duration.toHours();//相差的小时数
        log.info("相差的小时数 = {}",hours);
        long minutes = duration.toMinutes();//相差的分钟数
        log.info("相差的分钟数 = {}",minutes);
        long millis = duration.toMillis();//相差毫秒数
        log.info("相差毫秒数 = {}",millis);
        long nanos = duration.toNanos();//相差的纳秒数
        log.info("相差的纳秒数 = {}",nanos);

        System.out.println(now);
        System.out.println(end);
    }

    @Test
    void getTwoData() throws IllegalAccessException {
        List<Statistics> list = statisticsMapper.selectTwoData();
        for (Statistics statistics : list) {
            System.out.println(statistics);
        }
        Map<String, Object> map = CommonUtils.objectToMap(list.get(0));
        System.out.println(map);
    }

}