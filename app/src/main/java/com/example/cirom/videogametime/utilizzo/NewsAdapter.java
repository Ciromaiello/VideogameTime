package com.example.cirom.videogametime.utilizzo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cirom.videogametime.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{

    private ArrayList<News>  news;

    public NewsAdapter(Context context,List<News> news)
    {
        this.news = new ArrayList<>(news);
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.bindData(news.get(position));
        }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;

        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.img);
            v.setOnClickListener(this);
        }

        public void bindData(News notizia) {
            Picasso.with(itemView.getContext()).load(notizia.getImg()).into(img);
        }

        @Override
        public void onClick(View v) {
            News notizia=news.get(getAdapterPosition());
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(notizia.getUrl()));
            v.getContext().startActivity(i);
        }
    }

}



