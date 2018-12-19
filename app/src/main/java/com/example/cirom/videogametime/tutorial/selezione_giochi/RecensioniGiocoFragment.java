package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.Account;

import java.util.ArrayList;

import static com.example.cirom.videogametime.utilizzo.Account.genQuery;

public class RecensioniGiocoFragment extends Fragment {

    private RecyclerView list;
    private FloatingActionButton aggiungiReceButton;

    public RecensioniGiocoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recensioni_gioco, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aggiungiReceButton = (FloatingActionButton) view.findViewById(R.id.aggiungiRecButton);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aggiungiReceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Account.utente)
                    startActivity(new Intent(getContext(), NewRecensioneActivity.class));
                else {
                    Toast.makeText(getActivity(),"Non puoi recensire il gioco come ospite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
