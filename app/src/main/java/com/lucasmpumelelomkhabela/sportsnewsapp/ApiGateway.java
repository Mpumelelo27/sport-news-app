package com.lucasmpumelelomkhabela.sportsnewsapp;

import android.util.Log;

import com.lucasmpumelelomkhabela.sportsnewsapp.services.SportsNewsServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGateway {

    public static SportsNewsServices getSportsNewsCall() {
        Log.d("Called", "getSportsNewsCall");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constance.SPORTS_NEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SportsNewsServices.class);
    }
}
