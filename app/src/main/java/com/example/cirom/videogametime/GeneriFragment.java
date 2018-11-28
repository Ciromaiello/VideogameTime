package com.example.cirom.videogametime;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class GeneriFragment extends Fragment{

    ArrayList<Generi> generi;
    private RecyclerView list;
    private Button btnGetSelected;

    public GeneriFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_generi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnGetSelected = (Button) view.findViewById(R.id.btnGetSelected);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        generi = new ArrayList<>();
        String parole[] = {"Action", "Avventura", "Erotico", "Free-to-play", "Gestionale", "Hack and slash", "Indie", "MMORPG", "Party game", "Picchiaduro", "Platform", "RPG", "Simulatore", "Sparatutto", "Sportivo","Strategico", "Visual novel"};
        int k = parole.length;
        for (int i = 0; i <= k-1; i++) {
            Generi gen = new Generi();
            gen.setTextgeneri(parole[i]);
            this.generi.add(gen);
        }

        GeneriAdapter adapter = new GeneriAdapter(this.generi);
        list.setAdapter(adapter);

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {               StringBuilder stringBuilder = new StringBuilder();
                for (Generi gen : generi) {
                    if (gen.isSelected()) {
                        if (stringBuilder.length() > 0)
                            stringBuilder.append(", ");
                        stringBuilder.append(gen.getTextgeneri());
                        Intent intent = new Intent(getContext(),AccessoUtentiActivity.class);
                        startActivity(intent);
                    }
                }
                /**
                 * Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
                 * Se vogliamo un pop-up di quelli selezionati. Errore se non sono selezionati.
                 */
            }
        });
    }

}
