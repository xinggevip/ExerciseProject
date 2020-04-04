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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private TimelineMapper timelineMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    private String url = "https://ncov.dxy.cn/ncovh5/view/pneumonia";

    private String elementByIdStatisticsService = "getStatisticsService";

    private String elementByIdAreaStat = "getAreaStat";

    private String elementByIdTimeline = "getTimelineService1";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    JsonParser jp = new JsonParser();

    @Override
    public Statistics getAllStatistics() {
        List<Statistics> statistics = statisticsMapper.selectList(null);
        return statistics.get(0);
    }

    @Override
    public List<Province> getAllAreaStat() throws IOException {
        /**
         * 获取html源码
         *//*
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc);
        *//**
         * 去除html标签
         *//*
        String statisticsService = doc.getElementById(elementByIdAreaStat).toString();
        String s = Html2Text(statisticsService);
        *//**
         * 去除所有空格
         *//*
        String replaced = s.replace(" ", "");
        //System.out.println(replaced);
        *//**
         * 去除js语法
         *//*
        String out = replaced.substring(replaced.indexOf("[{\"provinceName\""),replaced.indexOf("}catch(e){}"));
        //System.out.println(out);

        *//**
         * json转对象
         *//*
        List<Province> provinceList = gson.fromJson(out, new TypeToken<List<Province>>(){}.getType());
        ArrayList<City> cities = new ArrayList<>();
        for (Province province : provinceList) {
            for (City city : province.getCities()) {
                city.setProvinceLocationid(province.getLocationId());
                cities.add(city);
            }
        }
        return provinceList;*/

        List<Province> provinceList = provinceMapper.getAllAreaStat();
        return provinceList;

    }

    @Override
    public List<Timeline> getAllTimeline() {
        List<Timeline> timelineList = timelineMapper.selectList(null);
        return timelineList;
    }

    /**
     * 去掉html相关标签
     * @param inputString
     * @return
     */
    private String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_html1;
        Matcher m_html1;
        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {

        }
        return textStr;// 返回文本字符串
    }
}
