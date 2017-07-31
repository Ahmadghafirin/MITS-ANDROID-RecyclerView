package com.example.ahmad.w.fitur;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.ahmad.w.R;

/**
 * Created by ahmad on 30/07/17.
 */

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int PAGE_HOME = 0, PAGE_FAVORITE = 1, PAGE_PROFILE = 2;

    private HomeFragment homeFragment;
    private FavoriteFragment favoriteFragment;
    private ProfileFragment profileFragment;
    private ActionBar actionBar;

    private ImageView ivHome, ivFavorite, ivProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        homeFragment = new HomeFragment();
        favoriteFragment = new FavoriteFragment();
        profileFragment = new ProfileFragment();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void setActionBarTitle(String favorite) {
        actionBar.setTitle(favorite);
    }
}
