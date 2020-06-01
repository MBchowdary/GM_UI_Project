package com.example.lastfm;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lastfm.ui.TopTagListFragment;
import com.example.lastfm.utils.Utils;
import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TopTagListFragment.OnFragmentInteractionListener {

    private static final String TAG = "HomeScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        isTablet(this);

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

    public static void isTablet(Context context) {
        Utils.isLandscape =  (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
