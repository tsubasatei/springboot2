package com.xt.springboot.cache.config;


import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author xt
 * @create 2019/4/8 7:08
 * @Desc
 */
@Configuration
public class MyCacheConfig {
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> method.getName() + "[" + Arrays.asList(objects).toString() + "]";
    }
}
