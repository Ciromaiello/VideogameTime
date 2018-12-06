package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectionGiochiActivity extends AppCompatActivity {

    private FloatingActionButton btnScelta;
    List<Giochi> giochi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_giochi);
        btnScelta = findViewById(R.id.btnGiochi);
        giochi = new ArrayList<>();
        giochi.add(new Giochi("FIFA 19","Sportivo","PS4, XBOX ONE, PC, SWITCH", "Descrizione", R.drawable.fifa19));
        giochi.add(new Giochi("Dark Souls","Action, Hack and slash, RPG","PS3, PC, XBOX 360","Descrizione", R.drawable.dark_souls));
        giochi.add(new Giochi("Persona 5","RPG, Visual novel, Strategico","PS4", "Descrizione", R.drawable.p5));
        giochi.add(new Giochi("Detroit Become...","Avventura","PS4", "Descrizione", R.drawable.detroit));
        giochi.add(new Giochi("Final Fantasy XV","Avventura, RPG","PS4, PC, XBOX ONE", "Descrizione", R.drawable.ffxv));
        giochi.add(new Giochi("WOW","MMORPG, Avventura, Strategico","PC", "Descrizione", R.drawable.wow));
        giochi.add(new Giochi("Pokémon X","RPG, Strategico","Nintendo 3DS", "Descrizione", R.drawable.pokemon_x));
        giochi.add(new Giochi("Super Mario Odyssey","Avventura, Platform","SWITCH", "Descrizione", R.drawable.odyssey));
        giochi.add(new Giochi("Patapon","Strategico, Musicale","PSP", "Descrizione", R.drawable.patapon));
        giochi.add(new Giochi("LOL","Free-to-play, MMORPG","PC", "Descrizione", R.drawable.lol));
        giochi.add(new Giochi("Overwatch","RPG, Sparatutto","PC, PS4, XBOX ONE", "Descrizione", R.drawable.overwatch));
        giochi.add(new Giochi("COD BO3","Sparatutto","PC, PS4, XBOX ONE", "Descrizione", R.drawable.bo3));
        giochi.add(new Giochi("Assassin's Creed","Avventura, Action, Platform","PS3, XBOX 360, PC", "Descrizione", R.drawable.assassins_creed));
        giochi.add(new Giochi("Inside","Platform, Indie","PC, PS4, XBOX ONE", "Descrizione", R.drawable.inside));
        giochi.add(new Giochi("Uncharted 2","Avventura, Action, Platform","PS3", "Descrizione", R.drawable.uncharted_2));
        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionGiochiActivity.this, ProfiloActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, giochi);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }
}
