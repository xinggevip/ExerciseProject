package com.example;

import com.example.entity.Article;
import com.example.entity.Book;
import com.example.repostry.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringBootElastic01ApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Autowired
    BookRepository bookRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void saveRow() {
        // 给Es中索引（保存）一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("创业为了什么");
        article.setAuthor("安生");
        article.setContent("宇宙大爆炸的那一刻起，就决定了你要创业");
        // 构建一个索引功能
        Index index = new Index.Builder(article).index("xing").type("article").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void simpleSearch() {
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"大爆炸\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(json).addIndex("xing").addType("article").build();

        // 执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO 无节点可用异常 待解决 可能因为springboot 和 elasticSearch版本不匹配
    @Test
    void saveRow2() {
        Book book = new Book(1, "煤老板字数三十年", "钻石王老五");
        bookRepository.index(book);
    }

    @Test
    public void testSearch(){
        for (Book book : bookRepository.findByBookNameLike("煤老板")) {
            System.out.println(book);
        }
    }

}
