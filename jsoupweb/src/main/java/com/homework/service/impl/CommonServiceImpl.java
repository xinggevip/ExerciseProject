package com.homework.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.homework.dao.CityMapper;
import com.homework.dao.ProvinceMapper;
import com.homework.dao.StatisticsMapper;
import com.homework.dao.TimelineMapper;
import com.homework.domain.Province;
import com.homework.domain.Statistics;
import com.homework.domain.Timeline;
import com.homework.service.CommonService;
import com.homework.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private TimelineMapper timelineMapper;

    @Autowired
    private ProvinceMapper provinceMapper;


    JsonParser jp = new JsonParser();


    @Override
    public List<Province> getAllAreaStat() throws IOException {
        List<Province> provinceList = provinceMapper.getAllAreaStat();
        return provinceList;

    }

    @Override
    public List<Timeline> getAllTimeline() {
        List<Timeline> timelineList = timelineMapper.selectList(null);
        return timelineList;
    }

    @Override
    public Map<String, Object> getStatisticsMap() throws Exception {
        // 获取倒数两天的全国概览数据
        List<Statistics> list = statisticsMapper.selectTwoData();
        // 计算今日和昨日相关数据的差值
        /**
         * 现存确诊  current_confirmed_count
         * 累计确诊  confirmed_count
         * 现存疑似  suspected_count
         * 累计死亡  dead_count
         * 现存重症  serious_count
         * 累计治愈  cured_count
         */
        // 今天减昨天得出差值
        Statistics todayData = list.get(0);
        Statistics yesterdayData = list.get(1);
        Integer currentConfirmedCountDifferenceValue = todayData.getCurrentConfirmedCount() - yesterdayData.getCurrentConfirmedCount();
        Integer confirmedCountDifferenceValue = todayData.getConfirmedCount() - yesterdayData.getConfirmedCount();
        Integer suspectedCountDifferenceValue = todayData.getSuspectedCount() - yesterdayData.getSuspectedCount();
        Integer deadCountDifferenceValue = todayData.getDeadCount() - yesterdayData.getDeadCount();
        Integer seriousCountDifferenceValue = todayData.getSeriousCount() - yesterdayData.getSeriousCount();
        Integer curedCountDifferenceValue = todayData.getCuredCount() - yesterdayData.getCuredCount();

        // 对象转map并添加差值
        Map<String, Object> map = CommonUtils.objectToMap(todayData);
        map.put("currentConfirmedCountDifferenceValue", currentConfirmedCountDifferenceValue);
        map.put("confirmedCountDifferenceValue", confirmedCountDifferenceValue);
        map.put("suspectedCountDifferenceValue", suspectedCountDifferenceValue);
        map.put("deadCountDifferenceValue", deadCountDifferenceValue);
        map.put("seriousCountDifferenceValue", seriousCountDifferenceValue);
        map.put("curedCountDifferenceValue", curedCountDifferenceValue);

        return map;
    }

}
