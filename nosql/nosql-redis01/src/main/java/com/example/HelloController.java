package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinggevip
 * @date 2020/5/2 15:28
 */
@RestController
public class HelloController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/set")
    public void set() {
        System.out.println("来到了set请求");
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name", "高公子");
    }
    @GetMapping("/get")
    public void get() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        System.out.println(ops.get("name"));
    }
}
