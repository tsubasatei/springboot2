package com.xt.elastic.config;

import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xt
 * @create 2019/4/10 8:10
 * @Desc
 */
@Configuration
public class MyESConfig {

    @Value("${spring.elasticsearch.jest.uris}")
    private String uri;

    @Bean
    public HttpClientConfig httpClientConfig() {
        HttpClientConfig clientConfig = new HttpClientConfig.Builder(uri).readTimeout(20000).multiThreaded(true).build();
        return clientConfig;
    }
}
