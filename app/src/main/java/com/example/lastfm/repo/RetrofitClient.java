package com.example.lastfm.repo;

import com.example.lastfm.utils.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {
    private static Retrofit mRetrofit;

    private RetrofitClient() {
        //Nothing TODO
    }

    public static Retrofit getRetrofitBuilder() {
        if (mRetrofit == null) { //Check for the first time
            synchronized (Retrofit.class) {
                //Check for the second time.
                // if there is no instance available... create new one
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(Utils.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }
}
