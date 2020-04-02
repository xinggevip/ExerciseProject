package com.mp.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MyMapper<T> extends BaseMapper<T> {
    /**
     * 删除所有
     * @return
     */
    int deleteAll();
}
