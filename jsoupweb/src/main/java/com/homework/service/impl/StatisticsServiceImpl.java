package com.homework.service.impl;

import com.homework.domain.Statistics;
import com.homework.dao.StatisticsMapper;
import com.homework.service.StatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {

    @Override
    public  IPage<Statistics> findListByPage(Integer page, Integer pageCount){
        IPage<Statistics> wherePage = new Page<>(page, pageCount);
        Statistics where = new Statistics();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Statistics statistics){
        return baseMapper.insert(statistics);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Statistics statistics){
        return baseMapper.updateById(statistics);
    }

    @Override
    public Statistics findById(Long id){
        return  baseMapper.selectById(id);
    }
}
