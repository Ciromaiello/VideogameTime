package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.cirom.videogametime.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiochiActivity extends AppCompatActivity {

    private TextView nome;
    private ImageView img;
    private RatingBar stars;
    static String Description, output1, output2, id_gioco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giochi);

        nome = (TextView) findViewById(R.id.title);
        img = (ImageView) findViewById(R.id.gameimg);
        stars = (RatingBar) findViewById(R.id.gettingStar);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        ArrayList<String> Generi = intent.getExtras().getStringArrayList("Generi");
        ArrayList<String> Piattaforme = intent.getExtras().getStringArrayList("Piattaforme");
        Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Image") ;
        float numRating = intent.getExtras().getFloat("Rating");
        String id = intent.getExtras().getString("id");

        // Setting values
        nome.setText(Title);
        output1 = "";
        for (int i = 0; i < Generi.size(); i++) {
            if(i>0) {
                output1 += ", ";
            }
            output1 += Generi.get(i);
        }
        Log.e("ciaogioco", "Il genere è " + output1);
        output2 = "";
        for (int j = 0; j < Piattaforme.size(); j++) {
            if(j>0) {
                output2 += ", ";
            }
            output2 += Piattaforme.get(j);
        }
        Picasso.with(getApplicationContext()).load(image).into(img);
        stars.setRating(numRating);
        Log.e("EEE", "L'id è " + id);
        id_gioco = id;
        //Bundle per passare i dati a InfoFragment
        /**Bundle bundle = new Bundle();
        bundle.putString("descrizione", Description);
        bundle.putString("piattaforme", output2);
        bundle.putString("generi", output1);
        InfoFragment infoFragment = new InfoFragment();
        infoFragment.setArguments(bundle);*/
        impostaPager();
    }

    private void impostaPager() {

        // Riferimento al pager
        final ViewPager viewPager = findViewById(R.id.pager);

        // Creo l'adapter
        final GiochiPagerAdapter pagerAdapter = new GiochiPagerAdapter(getSupportFragmentManager());

        // Imposto l'adapter per il pager
        viewPager.setAdapter(pagerAdapter);

        // Creazione dei tab ed aggiunta alla toolbar
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i < pagerAdapter.getCount(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter.getItemTabNameResourceId(i)));

        // Listner delle selezioni dei tab
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
