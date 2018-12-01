package com.example.cirom.videogametime.tutorial.selezione_piattaforme;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.Piattaforme;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeAdapter;

import java.util.ArrayList;

public class PiattaformeFragment extends Fragment {

    ArrayList<Piattaforme> piattaforme;
    private RecyclerView card;
    boolean Selezionato = false;

    public PiattaformeFragment() {
        //Costruttore
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_piattaforme, container, false);
        /**final MainActivity mainActivity = (MainActivity) getActivity();

        piattaformeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_DOWN) {
                    View Pager = mainActivity.findViewById(R.id.pager);
                    CustomViewPager viewPager = (CustomViewPager) Pager.findViewById(R.id.pager);
                    if (!Selezionato) {
                        viewPager.setEnabled(false);
                    } else {
                        viewPager.setEnabled(true);
                    }
                    return false;
                }
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "touched down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("TAG", "moving: (" + x + ", " + y + ")");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "touched up");
                        break;
                }
                return true;
            }
        });*/
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
        int immagini[] = {R.drawable.ps4, R.drawable.one, R.drawable.ciao, R.drawable.msi, R.drawable.psvita, R.drawable.ds3, R.drawable.wiiu, R.drawable.ps3, R.drawable.xbox360, R.drawable.wii, R.drawable.ds, R.drawable.psp} ;
        String parole[] = {"PlayStation 4", "XBOX ONE", "Nintendo SWITCH", "PC", "PS VITA", "Nintendo 3DS", "Wii U", "PlayStation 3", "XBOX 360", "Wii", "Nintendo DS", "PSP"};
        int k = parole.length;
        for (int i = 0; i <= k-1; i++) {
            Piattaforme console = new Piattaforme();
            console.setTextpiattaforme(parole[i]);
            console.setImage(immagini[i]);
            this.piattaforme.add(console);
        }
        PiattaformeAdapter adapter = new PiattaformeAdapter(this.piattaforme);
        card.setAdapter(adapter);
    }

    public boolean canSwipe() {
        for(Piattaforme console : piattaforme)
        {
            if(console.isSelected1()) {
                return true;
            }
        }
        return false;
    }

}
