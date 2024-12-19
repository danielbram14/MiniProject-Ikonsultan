package com.evo.miniproject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitHttpCall {
    protected OkHttpClient okHttpClient;
    protected Retrofit client;

    private RetrofitHttpCall(String urlApi) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Cache-Control", "no-cache"); // <-- this is the important line

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        okHttpClient = httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        client = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
                .client(okHttpClient)
                .build();
    }

    public static RetrofitHttpCall getInstance(String urlApi) {
        return new RetrofitHttpCall(urlApi);
    }


    public <T> T create(final Class<T> service) {
        return client.create(service);
    }


}
