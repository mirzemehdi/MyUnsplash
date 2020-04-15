package com.mmk.unsplashapp.api;



import com.mmk.unsplashapp.pojo.ResponseSearchPicturesPOJO;
import com.mmk.unsplashapp.utils.Constants;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiMethods {

    interface ImageList {
        @Headers("Authorization: Client-ID " + Constants.API_KEY)
        @GET("search/photos")
        Call<ResponseSearchPicturesPOJO> search(@Query("query") String query,
                                                @Query("page") String pageNumber);

        @Headers("Authorization: Client-ID " + Constants.API_KEY)
        @GET("photos")
        Call<List<ResponseSearchPicturesPOJO.Result>> loadPhotos(@Query("page") String pageNumber);
    }

    interface Download {
        @GET
        Call<ResponseBody> downloadImage(@Url String downloadUrl);
    }

}
