package com.nio.retrofit.provider.controller;

import com.nio.retrofit.provider.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloWordController {



    @GetMapping("/hello")
    public User helloWord2() throws IOException {
        User user = new User();
        user.setAge(25);
        user.setName("林博轩");
        return user;
    }

}
