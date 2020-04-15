package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Employee)表控制层
 *
 * @author 油条
 * @since 2020-04-15 12:49:49
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Employee selectOne(Integer id) {
        return this.employeeService.queryById(id);
    }

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeService.queryById(id);
        return employee;
    }

    @PostMapping("/emp")
    public Employee update(@RequestBody Employee employee) {
        Employee res = employeeService.update(employee);
        return res;
    }

    @PostMapping("/delemp/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if (employeeService.deleteById(id)) {
            return "success";
        }
        return "fail";
    }

}