package com.example.repostry;

import com.example.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    //自定义查询方法
    public List<Book> findByBookNameLike(String bookName);
}