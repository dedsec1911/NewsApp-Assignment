package com.dedsec.newsappassignment.network;

import  android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import com.dedsec.newsappassignment.model.Article;
import com.dedsec.newsappassignment.model.News;
import com.dedsec.newsappassignment.remote.NewsServiceApi;
import com.dedsec.newsappassignment.remote.RetrofitClient;
import com.dedsec.newsappassignment.utils.AppModule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    MutableLiveData<List<Article>> mutableLiveData;
    private Application application;
    private AppModule appModule = new AppModule();

    public final static String BASE_URL = "https://newsapi.org/v2/";

    String news_url = "top-headlines?country=in&category=business&apiKey=26ad96b0af72499691d4f0ec22f72b32";

    public NewsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Article>> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        NewsServiceApi serviceApi = RetrofitClient.getClient(BASE_URL).create(NewsServiceApi.class);
        serviceApi.getAllNews(news_url)
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                        if (response.isSuccessful()){
                            News newsRes = response.body();
                            assert newsRes != null;
                            Log.d("RESPONSE", newsRes.getStatus());
                            List<Article> articleList = newsRes.getArticles();
                            mutableLiveData.setValue(articleList);

                            appModule.provideNewsDao(appModule.provideRoomDatabase(application)).deleteArticles();
                            appModule.provideNewsDao(appModule.provideRoomDatabase(application)).insertArticles(articleList);

                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<News> call, @NotNull Throwable t) {
                        Toast.makeText(application, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getOfflineData() {
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        List<Article> articleList = appModule.provideNewsDao(appModule.provideRoomDatabase(application)).getArticles();
        mutableLiveData.setValue(articleList);

        return mutableLiveData;
    }

}
