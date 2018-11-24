package com.example.cirom.videogametime;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class PiattaformeFragment extends Fragment {

    ArrayList<Piattaforme> piattaforme;
    private RecyclerView card;

    public PiattaformeFragment() {
        //Costruttore
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_piattaforme, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        card = (RecyclerView) view.findViewById(R.id.card);
        card.setLayoutManager(new LinearLayoutManager(getActivity()));
        card.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        piattaforme = new ArrayList<>();
        String parole[] = {"PlayStation 4", "XBOX ONE", "Nintendo SWITCH", "PS VITA", "Nintendo 3DS", "PlayStation 3", "XBOX 360", "Nintendo DS", "PSP"};
        for (int i = 0; i <= 8; i++) {
            Piattaforme console = new Piattaforme();
            console.setTextpiattaforme(parole[i]);
            this.piattaforme.add(console);
        }

        PiattaformeAdapter adapter = new PiattaformeAdapter(this.piattaforme);
        card.setAdapter(adapter);
    }
}
