package com.mp.dao;

import com.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        Assertions.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }

    @Test
    void insertTest() {
        /**
         * MybatisPlus 默认的插入规则是 如果对象属性为null，则不插入
         */
        User user = new User();
        user.setRealName("赵六");
        user.setAge(20);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        Assertions.assertEquals(1,rows);
        log.info("rows = {}",rows);
    }


}