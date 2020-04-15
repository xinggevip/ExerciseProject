package com.example.dao;

import com.example.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xinggevip
 * @date 2020/4/15 14:55
 */
@SpringBootTest
@Slf4j
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void queryById() {
        Employee employee = employeeDao.queryById(1);
        System.out.println(employee);
    }

    @Test
    void queryAllByLimit() {
    }

    @Test
    void queryAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}