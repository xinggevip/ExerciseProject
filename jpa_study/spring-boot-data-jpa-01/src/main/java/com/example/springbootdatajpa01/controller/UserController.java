package com.example.springbootdatajpa01.controller;

import com.example.springbootdatajpa01.emtity.User;
import com.example.springbootdatajpa01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author xinggevip
 * @date 2020/4/15 9:47
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        /**
         * SpringBoot 1.5.x 使用
         * userRepository.findOne(id)即可
         * https://www.jianshu.com/p/9936ba98da5a
         */
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            return user;
        } else {
            // handle not found, return null or throw
            System.out.println("no exit!");
        }
        return null;
    }

    @PostMapping("/user")
    public User insertUser(@RequestBody User user) {
        User res = userRepository.save(user);
        return res;
    }

}
