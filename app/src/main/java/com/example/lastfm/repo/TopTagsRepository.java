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
import retrofit2.converter.gson.GsonConverterFactory;

// This class will provide live data to Model View by interacting with API
public class TopTagsRepository {
    private TopTagsApi mTopTagsApi;
    private static TopTagsRepository sTopTagsRepository;
    private static final String TAG = "ABC";

    private TopTagsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTopTagsApi = retrofit.create(TopTagsApi.class);
    }

    public synchronized static TopTagsRepository getInstance() {
        if (sTopTagsRepository == null) {
            if (sTopTagsRepository == null) {
                sTopTagsRepository = new TopTagsRepository();
            }
        }
        Log.i(TAG,"TopTagsRepository getInstance() ");
        return sTopTagsRepository;
    }

    public LiveData<List<TagItem>> getTopTagsList() {
        final MutableLiveData<List<TagItem>> tagItems = new MutableLiveData<>();

        final Call<TopTagsResponse> call = mTopTagsApi.getTopTags("tag.getTopTags",Utils.API_KEY,Utils.FORMAT);
        call.enqueue(new Callback<TopTagsResponse>() {
            @Override
            public void onResponse(Call<TopTagsResponse> call, Response<TopTagsResponse> response) {
                if(!response.isSuccessful()){
                    // If response is not successful
                    Log.i(TAG,"onResponse() not Successful"+response.code());
                    return;
                }
                Log.i(TAG,"onResponse() Successful");
                TopTagsResponse topTagsResponse = response.body();
                Toptags toptags = topTagsResponse.getToptags();
                tagItems.setValue(toptags.getTag());
            }
            @Override
            public void onFailure(Call<TopTagsResponse> call, Throwable t) {
                Log.i(TAG,"onFailure()"+t.getMessage());
                tagItems.setValue(null);
            }
        });
        return tagItems;
    }
}