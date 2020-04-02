package com.mp.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 逻辑删除  软删除
     * 不再是delete，变成了update
     * UPDATE user SET deleted=1 WHERE id=? AND deleted=0
     */
    @Test
    void deleteByIdTest() {
        int rows = userMapper.deleteById(1094592041087729666L);
        Assertions.assertEquals(1, rows);
    }

    /**
     * 数据库共有5条数据，但只查出了4条数据，因为有一条数据已经被软删除了
     * mp自动在后面加上了 deleted=0 条件
     * SELECT id,name,age,email,manager_id,create_time,update_time,version,deleted FROM user WHERE deleted=0
     * Total: 4
     */
    @Test
    void selectTest() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 更新也是，mp自动在后面加上了deleted=0 条件
     * UPDATE user SET age=? WHERE id=? AND deleted=0
     */
    @Test
    void updateTest() {
        User user = new User();
        user.setAge(33);
        user.setId(1088248166370832385L);

        int rows = userMapper.updateById(user);
        Assertions.assertEquals(1, rows);
    }

    /**
     * 注意：
     * 自定义sql不会自动在后面加条件
     * select * from user WHERE age > ?
     * 需要手动在sql中加入
     * 或者在代码中写条件
     */
    @Test
    void customSelect1Test() {
        List<User> users = userMapper.customSelect(Wrappers.<User>lambdaQuery()
                .gt(User::getAge, 20));
        users.forEach(System.out::println);
    }

    /**
     * select * from user WHERE age > ? AND deleted = ?
     */
    @Test
    void customSelect2Test() {
        List<User> users = userMapper.customSelect(Wrappers.<User>lambdaQuery()
                .gt(User::getAge, 20)
                .eq(User::getDeleted, 0));
        users.forEach(System.out::println);
    }

}