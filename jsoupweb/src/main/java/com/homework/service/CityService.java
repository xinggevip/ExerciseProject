package com.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.domain.City;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-04
 */
public interface CityService extends IService<City> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<City>
     */
    IPage<City> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param city 
     * @return int
     */
    int add(City city);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param city 
     * @return int
     */
    int updateData(City city);

    /**
     * id查询数据
     *
     * @param id id
     * @return City
     */
    City findById(Long id);

}
