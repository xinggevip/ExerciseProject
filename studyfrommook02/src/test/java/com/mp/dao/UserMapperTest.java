package com.mp.dao;

import com.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
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
        user.setName("赵六六");
        user.setAge(20);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setEmail("zs@qq.com");
        // user.setRemark("备注，不会被插入到数据库");
        int rows = userMapper.insert(user);
        log.info("user = {}",user);
        Assertions.assertEquals(1,rows);
        log.info("rows = {}",rows);
    }

    /**
     * 普通查询
     */

    // 根据id查询
    @Test
    void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        log.info("user = {}",user);
    }

    // 根据多个id查询 list
    @Test
    void selectIds() {
        List<Long> list = Arrays.asList(1087982257332887553L,
                1088248166370832385L,
                1088250446457389058L);

        List<User> users = userMapper.selectBatchIds(list);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    // 根据条件查询 map
    @Test
    void selectByMap() {
        HashMap<String, Object> columnMap = new HashMap<>();
        //columnMap.put("name","王天风");
        columnMap.put("age",20);  // 注意这里的属性是数据库中的属性名，不是实体中的属性名
        List<User> users = userMapper.selectByMap(columnMap);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }



}