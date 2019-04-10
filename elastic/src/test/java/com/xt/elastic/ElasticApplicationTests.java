package com.xt.elastic;

import com.xt.elastic.bean.Article;
import com.xt.elastic.bean.Book;
import com.xt.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticApplicationTests {

    @Autowired
    JestClient jestClient;
    @Autowired
    BookRepository bookRepository;


    // 版本有问题？回头记得查找
    @Test
    public void test () {
//        Book book = new Book();
//		book.setId(1);
//		book.setBookName("西游记");
//		book.setAuthor("吴承恩");
//		bookRepository.index(book);

        for (Book book : bookRepository.findByBookNameLike("游")) {
            System.out.println(book);
        }
    }

    @Test
    public void contextLoads() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(2);
        article.setTitle("好消息2");
        article.setAuthor("zhangsan2");
        article.setContent("Hello World2");

        Index index = new Index.Builder(article).index("xt").type("news").build();

        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //测试搜索
    @Test
    public void search(){

        //查询表达式
        String json ="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("xt").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
