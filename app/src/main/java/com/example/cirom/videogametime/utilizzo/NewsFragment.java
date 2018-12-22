package com.example.cirom.videogametime.utilizzo;



import android.arch.lifecycle.ViewModelStore;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;

public class NewsFragment extends Fragment {
    private final String TAG = "DEMO_MISC";
    private RecyclerView newsList;
    private List<News> news;
    private TextView newsTitolo;
    private TextView newsDesc;
    private ImageView newsImg;
    private NewsAdapter adapter;


    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsList = view.findViewById(R.id.newsList);
        news = new ArrayList<>();
        news.add(new News("titolo","descrizione","url","immagine"));
      
        adapter = new NewsAdapter(news);

        newsList.setAdapter(adapter);


    }
}
