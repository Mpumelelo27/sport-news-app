package com.lucasmpumelelomkhabela.sportsnewsapp.sportsnewsviewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lucasmpumelelomkhabela.sportsnewsapp.models.SportNewsResponseModel;
import com.lucasmpumelelomkhabela.sportsnewsapp.sportsnewsrepository.SportsNewsRepository;

import java.util.List;

import androidx.annotation.NonNull;

public class SportsNewsViewModel extends AndroidViewModel {

    private SportsNewsRepository sportsNewsRepository;
    private LiveData<List<SportNewsResponseModel>> sportNewsResponseModelLiveData;

    public SportsNewsViewModel(@NonNull Application application) {
        super(application);
        sportsNewsRepository = SportsNewsRepository.getInstance(application.getApplicationContext());
    }

    public void getSportNews() {
        sportsNewsRepository.getSportNews();
    }

    public LiveData<List<SportNewsResponseModel>> getsportNewsResponseModeLiveData() {

        if (sportNewsResponseModelLiveData == null) {
            sportNewsResponseModelLiveData = new MutableLiveData<>();
            sportNewsResponseModelLiveData = sportsNewsRepository.getSportNewsResponseModeLiveData();
        }

        return sportNewsResponseModelLiveData;
    }

}
