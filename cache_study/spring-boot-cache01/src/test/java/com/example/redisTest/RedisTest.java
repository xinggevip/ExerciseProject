package com.example.redisTest;

import com.example.dao.EmployeeDao;
import com.example.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author xinggevip
 * @date 2020/4/16 8:58
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;  // 操作k-v都是字符串的

    @Autowired
    private RedisTemplate redisTemplate;  // k-v都是对象的

    @Autowired
    private RedisTemplate<Object, Employee> empRedisTemplate;

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Redis常见的五大数据类型
     * String（字符串），List（列表），Set（集合），Hash（散列），ZSet（有序集合）
     * RedisTemplate
     * https://blog.csdn.net/It_sharp/article/details/104695893?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522158699924519725222458919%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=158699924519725222458919&biz_id=0&utm_source=distribute.pc_search_result.none-task-blog-2~all~baidu_landing_v2~default-4
     * StringRedisTemplate
     * https://blog.csdn.net/FindHuni/article/details/88019619?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522158699899519725219926725%2522%252C%2522scm%2522%253A%252220140713.130056874..%2522%257D&request_id=158699899519725219926725&biz_id=0&utm_source=distribute.pc_search_result.none-task-blog-1~blog~default~default-1
     * 两者区别
     * https://blog.csdn.net/notsaltedfish/article/details/75948281?ops_request_misc=&request_id=&biz_id=102&utm_source=distribute.pc_search_result.none-task-blog-SOBAIDUWEB-0
     */


    /**
     * 默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
     * 序列化成了16进制
     */
    @Test
    void saveObjectTest01() {
        Employee employee = employeeDao.queryById(1);
        redisTemplate.opsForValue().set("emp-00",employee);
        Employee employee1 = empRedisTemplate.opsForValue().get("emp-00");
        System.out.println(employee1);  // TODO 返回竟然是null
    }


    /**
     * 可以自定义序列化机制，把对象保存成json字符串
     */
    @Test
    void saveObjectTest02() {
        Employee employee = employeeDao.queryById(1);
        empRedisTemplate.opsForValue().set("emp-01",employee);
        Employee employee1 = empRedisTemplate.opsForValue().get("emp-01");
        System.out.println(employee1);  // Employee(id=1, lastName=string1111, email=string, gender=0, dId=0)
    }




}
