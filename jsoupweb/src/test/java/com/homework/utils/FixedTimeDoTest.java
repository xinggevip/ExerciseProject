package com.homework.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

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

}