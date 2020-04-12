package com.mmk.unsplashapp.network;



import com.mmk.unsplashapp.network.responses.SearchResponse;
import com.mmk.unsplashapp.utils.Common;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface UnsplashApiService {

    @Headers("Authorization: Client-ID "+ Common.API_KEY)
    @GET("search/photos")
    Call<SearchResponse> search(@Query("query") String query,
                                @Query("page") String pageNumber);

    @Headers("Authorization: Client-ID "+ Common.API_KEY)
    @GET("photos")
    Call<List<SearchResponse.Result>> loadPhotos(@Query("page") String pageNumber);

    @GET
    Call<ResponseBody> downloadImage(@Url String downloadUrl);

}
