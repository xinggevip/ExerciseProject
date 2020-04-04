package com.homework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homework.domain.Timeline;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinggevip
 * @since 2020-04-03
 */
public interface TimelineMapper extends BaseMapper<Timeline> {

    int deleteAll();
}
