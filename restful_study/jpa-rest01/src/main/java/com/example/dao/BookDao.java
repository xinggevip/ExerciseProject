package com.example.dao;

import com.example.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xinggevip
 * @date 2020/5/3 15:10
 */
public interface BookDao extends JpaRepository<Book,Integer> {
}
