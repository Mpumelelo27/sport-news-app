package com.lucasmpumelelomkhabela.sportsnewsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasmpumelelomkhabela.sportsnewsapp.R;

/**
 * Author LucasMpumeleloMkhabela
 */
public class SportsArticleFragment extends BaseFragment {

    public static final String TAG = SportsArticleFragment.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sports_article, container, false);
    }

}
