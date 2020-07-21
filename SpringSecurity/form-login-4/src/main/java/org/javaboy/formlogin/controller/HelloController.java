package org.javaboy.formlogin.controller;

import org.javaboy.formlogin.config.VerificationCode;
import org.javaboy.formlogin.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @GetMapping("/verifyCode")
    public void verfyCOde(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage codeImage = code.getImage();
        String codeText = code.getText();
        HttpSession session = request.getSession();
        session.setAttribute("verify_code",codeText);
        VerificationCode.output(codeImage, response.getOutputStream());

    }
}
