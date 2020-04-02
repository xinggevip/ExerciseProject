package com.mp.dao;

import com.mp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OptTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 默认把version的版本 + 1
     * UPDATE user SET email=?, update_time=?, version=? WHERE id=? AND version=? AND deleted=0
     */
    @Test
    void optTest() {
        /**
         * 注意这里的wrapper不能复用
         * 如果两个更新语句用一个wrapper
         * 第一条语句会更新成功
         * 第二条语句会更新失败
         */
        Integer version = 1;  // 模拟从数据库中查版本
        User user = new User();
        user.setId(1245562097182490625L);
        user.setEmail("121@qq.com");
        user.setVersion(version);

        int rows = userMapper.updateById(user);
        Assertions.assertEquals(1, rows);
    }

}
