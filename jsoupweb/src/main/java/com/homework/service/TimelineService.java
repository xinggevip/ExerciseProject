package com.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.domain.Timeline;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
public interface TimelineService extends IService<Timeline> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Timeline>
     */
    IPage<Timeline> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param timeline 
     * @return int
     */
    int add(Timeline timeline);

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
     * @param timeline 
     * @return int
     */
    int updateData(Timeline timeline);

    /**
     * id查询数据
     *
     * @param id id
     * @return Timeline
     */
    Timeline findById(Long id);
}
