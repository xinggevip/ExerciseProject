package com.mp.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-04-01 20:33:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Student extends Model<Student>{
    private static final long serialVersionUID = 610591100878401265L;
    
    private Integer id;
    
    private String name;

    private String sex;
    
    private Integer age;


    public Student(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}