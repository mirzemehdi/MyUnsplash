package com.mmk.unsplashapp.intractor;

import android.util.Log;

import com.mmk.unsplashapp.network.UnsplashApi;
import com.mmk.unsplashapp.network.responses.SearchResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosIntractor {

        public void loadPhotos(String pageNumber, Callback<List<SearchResponse.Result>> callbackResult){
            UnsplashApi.getService()
                    .loadPhotos(pageNumber)
                    .enqueue(callbackResult);
        }

        public void searchPhotos(String query,String pageNumber,Callback<SearchResponse>callback){
            UnsplashApi.getService()
                    .search(query,pageNumber)
                    .enqueue(callback);
        }
        public void downloadPhoto(String imageUrl, Callback<ResponseBody> callback){
            UnsplashApi.getService()
                    .downloadImage(imageUrl)
                    .enqueue(callback);
        }




}
