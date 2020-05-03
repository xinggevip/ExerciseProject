package com.example.dao;

import com.example.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author xinggevip
 * @date 2020/5/2 21:38
 */
public interface BookDao extends MongoRepository<Book,Integer> {
    List<Book> findBookByNameContaining(String name);
}
