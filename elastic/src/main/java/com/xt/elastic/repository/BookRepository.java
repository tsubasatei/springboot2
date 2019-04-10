package com.xt.elastic.repository;

import com.xt.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author xt
 * @create 2019/4/10 8:17
 * @Desc
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    //参照
    // https://docs.spring.io/spring-data/elasticsearch/docs/3.0.6.RELEASE/reference/html/
    List<Book> findByBookNameLike(String bookName);
}
