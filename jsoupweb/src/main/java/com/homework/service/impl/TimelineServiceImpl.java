package com.homework.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homework.dao.TimelineMapper;
import com.homework.domain.Timeline;
import com.homework.service.TimelineService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
@Service
public class TimelineServiceImpl extends ServiceImpl<TimelineMapper, Timeline> implements TimelineService {

    @Override
    public  IPage<Timeline> findListByPage(Integer page, Integer pageCount){
        IPage<Timeline> wherePage = new Page<>(page, pageCount);
        Timeline where = new Timeline();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Timeline timeline){
        return baseMapper.insert(timeline);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Timeline timeline){
        return baseMapper.updateById(timeline);
    }

    @Override
    public Timeline findById(Long id){
        return  baseMapper.selectById(id);
    }
}
