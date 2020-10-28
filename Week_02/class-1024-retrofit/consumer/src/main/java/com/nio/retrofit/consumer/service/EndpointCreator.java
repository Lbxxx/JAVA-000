package com.nio.retrofit.consumer.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EndpointCreator {
    private static class ClientBuilderHolder {
        static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
    }

    public static class ClientHolder {
        public static final OkHttpClient CLIENT = ClientBuilderHolder.BUILDER.build();
    }

    private static class ConverterFactoryHolder {
        static final GsonConverterFactory FACTORY = GsonConverterFactory.create();
    }

    private EndpointCreator() {
    }

    public static <T> T create(String baseUrl, Class<T> serviceClass) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ConverterFactoryHolder.FACTORY);
        Retrofit retrofit = builder.client(ClientHolder.CLIENT).build();
        return retrofit.create(serviceClass);
    }
}
