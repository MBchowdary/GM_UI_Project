package com.example.lastfm.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lastfm.utils.Utils;
import com.example.lastfm.api.TopAlbumsApi;
import com.example.lastfm.topAlbumsModel.AlbumItem;
import com.example.lastfm.topAlbumsModel.Albums;
import com.example.lastfm.topAlbumsModel.TopAlbumsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopAlbumsRepository {
    private TopAlbumsApi mTopAlbumsApi;
    private static TopAlbumsRepository sTopAlbumsRepository;
    private static final String TAG = "ABC";

    private TopAlbumsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTopAlbumsApi = retrofit.create(TopAlbumsApi.class);
    }

    public synchronized static TopAlbumsRepository getInstance() {
        if (sTopAlbumsRepository == null) {
            if (sTopAlbumsRepository == null) {
                sTopAlbumsRepository = new TopAlbumsRepository();
            }
        }
        Log.i(TAG, "TopAlbumsRepository getInstance() ");
        return sTopAlbumsRepository;
    }

    public LiveData<List<AlbumItem>> getTopAlbumsList(String tag) {
        final MutableLiveData<List<AlbumItem>> albumsList = new MutableLiveData<>();

        final Call<TopAlbumsResponse> call = mTopAlbumsApi.getTopAlbums("tag.gettopalbums", tag, Utils.API_KEY, Utils.FORMAT);
        call.enqueue(new Callback<TopAlbumsResponse>() {
            @Override
            public void onResponse(Call<TopAlbumsResponse> call, Response<TopAlbumsResponse> response) {
                if (!response.isSuccessful()) {
                    // If response is not successful
                    Log.i(TAG, "onResponse() not Successful" + response.code());
                    return;
                }
                Log.i(TAG, "onResponse() Successful");
                TopAlbumsResponse topAlbumsResponse = response.body();
                Albums albums = topAlbumsResponse.getAlbums();
                albumsList.setValue(albums.getAlbum());
            }

            @Override
            public void onFailure(Call<TopAlbumsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure()" + t.getMessage());
                albumsList.setValue(null);
            }
        });
        return albumsList;
    }
}
