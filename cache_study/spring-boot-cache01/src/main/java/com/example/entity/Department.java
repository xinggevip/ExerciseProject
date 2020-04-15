package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Department)实体类
 *
 * @author 油条
 * @since 2020-04-15 12:49:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = 838900442427487213L;
    
    private Integer id;
    
    private String departmentName;


}