package com.xt.rabbitmq.service;

import com.xt.rabbitmq.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author xt
 * @create 2019/4/9 18:29
 * @Desc
 */
@Service
public class BookService {

    @RabbitListener(queues = {"atguigu.news"})
    public void receive(Book book) {
        System.out.println("收到消息：" + book);
    }

    @RabbitListener(queues = {"gulixueyuan.news"})
    public void receive02(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
