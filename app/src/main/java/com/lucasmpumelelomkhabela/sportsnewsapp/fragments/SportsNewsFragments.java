package com.lucasmpumelelomkhabela.sportsnewsapp.fragments;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lucasmpumelelomkhabela.sportsnewsapp.R;
import com.lucasmpumelelomkhabela.sportsnewsapp.sportsnewsviewmodel.SportsNewsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: LucasMpumeleloMkhabela
 */
public class SportsNewsFragments extends BaseFragment {

    public static final String TAG = SportsArticleFragment.class.getName();
    private SportsNewsViewModel mSportsNewsViewModel;

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textViewSportName)
    TextView textViewSportName;
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.textViewSubtitle)
    TextView textViewSubtitle;

    Button button;

    View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSportsNewsViewModel = ViewModelProviders.of(this).get(SportsNewsViewModel.class);
        changeTitle("News");
        actionBarColor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (isNetworkAvailable()) {
            Log.d("Wifi On", "On");
            mSportsNewsViewModel.getSportNews();
            rootView = inflater.inflate(R.layout.fragment_sports_news_fragments, container, false);

            ButterKnife.bind(this, rootView);

            mSportsNewsViewModel.getsportNewsResponseModeLiveData().observe(this, model -> {
                if (model != null) {

                    textViewSportName.setText(model.get(0).siteName);
                    textViewTitle.setText(model.get(0).headline);
                    textViewSubtitle.setText(model.get(0).urlFriendlyDate);

//                    String url = model.get(0).imageUrlLocal;
//                    Glide
//                            .with(this)
//                            .load(url)
//                            .centerCrop()
//                            .placeholder(R.drawable.ic_launcher_background)
//                            .into(imageView);
                }
                Log.d("Json--> ", new Gson().toJson(model));

            });
        } else {
            Log.d("Wifi On", "Off");
            showErrorResponse(getActivity(), "No network", "Please check your settings to enable your network.", (dialogInterface, i) -> setButton());
        }
        return rootView;

    }

    public void setButton() {

    }

}
