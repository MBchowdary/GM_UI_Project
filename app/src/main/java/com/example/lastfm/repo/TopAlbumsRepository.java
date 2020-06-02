package com.example.lastfm.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lastfm.api.TopAlbumsApi;
import com.example.lastfm.topAlbumsModel.AlbumItem;
import com.example.lastfm.topAlbumsModel.Albums;
import com.example.lastfm.topAlbumsModel.TopAlbumsResponse;
import com.example.lastfm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TopAlbumsRepository {
    private TopAlbumsApi mTopAlbumsApi;
    private static TopAlbumsRepository sTopAlbumsRepository;
    private static final String TAG = "TopAlbumsRepository";

    private TopAlbumsRepository() {
        final Retrofit retrofit = RetrofitClient.getRetrofitBuilder();
        mTopAlbumsApi = retrofit.create(TopAlbumsApi.class);
    }

    public static TopAlbumsRepository getInstance() {
        if (sTopAlbumsRepository == null) { //Check for the first time
            synchronized (TopAlbumsRepository.class) {
                //Check for the second time.
                // if there is no instance available... create new one
                if (sTopAlbumsRepository == null) {
                    sTopAlbumsRepository = new TopAlbumsRepository();
                }
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
