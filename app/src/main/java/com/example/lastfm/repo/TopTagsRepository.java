package com.example.lastfm.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lastfm.api.TopTagsApi;
import com.example.lastfm.topTagsModel.TagItem;
import com.example.lastfm.topTagsModel.TopTagsResponse;
import com.example.lastfm.topTagsModel.Toptags;
import com.example.lastfm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// This class will provide live data to Model View by interacting with API
public class TopTagsRepository {
    private TopTagsApi mTopTagsApi;
    private static TopTagsRepository sTopTagsRepository;
    private static final String TAG = "TopTagsRepository";

    private TopTagsRepository() {
        final Retrofit retrofit = RetrofitClient.getRetrofitBuilder();
        mTopTagsApi = retrofit.create(TopTagsApi.class);
    }

    public static TopTagsRepository getInstance() {
        if (sTopTagsRepository == null) { //Check for the first time
            synchronized (TopTagsRepository.class) {
                //Check for the second time.
                // if there is no instance available... create new one
                if (sTopTagsRepository == null) {
                    sTopTagsRepository = new TopTagsRepository();
                }
            }
        }
        Log.i(TAG, "TopTagsRepository getInstance() ");
        return sTopTagsRepository;
    }

    public LiveData<List<TagItem>> getTopTagsList() {
        final MutableLiveData<List<TagItem>> tagItems = new MutableLiveData<>();

        final Call<TopTagsResponse> call = mTopTagsApi.getTopTags("tag.getTopTags", Utils.API_KEY, Utils.FORMAT);
        call.enqueue(new Callback<TopTagsResponse>() {
            @Override
            public void onResponse(Call<TopTagsResponse> call, Response<TopTagsResponse> response) {
                if (!response.isSuccessful()) {
                    // If response is not successful
                    Log.i(TAG, "onResponse() not Successful" + response.code());
                    return;
                }
                Log.i(TAG, "onResponse() Successful");
                TopTagsResponse topTagsResponse = response.body();
                Toptags toptags = topTagsResponse.getToptags();
                tagItems.setValue(toptags.getTag());
            }

            @Override
            public void onFailure(Call<TopTagsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure()" + t.getMessage());
                tagItems.setValue(null);
            }
        });
        return tagItems;
    }
}