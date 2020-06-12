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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfm.FragmentCommunicator;
import com.example.lastfm.R;
import com.example.lastfm.adapter.ItemTopTagsAdapter;
import com.example.lastfm.topTagsModel.TagItem;
import com.example.lastfm.utils.Utils;
import com.example.lastfm.viewModel.TagListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopTagListFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private ItemTopTagsAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TagItem> mTopTagItems;
    private static final String TAG = "TopTagListFragment";
    // Will be used only in landscape mode
    private static FragmentCommunicator sFragmentCommunicator;

    public TopTagListFragment(){
        mTopTagItems = new ArrayList<>();
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
        Log.i(TAG,"onActivityCreated() ");
        final TagListViewModel  tagListViewModel = ViewModelProviders.of(getActivity()).get(TagListViewModel.class);
        observeViewModel(tagListViewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tag_list_fragment, container, false);
        Log.i(TAG,"onCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Build recycler view
        Log.i(TAG,"onViewCreated() ");
        buildRecyclerView(view);
    }

    private void buildRecyclerView(View view){
        Log.i(TAG,"buildRecyclerView() ");
        mRecyclerView = view.findViewById(R.id.top_tags_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);

        mRecyclerAdapter = new ItemTopTagsAdapter(mTopTagItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        Log.i(TAG,"setOnItemClickListener registered ");
        mRecyclerAdapter.setOnItemClickListener(new ItemTopTagsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                // Handel onclick
                TagItem tagItem = mTopTagItems.get(position);
                Log.i(TAG,"List view item clicked "+tagItem.getName()+" Position: "+position);

                if(Utils.isLandscape){
                    Log.i(TAG,"sFragmentCommunicator Called !!");
                    if(sFragmentCommunicator != null) {
                        Log.i(TAG,"sFragmentCommunicator Called !!"+tagItem.getName());
                        sFragmentCommunicator.setTag(tagItem.getName());
                    }
                }else {
                    // Send Tag to Albums fragment for API query
                    final Bundle bundle = new Bundle();
                    bundle.putString("tag", tagItem.getName());

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_toptags_to_topalbums, bundle);
                }
                // Clear sub-element [Albums ]selection when user changes selection on  Item list [Tags]
                Utils.clearState =  true;
            }
        });
    }

    private void observeViewModel(TagListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getTopTagsListObservable().observe(getViewLifecycleOwner(), new Observer<List<TagItem>>() {
            @Override
            public void onChanged(@Nullable List<TagItem> tagItems) {
                if (tagItems != null) {
                    mTopTagItems = tagItems;
                    mRecyclerAdapter.setTagItems(tagItems);
                    mRecyclerAdapter.notifyDataSetChanged();
                    Log.i(TAG,"observeViewModel onChanged()");
                }
            }
        });
    }

    public void setFragmentCommunicator(FragmentCommunicator fragmentCommunicator){
        sFragmentCommunicator = fragmentCommunicator;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sFragmentCommunicator = null;
    }
}
