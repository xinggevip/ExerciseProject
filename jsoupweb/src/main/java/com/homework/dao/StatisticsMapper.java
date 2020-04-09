package com.homework.dao;

import com.homework.domain.Statistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
public interface StatisticsMapper extends BaseMapper<Statistics> {

    Statistics selectLastRow();

    Statistics selectYesterdayData();


    List<Statistics> selectTwoData();
}
