package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_generi.Generi;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import static com.example.cirom.videogametime.utilizzo.Account.consoleQuery;
import static com.example.cirom.videogametime.utilizzo.Account.genQuery;

public class SelectionGiochiActivity extends AppCompatActivity {

    private FloatingActionButton btnScelta;
    ArrayList<Giochi> giochi;
    private ArrayList<String> nomi;
    private ArrayList<String> nomige;
    private ArrayList<String> nomipi;

    private DatabaseReference mDatabase;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;
    private CollectionReference mGeneri;
    private CollectionReference mPiattaforme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_selection_giochi);
        btnScelta = findViewById(R.id.btnGiochi);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        giochi = new ArrayList<>();
        nomi =new ArrayList<>();
        nomige= new ArrayList<>();
        nomipi=new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();
        mGiochi=mFirestore.collection("Giochi");
        mGeneri = mFirestore.collection("Generi");
        mPiattaforme = mFirestore.collection("Piattaforme");

        AggiungiGiochiPiattaforme();
        AggiungiGiochiGeneri();
        Controllo();
        AggiungiGiochi();


        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getApplicationContext(), giochi);
        myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        myrv.setAdapter(myAdapter);


        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionGiochiActivity.this, ProfiloActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AggiungiGiochiGeneri()
    {
        for(int i=0;i<genQuery.size();i++)
        {
            mGeneri.document(genQuery.get(i))
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            SceltaDalDatabase s = documentSnapshot.toObject(SceltaDalDatabase.class);
                            ArrayList<String> S = s.getGiochi();

                            for (int j = 0; j < S.size(); j++) {

                                if (!nomige.contains(S.get(j)))
                                {nomige.add(S.get(j));
                                  Log.e("NOMIGE", "I NOMIGE SONO:"+ nomige.get(j));
                                }
                            }
                        }
                    });


        }

    }


    private void AggiungiGiochiPiattaforme()
    {
        for(int i=0;i<consoleQuery.size();i++)
        {
            mPiattaforme.document(consoleQuery.get(i))
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            SceltaDalDatabase s = documentSnapshot.toObject(SceltaDalDatabase.class);
                            ArrayList<String> S= s.getGiochi();

                            for (int j = 0; j <S.size(); j++) {

                                if (!nomipi.contains(S.get(j)))
                                {nomipi.add(S.get(j));
                                   Log.e("NOMIPI", "I NOMIPI SONO:"+ nomipi.get(j));
                                    }
                            }
                        }

                    });

        }
    }

    public void Controllo()
    {
        for(int i=0;i<nomige.size();i++)
        {
            for(int j=0;j<nomipi.size();j++)
            {
                if(nomipi.get(j).equals(nomige.get(i)))
                {
                    nomi.add(nomipi.get(j));
                }
            }
        }

        if(nomi.size()==0)
        {
            Log.e("ZERO", "UE VIENE 0");
        }

        if(nomige.size()==0)
        {
            Log.e("ZEROge", "UE VIENE 0");
        }
        if(nomipi.size()==0)
        {
            Log.e("ZEROpi", "UE VIENE 0");
        }

        for(int i=0;i<nomi.size();i++)
        {
            Log.e("NOMI", "I NOMI SONO: " +nomi.get(i));
        }
    }

    private void AggiungiGiochi()
    {
        for(int i=0;i<nomi.size();i++)
        {
            mGiochi.document(nomi.get(i))
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            giochi.add(documentSnapshot.toObject(Giochi.class));
                        }
                    });
        }
    }


}

