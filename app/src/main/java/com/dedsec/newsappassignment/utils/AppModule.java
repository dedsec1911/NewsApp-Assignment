package com.dedsec.newsappassignment.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.room.Room;
import com.dedsec.newsappassignment.database.NewsDao;
import com.dedsec.newsappassignment.database.NewsDatabase;

public class AppModule {

    public NewsDatabase provideRoomDatabase(Application application){
        return Room.databaseBuilder(application,
                NewsDatabase.class, "news_db")
                .allowMainThreadQueries()
                .build();
    }

    public NewsDao provideNewsDao(NewsDatabase database) {
        return database.newsDao();
    }
}
