package com.lucasmpumelelomkhabela.sportsnewsapp;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.lucasmpumelelomkhabela.sportsnewsapp.fragments.SportsArticleFragment;
import com.lucasmpumelelomkhabela.sportsnewsapp.fragments.SportsNewsFragments;

public class MainActivity extends AppCompatActivity {

    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportActionBar = getSupportActionBar();
        replaceFragment(new SportsNewsFragments(), SportsNewsFragments.TAG);
    }

    public void hideBackButton() {
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void showBackButton() {
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    private void replaceFragment(Fragment fragment, String backStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack(backStack);
        fragmentTransaction.replace(R.id.sports_news_container, fragment, backStack);
        fragmentTransaction.commit();
    }

    public void changeTitle(String title) {
        if (supportActionBar != null) supportActionBar.setTitle(title);
    }

    public void actionBarColor() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGradientStart)));
        }
    }

    @Override
    public void onBackPressed() {
        validateOnBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void validateOnBackPressed() {

        if (getSupportFragmentManager().findFragmentById(R.id.sports_news_container) instanceof SportsArticleFragment) {
            getSupportFragmentManager().popBackStack();
            replaceFragment(new SportsNewsFragments(), SportsNewsFragments.TAG);
        } else if (getSupportFragmentManager().findFragmentById(R.id.sports_news_container) instanceof SportsNewsFragments) {
            this.finish();
        }
    }
}
