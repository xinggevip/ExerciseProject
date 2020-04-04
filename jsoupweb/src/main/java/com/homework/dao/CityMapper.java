package com.homework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homework.domain.City;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-04
 */
public interface CityMapper extends BaseMapper<City> {
    City getCityWidthProvinceLocationId(Integer id);
    int clearAll();
}
