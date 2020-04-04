package com.homework.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homework.dao.CityMapper;
import com.homework.dao.ProvinceMapper;
import com.homework.domain.City;
import com.homework.domain.Province;
import com.homework.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {


    @Override
    public  IPage<Province> findListByPage(Integer page, Integer pageCount){
        IPage<Province> wherePage = new Page<>(page, pageCount);
        Province where = new Province();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Province province){
        return baseMapper.insert(province);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Province province){
        return baseMapper.updateById(province);
    }

    @Override
    public Province findById(Long id){
        return  baseMapper.selectById(id);
    }


}
