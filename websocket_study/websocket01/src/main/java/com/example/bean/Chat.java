package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinggevip
 * @date 2020/5/2 12:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String from;
    private String content;
    private String to;
}
