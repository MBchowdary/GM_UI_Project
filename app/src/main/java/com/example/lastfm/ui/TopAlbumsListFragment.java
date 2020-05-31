package com.example.lastfm.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfm.R;
import com.example.lastfm.adapter.ItemTopAlbumsAdapter;
import com.example.lastfm.topAlbumsModel.AlbumItem;
import com.example.lastfm.viewModel.AlbumsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopAlbumsListFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private ItemTopAlbumsAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<AlbumItem> mAlbumItems;
    private static final String TAG = "ABC";

    public TopAlbumsListFragment() {
        mAlbumItems = new ArrayList<>();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated() ");
        final AlbumsListViewModel albumsListViewModel = ViewModelProviders.of(getActivity()).get(AlbumsListViewModel.class);
        observeViewModel(albumsListViewModel, "disco");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.albums_list_fragment, container, false);
        Log.i(TAG, "onCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Build recycler view
        Log.i(TAG, "onViewCreated() ");
        buildRecyclerView(view);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void buildRecyclerView(View view) {
        Log.i(TAG, "buildRecyclerView() ");
        mRecyclerView = view.findViewById(R.id.top_albums_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);

        mRecyclerAdapter = new ItemTopAlbumsAdapter(mAlbumItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        Log.i(TAG, "setOnItemClickListener registered ");
        mRecyclerAdapter.setOnItemClickListener(new ItemTopAlbumsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                // Handel onclick
                AlbumItem albumItem = mAlbumItems.get(position);
                Log.i(TAG, "List view item clicked " + albumItem.getName() + " Position: " + position);
                mRecyclerAdapter.notifyItemChanged(position);
            }
        });
    }

    private void observeViewModel(AlbumsListViewModel viewModel, String tag) {
        // Update the list when the data changes
        viewModel.getTopAlbumsListObservable(tag).observe(getViewLifecycleOwner(), new Observer<List<AlbumItem>>() {
            @Override
            public void onChanged(@Nullable List<AlbumItem> albumItems) {
                if (albumItems != null) {
                    mAlbumItems = albumItems;
                    mRecyclerAdapter.setAlbumItems(albumItems);
                    mRecyclerAdapter.notifyDataSetChanged();
                    Log.i(TAG, "observeViewModel onChanged()");
                }
            }
        });
    }

}
