package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import static com.example.cirom.videogametime.utilizzo.Account.acct;
import static com.example.cirom.videogametime.utilizzo.Account.idGiochiScelti;

public class GiochiActivity extends AppCompatActivity {

    private TextView nome;
    private ImageView img;
    private RatingBar stars;
    private Button segui;
    static String Description, output1, output2, id_gioco, Title, image, id;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;
    GiochiActivity giochiActivity;
    static ArrayList<String> Piattaforme, Generi;
    ArrayList<Float> numMedia;
    float numRating = 0;
    private CollectionReference mScelte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giochi);

        nome = (TextView) findViewById(R.id.title);
        img = (ImageView) findViewById(R.id.gameimg);
        stars = (RatingBar) findViewById(R.id.gettingStar);
        segui = (Button) findViewById(R.id.id_segui);

        // Recieve data
        Intent intent = getIntent();
        Title = intent.getExtras().getString("Title");
        Generi = intent.getExtras().getStringArrayList("Generi");
        Piattaforme = intent.getExtras().getStringArrayList("Piattaforme");
        Description = intent.getExtras().getString("Description");
        image = intent.getExtras().getString("Image");
        id = intent.getExtras().getString("id");

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
        id_gioco = id;
        if(Account.utente && Account.fatto && idGiochiScelti.contains(id_gioco)) {
            segui.setBackgroundColor(getResources().getColor(R.color.Grigio));
            segui.setText(R.string.seguito);
            segui.setTextColor(getResources().getColor(R.color.verde2));
        }
        else {
            segui.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            segui.setText(R.string.segui);
            segui.setTextColor(getResources().getColor(R.color.cardview_light_background));
        }
        mFirestore = FirebaseFirestore.getInstance();
        mGiochi = mFirestore.collection("Giochi");
        giochiActivity = new GiochiActivity();
        numMedia = new ArrayList<>();
        mGiochi.document(giochiActivity.id_gioco).collection("Recensioni")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot document : task.getResult()) {
                            Recensione r = document.toObject(Recensione.class);
                            numMedia.add(r.getStars());
                        }
                        Media();
                        stars.setRating(numRating);
                        impostaPager();
                    }
                });
        mScelte = mFirestore.collection("Account");
        segui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Account.utente) {
                    if(Account.fatto) {
                        if(idGiochiScelti.contains(id_gioco)) {
                            segui.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            segui.setText(R.string.segui);
                            segui.setTextColor(getResources().getColor(R.color.cardview_light_background));
                            mFirestore = FirebaseFirestore.getInstance();
                            mScelte = mFirestore.collection("Account");
                            Account.idGiochiScelti.remove(GiochiActivity.id_gioco);
                            mScelte.document(acct.getId()).collection("Scelte")
                                    .document("GiochiScelti")
                                    .update("scelte", FieldValue.arrayRemove(GiochiActivity.id_gioco));
                            Toast.makeText(getApplication(), R.string.non_segui_titolo, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            segui.setBackgroundColor(getResources().getColor(R.color.Grigio));
                            segui.setText(R.string.seguito);
                            segui.setTextColor(getResources().getColor(R.color.verde2));
                            mScelte.document(acct.getId()).collection("Scelte")
                                    .document("GiochiScelti")
                                    .update("scelte", FieldValue.arrayUnion(id_gioco));
                            idGiochiScelti.add(id_gioco);
                            Toast.makeText(getApplication(), R.string.segui_titolo, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplication(), R.string.funzionalità_tutorial, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplication(), R.string.segui_ospite, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Media() {
        float sum = 0;
        for(int i=0; i<numMedia.size(); i++) {
            sum += numMedia.get(i);
        }
        numRating = sum/numMedia.size();
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
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}