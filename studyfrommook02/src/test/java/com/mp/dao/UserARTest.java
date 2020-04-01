package com.mp.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
public class UserARTest {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * AR模式 插入
     * INSERT INTO user ( id, name, age, email, manager_id, create_time ) VALUES ( ?, ?, ?, ?, ?, ? )
     */
    @Test
    void insertTest() {
        User user = new User();
        user.setName("高公子");
        user.setAge(22);
        user.setEmail("1511844263@qq.com");
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());

        boolean success = user.insert();
        log.info("success = {}",success);
        log.info("user = {}",user);
    }

    /**
     * AR模式 根据主键查询
     * SELECT id,name,age,email,manager_id,create_time FROM user WHERE id=?
     */
    @Test
    void selectByIdTest() {
        User user = new User();

        User userSelect = user.selectById(1245242248594059266L);
        log.info("userSelect = {}",gson.toJson(userSelect));
    }

    /**
     * AR模式 根据主键更新
     * UPDATE user SET name=? WHERE id=?
     */
    @Test
    void updateById() {
        User user = new User();
        user.setId(1245244017692753922L);
        user.setName("苍老师");

        boolean success = user.updateById();
        log.info("success = {}",success);

    }

    /**
     * AR模式 根据主键删除
     * DELETE FROM user WHERE id=?
     * 注意：删除不存在的也会返回true
     */
    @Test
    void deleteById() {
        User user = new User();
        user.setId(1245244017692753922L);

        boolean success = user.deleteById();
        log.info("success = {}",success);
    }

    /**
     * 先根据主键进行查询，
     * 存在则执行update
     * 不存在则执行insert
     */
    @Test
    void insertOrUpdate() {
        User user = new User();
        user.setId(1245243846363779073L);
        user.setName("高公子");
        user.setAge(22);
        user.setEmail("1511844263@qq.com");
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());

        boolean success = user.insertOrUpdate();
        log.info("success = {}",success);
        log.info("user = {}",user);
    }
}
