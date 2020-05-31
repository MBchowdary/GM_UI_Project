package com.example.lastfm.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lastfm.TopTagsRepository;
import com.example.lastfm.topTagsModel.TagItem;

import java.util.List;

public class TagListViewModel  extends AndroidViewModel {
    private final LiveData<List<TagItem>> mTopTagsListObservable;
    private static final String TAG = "ABC";

    public TagListViewModel(Application application) {
        super(application);
        Log.i(TAG,"TagListViewModel() ");
        mTopTagsListObservable = TopTagsRepository.getInstance().getTopTagsList();
    }

    /**
     * Expose the LiveData TopTags query so the UI can observe it.
     */
    public LiveData<List<TagItem>> getTopTagsListObservable() {
        Log.i(TAG,"getTopTagsListObservable() ");
        return mTopTagsListObservable;
    }
}
