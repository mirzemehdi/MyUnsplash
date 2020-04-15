package com.mmk.unsplashapp.intractor;

import com.mmk.unsplashapp.api.ApiInitHelper;
import com.mmk.unsplashapp.pojo.ResponseSearchPicturesPOJO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class PhotosIntractor {

    private ApiInitHelper apiInitHelper;

    public PhotosIntractor() {
        apiInitHelper=new ApiInitHelper().initDefaultApi();
    }

    public void loadPhotos(String pageNumber, Callback<List<ResponseSearchPicturesPOJO.Result>> callbackResult) {

        apiInitHelper
                .createImageListService()
                .getImageListService()
                .loadPhotos(pageNumber)
                .enqueue(callbackResult);
    }

    public void searchPhotos(String query, String pageNumber, Callback<ResponseSearchPicturesPOJO> callback) {
        apiInitHelper
                .createImageListService()
                .getImageListService()
                .search(query, pageNumber)
                .enqueue(callback);
    }

    public void downloadPhoto(String imageUrl, Callback<ResponseBody> callback) {
        apiInitHelper
                .createDownloadService()
                .getDownloadService()
                .downloadImage(imageUrl)
                .enqueue(callback);
    }


}
