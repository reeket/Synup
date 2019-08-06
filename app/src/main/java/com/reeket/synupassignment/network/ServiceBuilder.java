package com.reeket.synupassignment.network;

import android.os.Build;

import com.reeket.synupassignment.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {


    //Create Logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Create Custom Interceptor

    private static Interceptor headerInterceptor = new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();

            request = request.newBuilder()
                    .build();

            Response response = chain.proceed(request);

            return response;
        }
    };

    //Create OkHttp Client
    private static OkHttpClient okHttpClient = new OkHttpClient
            .Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logger)
            .build();

    //Crete Retrofit Builder
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient);

    //Create Retrofit instance
    private static Retrofit retrofit = builder.build();

    public static Object buildService(@NotNull Class serviceType) {
        return retrofit.create(serviceType);
    }

}
