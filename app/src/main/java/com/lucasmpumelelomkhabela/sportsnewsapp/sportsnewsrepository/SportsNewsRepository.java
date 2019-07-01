package com.lucasmpumelelomkhabela.sportsnewsapp.sportsnewsrepository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lucasmpumelelomkhabela.sportsnewsapp.ApiGateway;
import com.lucasmpumelelomkhabela.sportsnewsapp.models.SportArticleResponseModel;
import com.lucasmpumelelomkhabela.sportsnewsapp.models.SportNewsResponseModel;
import com.lucasmpumelelomkhabela.sportsnewsapp.services.SportsNewsServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsNewsRepository {

    @SuppressLint("StaticFieldLeak")
    private static SportsNewsRepository ourInstance;
    private SportsNewsServices mSportsNewsServices;
    private MutableLiveData<List<SportNewsResponseModel>> sportNewsResponseModelLiveData;
    private List<SportNewsResponseModel> body;
    private MutableLiveData<SportArticleResponseModel> sportArticleResponseModelLiveData;

    public static SportsNewsRepository getInstance(Context context) {
        if (ourInstance == null) ourInstance = new SportsNewsRepository(context);
        return ourInstance;
    }

    public SportsNewsRepository(Context context) {

        mSportsNewsServices = ApiGateway.getSportsNewsCall();
        sportNewsResponseModelLiveData = new MutableLiveData<>();
        sportArticleResponseModelLiveData = new MutableLiveData<>();
    }

    public void getSportNews() {

        Call<List<SportNewsResponseModel>> apiCall = mSportsNewsServices.getSportsNews();
        apiCall.enqueue(new Callback<List<SportNewsResponseModel>>() {
            @Override
            public void onResponse(Call<List<SportNewsResponseModel>> call, Response<List<SportNewsResponseModel>> response) {

                if (response.code() == 200) {
                    body = response.body();
                    sportNewsResponseModelLiveData.postValue(body);
                } else {
//                    Timber.i("MyMessage: %s", apiErrorResponse.getErrorMessage());
                }

                Log.d("Json1", new Gson().toJson(body));
            }

            @Override
            public void onFailure(Call<List<SportNewsResponseModel>> call, Throwable t) {

                Log.d("Failed -->", t.getMessage());

            }
        });

    }

    public MutableLiveData<List<SportNewsResponseModel>> getSportNewsResponseModeLiveData() {
        return sportNewsResponseModelLiveData;
    }
}
