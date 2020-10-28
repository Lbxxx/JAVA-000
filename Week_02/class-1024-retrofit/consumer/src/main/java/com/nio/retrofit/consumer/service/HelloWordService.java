package com.nio.retrofit.consumer.service;


import com.nio.retrofit.provider.bean.User;
import retrofit2.Call;
import retrofit2.http.GET;


public interface HelloWordService {
    @GET("/hello")
    Call<User> hello();
}
