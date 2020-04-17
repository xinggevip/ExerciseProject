package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xinggevip
 * @date 2020/4/17 21:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private String author;
}
