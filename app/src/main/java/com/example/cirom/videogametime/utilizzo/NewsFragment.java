package com.example.cirom.videogametime.utilizzo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cirom.videogametime.R;

import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;


public class NewsFragment extends Fragment {
    private RecyclerView newsList;
    private FirebaseFirestore mFirestore;
    private CollectionReference mNews;
    private String[] siti = {"multiplayer","spaziogames","gamesvilage","gamespot","calcionapoli24"};
    private ArrayList<News> news;


    public NewsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsList = view.findViewById(R.id.newsList);
        mFirestore=FirebaseFirestore.getInstance();
        mNews= mFirestore.collection("News");
        newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        news=new ArrayList<>();
        int i=0;
        AggiungiNews(i);
    }

    private void AggiungiNews(int i) {
        if(i<siti.length)
            QueryNews(i);
        else {
            NewsAdapter newsAdapter = new NewsAdapter(getContext(),news);
            newsList.setAdapter(newsAdapter);
        }
    }

    private void QueryNews(final int i) {
        mNews.document(siti[i])
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        News s = documentSnapshot.toObject(News.class);
                        news.add(new News(s.getImg(),s.getUrl()));
                        int f = i;
                        f++;
                        AggiungiNews(f);
                    }
                });
    }
}