package com.example.cirom.videogametime.utilizzo;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cirom.videogametime.R;

public class NewsFragment extends Fragment {
    public NewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}