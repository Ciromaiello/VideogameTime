package com.example.cirom.videogametime;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseGiochi;
    DatabaseReference ref;
    String[] id = new String[10];
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        impostaPager();

        databaseGiochi = FirebaseDatabase.getInstance().getReference("giochi");

        //esempi di inserimenti fatti da noi di giochi
/*
        inserisciGioco("Fifa 19","Sportivo","PlayStation 4,XBOX ONE");
        inserisciGioco("Football Manager 2019","Gestionale,Sportivo","PS VITA");
        inserisciGioco("Pokémon Smeraldo ","GDR","Nintendo DS,Nintendo 3DS");
        inserisciGioco("ARMS","Picchiaduro","Nintendo SWITCH");
*/
    }

    private void impostaPager() {

        // Riferimento al pager
        final ViewPager viewPager = findViewById(R.id.pager);

        // Creo l'adapter
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        // Imposto l'adapter per il pager
        viewPager.setAdapter(pagerAdapter);

        // Creazione dei tab ed aggiunta alla toolbar
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager, true);
    }

    public void inserisciGioco(String nome,String genere,String piattaforma) {


        //metodo per inserire un gioco nel database da codice


        ref = databaseGiochi.push().getRef();
        id[cont] =ref.getKey();
        ref.setValue(new Giochi(nome,genere,piattaforma,id[cont]));
        cont++;

    }


    //metodo alternativo per inserimento di giochi ma sovrascrive il database ogni volta
        /*Map<String, Giochi> giochi = new HashMap<>();
        String[] id = new String[10];
        for (int i=0;i<10;i++)
        {
            id[i]=databaseGiochi.push().getKey();
        }
        giochi.put(id[0],new Giochi("Fifa 19", "Sportivo", "PS4,XBOX ONE",id[0]));
        giochi.put(id[1],new Giochi("ARMS","picchiaduro","NINTENDO SWITCH",id[1]));
        giochi.put(id[2],new Giochi("Football Manager 2019","Simulazione","PC",id[2]));
        databaseGiochi.setValue(giochi);
    */


}