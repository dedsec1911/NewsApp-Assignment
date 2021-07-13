package com.dedsec.newsappassignment.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.dedsec.newsappassignment.model.Article;

@Dao
public interface NewsDao {

    @Insert
    void insertArticles(List<Article> articlesList);

    @Query("DELETE From articles_table")
    void deleteArticles();

    @Query("SELECT * From articles_table")
    List<Article> getArticles();
}
