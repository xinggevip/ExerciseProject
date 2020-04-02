package com.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.time.LocalDateTime;

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
    @TableField(fill = FieldFill.INSERT)  // 插入时填充
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    @TableField(fill = FieldFill.UPDATE)  // 更新时填充
    private LocalDateTime updateTime;
    /**
    * 版本
    */
    @Version // 标识版本
    private Integer version;
    /**
    * 逻辑删除标识(0.未删除,1.已删除)
    */
    @TableLogic // 逻辑删除标识
    @TableField(select = false)  // 查询的时候不显示
    private Integer deleted;


}