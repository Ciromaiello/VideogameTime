package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cirom.videogametime.R;

public class InfoFragment extends Fragment {

    private TextView descrizione, piattaforme, generi;
    GiochiActivity giochiActivity;

    public InfoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        descrizione = (TextView) view.findViewById(R.id.description);
        piattaforme = (TextView) view.findViewById(R.id.piattaforme);
        generi = (TextView) view.findViewById(R.id.generi);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        giochiActivity = new GiochiActivity();
        generi.setText(giochiActivity.output1);
        piattaforme.setText(giochiActivity.output2);
        descrizione.setText(giochiActivity.Description);
        /**String Description = this.getArguments().getString("descrizione");
        String Console = this.getArguments().getString("piattaforme");
        String gen = this.getArguments().getString("generi");
        generi.setText(gen);
        descrizione.setText(Description);
        piattaforme.setText(Console);*/
    }
}