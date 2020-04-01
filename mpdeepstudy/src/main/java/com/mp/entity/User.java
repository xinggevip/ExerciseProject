package com.mp.entity;

import lombok.Data;

import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-01 19:03:16
 */
@Data
public class User{
    /**
    * 主键
    */
    private Long id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 直属上级id
    */
    private Long managerId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 版本
    */
    private Integer version;
    /**
    * 逻辑删除标识(0.未删除,1.已删除)
    */
    private Integer deleted;


}