package com.example.controller;

import com.example.bean.Chat;
import com.example.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author xinggevip
 * @date 2020/5/1 18:21
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Message greeting(Message message) {
//        System.out.println(message);
//        return message;
//    }

    @MessageMapping("/hello")
    public void greeting(Message message) {
        System.out.println(message);
        simpMessagingTemplate.convertAndSend("/topic/greetings",message);
    }

    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat) {
        chat.setFrom(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }


}
