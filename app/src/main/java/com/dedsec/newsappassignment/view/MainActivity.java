package com.dedsec.newsappassignment.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dedsec.newsappassignment.R;
import com.dedsec.newsappassignment.adapter.NewsAdapter;
import com.dedsec.newsappassignment.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        MainActivityViewModel activityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        if (isNetworkAvailable()) {
            activityViewModel.getAllArticle().observe(this, articles -> {
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new NewsAdapter(articles, MainActivity.this));
            });
        } else {
            activityViewModel.getOfflineArticle().observe(this, articles -> {
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new NewsAdapter(articles, MainActivity.this));
            });
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
