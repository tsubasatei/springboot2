package com.xt.springboot.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xt
 * @create 2019/4/10 9:02
 * @Desc
 */
@Service
public class AsyncService {

    @Async
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理中。。。。");
    }
}
