package com.mmk.unsplashapp.api;

import com.mmk.unsplashapp.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInitHelper {

    private static Retrofit retrofit;

    private static ApiMethods.ImageList imageListService;
    private static ApiMethods.Download downloadService;



    private static OkHttpClient getOkHttpClient(){
         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        return client;

    }


    public ApiInitHelper initDefaultApi(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return this;
    }

    public ApiInitHelper createImageListService(){
        imageListService=retrofit.create(ApiMethods.ImageList.class);
        return this;
    }

    public ApiMethods.ImageList getImageListService(){
        return imageListService;
    }

    public ApiInitHelper createDownloadService(){
        downloadService=retrofit.create(ApiMethods.Download.class);
        return this;
    }
    public ApiMethods.Download getDownloadService(){return  downloadService;}



}
