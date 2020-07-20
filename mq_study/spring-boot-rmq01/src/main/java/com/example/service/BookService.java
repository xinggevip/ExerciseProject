package com.example.service;

import com.example.entity.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author xinggevip
 * @date 2020/4/17 21:02
 */
@Service
public class BookService {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book) {
        System.out.println("收到消息" + book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive02(Message message){
        byte[] body = message.getBody();
        HashMap<String,Object> hashMap = gson.fromJson(new String(body), HashMap.class);
        System.out.println(hashMap);
        System.out.println(message.getMessageProperties());
    }
}
