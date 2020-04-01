package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
@Slf4j
@Transactional // 加上事务注解，测试的时候不会真正的更新删除数据库中的数据
public class UserMapperForDeleteTest {

    @Autowired
    private UserMapper userMapper;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * 根据主键删除
     * DELETE FROM user WHERE id=?
     */
    @Test
    void deleteById() {
        int rows = userMapper.deleteById(1244808674497593345L);
        Assertions.assertEquals(1,rows);
    }

    /**
     * map删除
     * 匹配条件为相等
     * DELETE FROM user WHERE name = ?
     */
    @Test
    void deleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "赵六");
        int rows = userMapper.deleteByMap(map);
        Assertions.assertEquals(1,rows);
    }

    /**
     * 根据主键集合删除
     * DELETE FROM user WHERE id IN ( ? , ? )
     */
    @Test
    void deleteByIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1244808674497593345L, 1244832954518114305L));
        Assertions.assertEquals(2,rows);
    }

    /**
     * LambdaQueryWrapper 删除
     * 根据条件删除
     * DELETE FROM user WHERE name = ? OR age > ?
     */
    @Test
    void deleteByLambdaQueryWrapper() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.eq(User::getName, "张三").or().gt(User::getAge, 50);
        int rows = userMapper.delete(lambdaQuery);
        log.info("rows = {}",rows);
    }
    /**
     * 其他的删除和查询的语法基本一样
     * 不再写了
     */
}
