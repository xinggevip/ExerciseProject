package com.mp.dao;

import com.mp.config.MyBatisPlusConfig;
import com.mp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FillTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        User user = new User();
        user.setName("新增d");
        user.setAge(20);
        //user.setManagerId(1088248166370832385L);
        user.setEmail("1511@qq.com");
        int rows = userMapper.insert(user);
        Assertions.assertEquals(1, rows);
    }

    @Test
    void updateTest() {
        User user = new User();
        user.setId(1245562097182490625L);
        user.setName("更新后");
        int rows = userMapper.updateById(user);
        Assertions.assertEquals(1, rows);
    }

    @Test
    void selectById() {
        /**
         * 注意
         * sql进行了过滤后，指定表名则不生效
         * SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user WHERE id=? AND deleted=0
         */
        MyBatisPlusConfig.myTableName.set("user_2020");
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

}
