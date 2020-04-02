package com.mp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class InjectorTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void deleteAllTest() {
        int rows = userMapper.deleteAll();
        System.out.println(rows);
    }

}
