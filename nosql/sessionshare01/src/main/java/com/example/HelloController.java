package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author xinggevip
 * @date 2020/5/2 16:26
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("name","高大侠");
        return String.valueOf(port);
    }

    @RequestMapping("/get")
    public String get(HttpSession session) {
        String value = (String) session.getAttribute("name");
        return value + port;
    }

}
