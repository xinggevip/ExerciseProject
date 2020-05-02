package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinggevip
 * @date 2020/5/1 18:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String name;
    private String content;
}
