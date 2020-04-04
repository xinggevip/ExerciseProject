package com.homework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homework.domain.Province;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
public interface ProvinceMapper extends BaseMapper<Province> {


    List<Province> getAllAreaStat();
}
