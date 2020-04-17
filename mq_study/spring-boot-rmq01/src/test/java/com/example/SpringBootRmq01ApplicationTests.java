package com.example;

import com.example.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBootRmq01ApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 点对点
     */
    @Test
    void contextLoads() {
        // Message需要自己构建一个；定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);
        // Object 默认当成消息体，只需要传入要发送的对象，自动化序列发送给rabbitmq；
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "这是第一个信息");
        map.put("data", Arrays.asList("HelloWorld", 666, true));
        //对象被默认序列以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","atguigu",map);
    }

    /**
     * 取指定消息队列消息
     */
    @Test
    public void receiveAndConvert(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    void sendMessageToAll() {
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }

}
