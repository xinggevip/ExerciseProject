package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class MailDemo01ApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        // 简单邮件纯文本
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("主题名称测试");
        simpleMailMessage.setText("今天天气不错~测试");
        simpleMailMessage.setFrom("1511844263@qq.com");
        simpleMailMessage.setTo("1511844263@qq.com");
        mailSender.send(simpleMailMessage);

    }

    @Test
    void contextLoads2() throws Exception {
        // 复杂邮件 html标签 附件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        // 正文
        helper.setSubject("一封复杂邮件测试");
        String html = "<h1>复杂邮件title</h1>\n" +
                "<p style=\"color: skyblue\">今天天气不错</p>";
        helper.setText(html,true);
        File file = ResourceUtils.getFile("classpath:static/imgs/girl01.jpg");//附件文件在resource下的相对路径
        helper.addAttachment("girl01.jpg",file);

        helper.setFrom("1511844263@qq.com");
        helper.setTo("1511844263@qq.com");

        mailSender.send(mimeMessage);

    }

}
