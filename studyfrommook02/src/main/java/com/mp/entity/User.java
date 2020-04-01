package com.mp.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 这是局部的主键策略
     * 也可以配置全局的主键策略
     * 当全局策略和局部策略都配置的时候
     * 局部策略的优先级大于全局策略
     */

    // @TableId(type = IdType.AUTO)  // 数据库为数值类型 数据库设置主键自增才能生效，否则报错
    // @TableId(type = IdType.NONE)   // 该类型为未设置主键类型 默认配置  无论数据库是否设置自增，都会使用该模式
    // @TableId(type = IdType.ID_WORKER_STR)  // 数据库为字符串类型  效果自增
    // @TableId(type = IdType.UUID)  // 数据库为字符串类型 生成随机字符串
    private Long id;

    @TableField(condition = SqlCondition.LIKE)
    private String name;

    @TableField(condition = "%s&lt;#{%s}")
    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

}
