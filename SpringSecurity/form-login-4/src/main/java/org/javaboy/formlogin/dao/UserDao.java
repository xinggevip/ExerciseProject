package org.javaboy.formlogin.dao;

import org.javaboy.formlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}