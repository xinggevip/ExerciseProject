package com.mq.dao;

import com.mq.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        Assertions.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }

}