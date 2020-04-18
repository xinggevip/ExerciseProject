package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "xing",type="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String bookName;
    private String auto;

}