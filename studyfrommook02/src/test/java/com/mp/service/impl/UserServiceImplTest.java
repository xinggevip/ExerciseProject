package com.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mp.entity.User;
import com.mp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * 获取一条记录
     * SELECT id,name,age,email,manager_id,create_time FROM user WHERE age > ?
     */
    @Test
    void getOneTest() {

        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.gt(User::getAge, 20);
        // User one = userService.getOne(lambdaQueryWrapper); // 返回多条或null结果将会报错 第二个参数默认为true
        User one = userService.getOne(lambdaQueryWrapper, false); // 如果返回多个结果会有警告，并返回第一个结果
        System.out.println(gson.toJson(one));

    }

    /**
     * 默认操作1000条数据，第二个参数可以自定义数量
     * saveBatch:批量插入
     * saveOrUpdateBatch:批量插入或更新
     */
    @Test
    void batchTest() {

        User user1 = new User();
        user1.setName("小红1");
        user1.setAge(21);

        User user2 = new User();
        user2.setName("小红2");
        user2.setAge(22);

        List<User> users = Arrays.asList(user1, user2);

        boolean success = userService.saveBatch(users); // 第二个参数更改操作数量
        Assertions.assertEquals(true,success);

    }

    /**
     * 查询 lambdaQuery链式调用
     * SELECT id,name,age,email,manager_id,create_time FROM user WHERE age > ? AND name LIKE ?
     */
    @Test
    void lambdaQueryTest() {
        List<User> userList = userService.lambdaQuery().gt(User::getAge, 20).like(User::getName, "雨").list();
        userList.forEach(System.out::println);
    }

    /**
     * 更新 lambdaUpdate链式调用
     * UPDATE user SET age=? WHERE age = ?
     */
    @Test
    void lambdaUpdateTest() {
        boolean success = userService.lambdaUpdate()
                .eq(User::getAge, 25)  // 条件
                .set(User::getAge, 26)     // 新实体
                .update();                 // 执行更新
        Assertions.assertEquals(true,success);
    }

    /**
     * 删除 lambdaUpdate链式调用
     */
    @Test
    void lambdaUpdateForDelTest() {
        boolean success = userService.lambdaUpdate()
                .eq(User::getAge, 25)  // 条件
                .remove();                  // 删除
        Assertions.assertEquals(true,success);
    }

}