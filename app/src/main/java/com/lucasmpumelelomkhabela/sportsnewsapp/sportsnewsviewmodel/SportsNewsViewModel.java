package com.lucasmpumelelomkhabela.sportsnewsapp.sportsnewsviewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lucasmpumelelomkhabela.sportsnewsapp.core.Content;
import com.lucasmpumelelomkhabela.sportsnewsapp.models.SportArticleResponseModel;
import com.lucasmpumelelomkhabela.sportsnewsapp.models.SportNewsResponseModel;
import com.lucasmpumelelomkhabela.sportsnewsapp.sportsnewsrepository.SportsNewsRepository;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Author LucasMpumeleloMkhabela
 */
public class SportsNewsViewModel extends AndroidViewModel {

    private SportsNewsRepository sportsNewsRepository;
    private LiveData<List<SportNewsResponseModel>> sportNewsResponseModelLiveData;
    private LiveData<SportArticleResponseModel> sportArticleResponseModelLiveData;

    public SportsNewsViewModel(@NonNull Application application) {
        super(application);
        sportsNewsRepository = SportsNewsRepository.getInstance(application.getApplicationContext());
    }

    public void getSportNews() {
        sportsNewsRepository.getSportNews();
    }

    public LiveData<List<SportNewsResponseModel>> getSportNewsResponseModeLiveData() {

        if (sportNewsResponseModelLiveData == null) {
            sportNewsResponseModelLiveData = new MutableLiveData<>();
            sportNewsResponseModelLiveData = sportsNewsRepository.getSportNewsResponseModeLiveData();
        }

        return sportNewsResponseModelLiveData;
    }

    public void getSportsArticles(String siteName, String urlName, String urlFriendlyDate, String urlFriendlyHeadline) {
        sportsNewsRepository.getSportsArticles(siteName, urlName, urlFriendlyDate, urlFriendlyHeadline);
    }

    public LiveData<SportArticleResponseModel> getSportArticleResponseModeLiveData() {

        if (sportArticleResponseModelLiveData == null) {
            sportArticleResponseModelLiveData = new MutableLiveData<>();
            sportArticleResponseModelLiveData = sportsNewsRepository.getSportArticleResponseModeLiveData();
        }
        return sportArticleResponseModelLiveData;
    }

    public MutableLiveData<Content> getNetworkFailure() {
        return sportsNewsRepository.getNetworkFailure();
    }

    public void clearLiveData() {
        sportsNewsRepository.clearLiveData();
    }

}
