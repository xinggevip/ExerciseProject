package com.homework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.homework.dao.CityMapper;
import com.homework.dao.ProvinceMapper;
import com.homework.dao.StatisticsMapper;
import com.homework.dao.TimelineMapper;
import com.homework.domain.City;
import com.homework.domain.Province;
import com.homework.domain.Statistics;
import com.homework.domain.Timeline;
import com.homework.service.CityService;
import com.homework.service.ProvinceService;
import com.homework.service.StatisticsService;
import com.homework.service.TimelineService;
import jdk.nashorn.internal.objects.annotations.Where;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class FixedTimeDo {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private TimelineMapper timelineMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private StatisticsMapper statisticsMapper;

    private String url = "https://ncov.dxy.cn/ncovh5/view/pneumonia";

    private String elementByIdStatisticsService = "getStatisticsService";

    private String elementByIdAreaStat = "getAreaStat";

    private String elementByIdTimeline = "getTimelineService1";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    JsonParser jp = new JsonParser();


    /**
     * 全国各城市疫情数据持久化
     * @throws IOException
     */
    public void getAreaStat() throws IOException {
        /**
         * 获取html源码
         */
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc);
        /**
         * 去除html标签
         */
        String statisticsService = doc.getElementById(elementByIdAreaStat).toString();
        String s = Html2Text(statisticsService);
        /**
         * 去除所有空格
         */
        String replaced = s.replace(" ", "");
        //System.out.println(replaced);
        /**
         * 去除js语法
         */
        String out = replaced.substring(replaced.indexOf("[{\"provinceName\""),replaced.indexOf("}catch(e){}"));
        //System.out.println(out);

        /**
         * json转对象
         */
        List<Province> provinceList = gson.fromJson(out, new TypeToken<List<Province>>(){}.getType());
        ArrayList<City> cities = new ArrayList<>();
        for (Province province : provinceList) {
            for (City city : province.getCities()) {
                city.setProvinceLocationId(province.getLocationId());
                cities.add(city);
            }
        }
//        System.out.println(gson.toJson(provinceList));

        // 写入数据库前过滤数据，即找出需要增加/更新的数据
        // 1.从数据库找到所有省疫情数据
        List<Province> provinceListOld = provinceMapper.selectList(null);
        Iterator<Province> iterator = provinceList.iterator();
        // 2.如果数据库没有数据，则跳过判断直接插入数据；否则找出一样的数据移除掉，然后saveorupdate
        if (provinceListOld != null) {
            // 遍历判断一样的数据，一样则移出
            for (int i = 0; i < provinceList.size(); i++) {
                if (provinceList.get(i).equals(provinceListOld.get(i))) {
                    provinceList.remove(i);
                    i--;
                }

            }
        }

        // 写入到数据库

        // 批量插入省记录
        provinceService.saveOrUpdateBatch(provinceList);
        // 清空城市表
        cityMapper.clearAll();
        // 批量插入城市记录
        cityService.saveBatch(cities);

    }

    /**
     * 全国概览数据持久化
     * @throws IOException
     */
    public void getStatisticsService() throws IOException {
        /**
         * 获取html源码
         */
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc);
        /**
         * 去除html标签
         */
        String statisticsServiceStr = doc.getElementById(elementByIdStatisticsService).toString();
        String s = Html2Text(statisticsServiceStr);
        /**
         * 去除所有空格
         */
        String replaced = s.replace(" ", "");
        //System.out.println(replaced);
        /**
         * 去除js语法
         */
        String out = replaced.substring(replaced.indexOf("{\"id\":1"),replaced.indexOf("}catch(e){}"));
        //System.out.println(out);

        /**
         * 把字符串时间戳转换成时间
         */
        JsonObject jo = jp.parse(out).getAsJsonObject();
        //获取时间对应的值
        String createTime = jo.get("createTime").getAsString();
        String modifyTime = jo.get("modifyTime").getAsString();
        boolean deleted = jo.get("deleted").getAsBoolean();
        if (deleted) {
            out = out.replace("true", "1");
        } else {
            out = out.replace("false", "0");
        }
        //System.out.println(createTime);
        //System.out.println(modifyTime);
        //时间戳毫秒转LocalDateTime
        LocalDateTime localDateTimeCreateTime = new Date(Long.valueOf(createTime)).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        LocalDateTime localDateTimeModifyTime = new Date(Long.valueOf(modifyTime)).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        System.out.println(localDateTimeCreateTime);
        System.out.println(localDateTimeModifyTime);


        out = out.replace("createTime", localDateTimeCreateTime.toString());
        out = out.replace("modifyTime", localDateTimeModifyTime.toString());



        /**
         * json转对象
         */
        Statistics statics = gson.fromJson(out, Statistics.class);
        statics.setCreateTime(localDateTimeCreateTime);
        statics.setModifyTime(localDateTimeModifyTime);

        /**
         * 从数据库获取最后一条记录
         */
        Statistics statistics = statisticsMapper.selectLastRow();
        /**
         * 数据库获取最后一条记录为null，则执行插入，否则判断
         */
        if (statistics == null) {
            statisticsService.save(statics);
        } else {
            /**
             * 判断新老对象的修改时间是否为同一天
             */
            Boolean boo = thanTime(statics.getModifyTime(), statistics.getModifyTime());
            if (boo) {
                // 执行更新,根据id
                statics.setId(statistics.getId());
                statisticsService.updateById(statics);
            } else {
                // 执行插入
                statisticsService.save(statics);
            }

        }


        /*statics.setId(1);
        statisticsService.saveOrUpdate(statics);

        System.out.println(gson.toJson(statics));*/

    }

    /**
     * 国内资讯持久化
     * @throws IOException
     */
    public void getTimeline() throws IOException {
        /**
         * 获取html源码
         */
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc);
        /**
         * 去除html标签
         */
        String statisticsServiceStr = doc.getElementById(elementByIdTimeline).toString();
        String s = Html2Text(statisticsServiceStr);
        /**
         * 去除所有空格
         */
        String replaced = s.replace(" ", "");
        //System.out.println(replaced);
        /**
         * 去除js语法
         */
        String out = replaced.substring(replaced.indexOf("[{"),replaced.indexOf("}catch(e){}"));
        List<Timeline> timelineList = gson.fromJson(out, new TypeToken<List<Timeline>>(){}.getType());
        // 清空文章表
        timelineMapper.deleteAll();
        // 插入新文章表
        timelineService.saveBatch(timelineList);

//        System.out.println(gson.toJson(timelineList));
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

    /**
     * 判断两个时间是否为同一天
     * @param now
     * @param end
     * @return
     */
    private Boolean thanTime(LocalDateTime now,LocalDateTime end) {
        Duration duration = Duration.between(now,end);
        long days = duration.toDays(); //相差的天数
        return days == 0 ? true : false;
    }

}
