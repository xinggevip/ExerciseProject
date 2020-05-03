package org.javaboy.formlogin.controller;

import org.javaboy.formlogin.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 */
@RestController
public class HelloController {

    @Autowired
    private MethodService methodService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user";
    }

    /**
     * 配置拦截方法，能来到web层，但不一定能到service层
     * @return
     */
    @RequestMapping("hello1")
    public String hello1() {
        return methodService.admin();
    }

    @RequestMapping("hello2")
    public String hello2() {
        return methodService.user();
    }

    @RequestMapping("hello3")
    public String hello3() {
        return methodService.hello();
    }
}
