package com.nio.retrofit.consumer.controller;

import com.nio.retrofit.consumer.service.HelloWordService;
import com.nio.retrofit.provider.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;

@RestController
public class HelloWordController {


    @Autowired
    private HelloWordService helloWordService;


    @GetMapping("/hello")
    public User helloWord() throws IOException {
        Response<User> execute = helloWordService.hello().execute();
        if(execute.code() == HttpStatus.OK.value()) {
            return execute.body();
        }else {
            return null;
        }
    }

}
