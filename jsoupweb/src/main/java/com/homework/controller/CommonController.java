package com.homework.controller;


import com.homework.domain.Province;
import com.homework.domain.Statistics;
import com.homework.domain.Timeline;
import com.homework.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Api(tags = {""})
@RestController
//@RequestMapping("/city")
public class CommonController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonService commonService;

    // 获取全国概览数据
    @ApiOperation(value = "获取全国概览数据")
    @GetMapping("/getAllStatistics")
    public Statistics getAllStatistics(){
        return commonService.getAllStatistics();
    }
    // 获取全国详情数据
    // TODO 从数据库中读取数据，等待时间有点长 待优化
    @ApiOperation(value = "获取全国详情数据")
    @GetMapping("/getAllAreaStat")
    public List<Province> getAllAreaStat() throws IOException {
        return commonService.getAllAreaStat();
    }
    // 获取国内资讯
    @ApiOperation(value = "获取国内资讯")
    @GetMapping("/getAllTimeline")
    public List<Timeline> getAllTimeline(){
        return commonService.getAllTimeline();
    }
}
