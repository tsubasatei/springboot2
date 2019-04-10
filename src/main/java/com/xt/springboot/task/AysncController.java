package com.xt.springboot.task;

import com.xt.springboot.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xt
 * @create 2019/4/10 9:04
 * @Desc
 */
@RestController
public class AysncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello() {
        asyncService.hello();
        return "SUCCESS";
    }
}
