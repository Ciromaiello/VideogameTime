package com.example.cirom.videogametime.utilizzo;



import android.arch.lifecycle.ViewModelStore;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    private int[] imgs={R.drawable.multiplayer,R.drawable.spaziogames,R.drawable.gamesvillage,R.drawable.gamespot};
    private String[] urls={"https://multiplayer.it/","https://www.spaziogames.it/","http://www.gamesvillage.it/","https://www.gamespot.com/"};


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

        newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<News> news=new ArrayList<>();
        for(int i=0;i<imgs.length;i++){
            news.add(new News(imgs[i],urls[i]));
        }

        NewsAdapter newsAdapter = new NewsAdapter(getContext(),news);
        newsList.setAdapter(newsAdapter);


    }




}
