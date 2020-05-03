package com.example.dao;

import com.example.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * @author xinggevip
 * @date 2020/5/2 21:43
 */
@SpringBootTest
class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void insertBookTest() {
        Book book = new Book();
        book.setId(2);
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        bookDao.insert(book);
    }

    @Test
    void findBookByNameContaining() {
        List<Book> books = bookDao.findBookByNameContaining("红");
        System.out.println(books);
    }
    @Test
    void findAllBookTest() {
        List<Book> bookList = bookDao.findAll();
        System.out.println(bookList);
    }



    @Test
    public void test2() {
        Book book = new Book();
        book.setId(3);
        book.setName("水浒传");
        book.setAuthor("施耐庵");
        mongoTemplate.insert(book);

        List<Book> list = mongoTemplate.findAll(Book.class);
        System.out.println(list);
    }
}