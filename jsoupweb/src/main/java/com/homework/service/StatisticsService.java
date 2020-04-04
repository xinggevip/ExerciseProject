package com.homework.service;

import com.homework.domain.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
public interface StatisticsService extends IService<Statistics> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Statistics>
     */
    IPage<Statistics> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param statistics 
     * @return int
     */
    int add(Statistics statistics);

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
     * @param statistics 
     * @return int
     */
    int updateData(Statistics statistics);

    /**
     * id查询数据
     *
     * @param id id
     * @return Statistics
     */
    Statistics findById(Long id);
}
