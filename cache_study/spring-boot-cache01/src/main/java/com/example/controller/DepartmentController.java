package com.example.controller;

import com.example.entity.Department;
import com.example.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Department)表控制层
 *
 * @author 油条
 * @since 2020-04-15 12:49:40
 */
@RestController
@RequestMapping("department")
public class DepartmentController {
    /**
     * 服务对象
     */
    @Resource
    private DepartmentService departmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Department selectOne(Integer id) {
        return this.departmentService.queryById(id);
    }

    @GetMapping("/dep/{id}")
    public Department getEmployee(@PathVariable("id") Integer id) {
        Department department = departmentService.queryById(id);
        return department;
    }

    @PostMapping("/dep")
    public Department update(@RequestBody Department department) {
        Department res = departmentService.update(department);
        return res;
    }

    @PostMapping("/deldep/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if (departmentService.deleteById(id)) {
            return "success";
        }
        return "fail";
    }

}