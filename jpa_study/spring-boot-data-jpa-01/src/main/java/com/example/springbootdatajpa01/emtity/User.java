package com.example.springbootdatajpa01.emtity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xinggevip
 * @date 2020/4/15 9:30
 */
@Data
@Entity  // 告诉jpa这是一个实体类（和数据表映射的类）
@Table(name = "tbl_user")  // @table指定和哪个数据表对应；如果省略默认表名就是user
public class User {
    @Id  // 标明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键
    private Integer id;

    @Column(name = "last_name",length = 50)  // 和数据表对应的列
    private String lastName;
    @Column  // 省略默认列明就是属性名
    private String email;
}
