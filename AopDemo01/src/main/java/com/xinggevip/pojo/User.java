package com.xinggevip.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author xinggevip
 * @date 2020/7/17 14:07
 */
@Component
@Data
public class User {
    private String userName;
    private String age;

    public String eat(String foodName){
        System.out.println("吃" + foodName + "...");
        return "吃饭";
    }

}
