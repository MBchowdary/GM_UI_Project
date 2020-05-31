package com.example.lastfm.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lastfm.repo.TopAlbumsRepository;
import com.example.lastfm.topAlbumsModel.AlbumItem;

import java.util.List;

public class AlbumsListViewModel extends AndroidViewModel {
   private LiveData<List<AlbumItem>> mTopAlbumsListObservable;
    private String mTag;
    private static final String TAG = "ABC";

    public AlbumsListViewModel(Application application) {
        super(application);
        Log.i(TAG,"AlbumsListViewModel() ");
    }

    /**
     * Expose the LiveData TopAlbums query so the UI can observe it.
     */
    public LiveData<List<AlbumItem>> getTopAlbumsListObservable(String tag) {
        Log.i(TAG,"getTopAlbumsListObservable() ");
        if(mTag != tag){
            mTag = tag;
            getAlbumsListObservable();
        }
        return mTopAlbumsListObservable;
    }

    private void getAlbumsListObservable(){
        mTopAlbumsListObservable = TopAlbumsRepository.getInstance().getTopAlbumsList(mTag);
    }
}
