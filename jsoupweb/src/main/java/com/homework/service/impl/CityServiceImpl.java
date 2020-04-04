package com.homework.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homework.dao.CityMapper;
import com.homework.domain.City;
import com.homework.service.CityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-04
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Override
    public  IPage<City> findListByPage(Integer page, Integer pageCount){
        IPage<City> wherePage = new Page<>(page, pageCount);
        City where = new City();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(City city){
        return baseMapper.insert(city);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(City city){
        return baseMapper.updateById(city);
    }

    @Override
    public City findById(Long id){
        return  baseMapper.selectById(id);
    }
}
