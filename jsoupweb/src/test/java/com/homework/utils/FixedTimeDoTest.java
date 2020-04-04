package com.homework.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class FixedTimeDoTest {

    @Autowired
    private FixedTimeDo fixedTimeDo;

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

}