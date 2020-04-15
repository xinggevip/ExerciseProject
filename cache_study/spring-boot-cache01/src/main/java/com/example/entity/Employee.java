package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Employee)实体类
 *
 * @author 油条
 * @since 2020-04-15 12:49:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = -89608440207177280L;
    
    private Integer id;
    
    private String lastName;
    
    private String email;
    
    private Integer gender;
    
    private Integer dId;


}