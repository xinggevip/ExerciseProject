package com.example.service.impl;

import com.example.dao.EmployeeDao;
import com.example.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xinggevip
 * @date 2020/4/15 20:46
 */
@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void getEmployeeByLastName() {
        Employee str = employeeDao.getEmployeeByLastName("string1111");
        System.out.println(str);
    }
}