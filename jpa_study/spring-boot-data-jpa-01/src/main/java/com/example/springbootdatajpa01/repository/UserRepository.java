package com.example.springbootdatajpa01.repository;

import com.example.springbootdatajpa01.emtity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xinggevip
 * @date 2020/4/15 9:40
 */
//继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {  // 泛型第一个参数为要操作的对象，第二个参数为该对象的主键类型
}
