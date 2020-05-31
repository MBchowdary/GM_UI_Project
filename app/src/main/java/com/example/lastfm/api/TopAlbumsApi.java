package com.example.lastfm.api;

import com.example.lastfm.topAlbumsModel.TopAlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopAlbumsApi {

    @GET(".")
    Call<TopAlbumsResponse> getTopAlbums(
            @Query("method") String method,
            @Query("tag") String tag,
            @Query("api_key") String api_key,
            @Query("format") String format);
}
