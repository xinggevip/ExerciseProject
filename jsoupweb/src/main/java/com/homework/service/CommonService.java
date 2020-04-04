package com.homework.service;

import com.homework.domain.Province;
import com.homework.domain.Statistics;
import com.homework.domain.Timeline;

import java.io.IOException;
import java.util.List;

public interface CommonService {
    // 获取全国概览数据
    Statistics getAllStatistics();
    // 获取全国详情数据
    List<Province> getAllAreaStat() throws IOException;
    // 获取国内资讯
    List<Timeline> getAllTimeline();
}
