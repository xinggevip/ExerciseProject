package com.homework.service;

import com.homework.domain.Province;
import com.homework.domain.Timeline;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CommonService {
    // 获取全国详情数据
    List<Province> getAllAreaStat() throws IOException;
    // 获取国内资讯
    List<Timeline> getAllTimeline();

    Map<String, Object> getStatisticsMap() throws Exception;
}
