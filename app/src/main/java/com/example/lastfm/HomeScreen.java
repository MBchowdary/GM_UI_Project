package com.example.lastfm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lastfm.ui.TopAlbumsListFragment;
import com.example.lastfm.ui.TopTagListFragment;

public class HomeScreen  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        attachFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    // Attach fragment

    private void attachFragment(){
        // Create a fragment instance
        //TopTagListFragment topTagListFragment = new TopTagListFragment();
        TopAlbumsListFragment topTagListFragment = new TopAlbumsListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, topTagListFragment);
        fragmentTransaction.commit();
    }
}
