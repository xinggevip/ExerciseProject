package com.example.dao;

import com.example.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author xinggevip
 * @date 2020/5/3 16:05
 */
public interface BookDao extends MongoRepository<Book,Integer> {
}
