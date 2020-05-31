package com.example.lastfm.api;

import com.example.lastfm.topTagsModel.TopTagsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopTagsApi {
    /*@GET(".")
    Call<TopTagsResponse> getTopTags(@Query("method") String method,
                                     @Query("api_key") String api_key,
                                     @Query("format") String format);*/

    @GET("?method=tag.getTopTags&api_key=2ed040311b0d00a30f8a7a402b753103&format=json")
    Call<TopTagsResponse> getTopTags();
}
