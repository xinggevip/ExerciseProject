package com.mp.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @SqlParser(filter = true) // 过滤不加入租户信息的方法 3.1.1之前的版本还需要再yml文件做一个全局配置
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> customSelect(@Param(Constants.WRAPPER) Wrapper<User> wrapper);


}
