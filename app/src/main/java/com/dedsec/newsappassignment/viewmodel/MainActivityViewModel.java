package com.dedsec.newsappassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import com.dedsec.newsappassignment.model.Article;
import com.dedsec.newsappassignment.network.NewsRepository;

public class MainActivityViewModel extends AndroidViewModel {

    NewsRepository newsRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<Article>> getAllArticle() {
        return newsRepository.getMutableLiveData();
    }

    public LiveData<List<Article>> getOfflineArticle() {
        return newsRepository.getOfflineData();
    }

}
