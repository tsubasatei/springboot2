package com.xt.rabbitmq;

import com.xt.rabbitmq.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void test () {

        Exchange exchange = new DirectExchange("ampqadmin.direct");
        amqpAdmin.declareExchange(exchange);
        System.out.println("交换器创建完成");

        Queue queue = new Queue("amqpadmin.queue", true);
        amqpAdmin.declareQueue(queue);
        System.out.println("队列创建完成");

        Binding binding = new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "ampqadmin.direct", "amqo.msg", null);
        amqpAdmin.declareBinding(binding);
    }

    /**
     * 1、单播（点对点）
     */
    @Test
    public void contextLoads() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchange,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchange,routeKey,object);

        Map<String, Object> message = new HashMap<>();
        message.put("msg","这是第一个消息");
        message.put("data", Arrays.asList("helloworld",123,true));
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", message);
    }

    //接受数据,如何将数据自动的转为json发送出去
    @Test
    public void testReceive () {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass().getName());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void testCast () {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("西游记", "吴承恩"));
    }
}
