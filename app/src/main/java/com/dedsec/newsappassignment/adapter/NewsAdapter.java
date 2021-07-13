package com.dedsec.newsappassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.dedsec.newsappassignment.R;
import com.dedsec.newsappassignment.model.Article;


public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<Article> articles;
    private Context context;

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_news_view, parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getDescription());

        String photoUrl = articles.get(position).getUrlToImage();

        Picasso.get()
                .load(photoUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        ImageView imageView;

        public NewsViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
            });
        }
    }

}