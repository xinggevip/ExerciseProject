package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperForUpdateTest {
    @Autowired
    private UserMapper userMapper;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * 根据主键进行更新
     * 只更新不为null的属性
     * UPDATE user SET age=?, email=? WHERE id=?
     */
    @Test
    void updateById() {
        User user = new User();
        user.setId(1244858464782528513L);
        user.setAge(22);
        user.setEmail("wangyu@qq.com");
        int rows = userMapper.updateById(user);
        Assertions.assertEquals(1,rows);
    }

    /**
     * 根据条件更新
     * 只更新不为null的属性
     * UPDATE user SET email=? WHERE name = ? AND age = ?
     */
    @Test
    void updateByWrapper() {
        // 条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "王雨").eq("age", 22);
        // 新实体
        User user = new User();
        user.setEmail("wnagyu666@qq.com");
        // 执行更新
        int rows = userMapper.update(user, updateWrapper);
        Assertions.assertEquals(1,rows);
    }

    /**
     * UpdateWrapper构造器更新
     * UPDATE user SET email=? WHERE name LIKE CONCAT('%',?,'%')
     * 把where name like '%王雨%' 的邮箱改成 wangyuIsPretty@qq.com
     */
    @Test
    void updateByUpdateWrapper() {
        // 条件
        User user = new User();
        user.setName("王雨");
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>(user);  // 条件在实体类属性上的注解 like、eq、lt...，默认为eq
        // 新实体
        User userNew = new User();
        userNew.setEmail("wangyuIsPretty@qq.com");
        // 执行更新
        int rows = userMapper.update(userNew, userUpdateWrapper);
        Assertions.assertEquals(1,rows);
    }

    /**
     * 不用new新实体的更新
     * UPDATE user SET email=? WHERE name = ? AND age = ?
     */
    @Test
    void updateByUpdateWrapper2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        UpdateWrapper<User> userUpdateWrapper = updateWrapper
                .eq("name", "王雨").eq("age", 22)  // 条件
                .set("email", "wangyuIsAStudent@qq.com");                    // 新实体

        int rows = userMapper.update(null, updateWrapper);             // 执行更新
        Assertions.assertEquals(1,rows);

    }

    /**
     * LambdaUpdateWrapper更新
     * UPDATE user SET email=? WHERE name = ? AND age = ?
     */
    @Test
    void updateByLambdaUpdateWrapper() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.<User>lambdaUpdate();
        LambdaUpdateWrapper<User> updateWrapper = lambdaUpdate
                .eq(User::getName, "王雨").eq(User::getAge, 22)  // 条件
                .set(User::getEmail, "wangyuVip@qq.coom");               // 新实体

        int rows = userMapper.update(null, updateWrapper);        // 执行更新
        Assertions.assertEquals(1,rows);
    }

    /**
     * LambdaUpdateChainWrapper更新
     * UPDATE user SET email=? WHERE name = ? AND age = ?
     */
    @Test
    void updateByLambdaUpdateChainWrapper() {
        boolean success = new LambdaUpdateChainWrapper<User>(userMapper)  // 对返回的更新行数结果进行了封装，更新行数大于0，返回true；小于0，返回false
                .eq(User::getName, "王雨").eq(User::getAge, 22)   // 条件
                .set(User::getEmail, "wangyuSVIP@qq.coom")                 // 新实体
                .update();                                                  // 执行更新
        Assertions.assertEquals(true,success);
    }


}
