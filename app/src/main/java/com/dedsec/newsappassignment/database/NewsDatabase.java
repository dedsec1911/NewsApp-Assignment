package com.dedsec.newsappassignment.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dedsec.newsappassignment.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
}
