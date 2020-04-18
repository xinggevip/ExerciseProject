package com.example.entity;

import io.searchbox.annotations.JestId;
import lombok.Data;

@Data
public class Article {

    @JestId
    private Integer id;
    private String author;
    private String title;
    private String content;

}