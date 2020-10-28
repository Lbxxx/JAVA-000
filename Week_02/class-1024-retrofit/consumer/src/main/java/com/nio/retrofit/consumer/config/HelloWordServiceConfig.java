package com.nio.retrofit.consumer.config;


import com.nio.retrofit.consumer.service.EndpointCreator;
import com.nio.retrofit.consumer.service.HelloWordService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "provider",ignoreInvalidFields=true)
public class HelloWordServiceConfig {

    @Bean
    public HelloWordService helloWordService() {
        return EndpointCreator.create(url, HelloWordService.class);
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
