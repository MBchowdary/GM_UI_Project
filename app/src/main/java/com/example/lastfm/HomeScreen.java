package com.example.lastfm;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lastfm.ui.TopTagListFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TopTagListFragment.OnFragmentInteractionListener {

    private static final String TAG = "ABC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.i(TAG,"onAttachFragment() ");
        if (fragment instanceof TopTagListFragment) {
            TopTagListFragment topTagListFragment = (TopTagListFragment) fragment;
            topTagListFragment.setOnFragmentInteractionListener(this);
            Log.i(TAG,"onAttachFragment() listener set ");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(String tag) {
    Log.i(TAG,"onFragmentInteraction: "+tag);
    }

}
