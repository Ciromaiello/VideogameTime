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
import com.example.cirom.videogametime.utilizzo.Account;
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


import static android.os.SystemClock.sleep;
import static com.example.cirom.videogametime.utilizzo.Account.consoleQuery;
import static com.example.cirom.videogametime.utilizzo.Account.genQuery;
import static com.example.cirom.videogametime.utilizzo.Account.nomige;

public class SelectionGiochiActivity extends AppCompatActivity {

    private FloatingActionButton btnScelta;
    ArrayList<Giochi> giochi;
    private ArrayList<String> nomi;

    private DatabaseReference mDatabase;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_giochi);
        btnScelta = findViewById(R.id.btnGiochi);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        giochi = new ArrayList<>();
        nomi = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        mGiochi = mFirestore.collection("Giochi");
        Controllo();
        int i = 0;
        AggiungiGiochi(i);
        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionGiochiActivity.this, ProfiloActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Controllo() {
        for(int i=0;i<Account.nomige.size();i++) {
            for(int j=0;j<Account.nomipi.size();j++) {
                if(Account.nomipi.get(j).equals(Account.nomige.get(i))) {
                    if(!nomi.contains(Account.nomipi.get(j)))
                        nomi.add(Account.nomipi.get(j));
                }
            }
        }
    }

    private void AggiungiGiochi(int i) {
        if(i<nomi.size()) {
            Query(i);
        }
        else {
            RecyclerView myrv = findViewById(R.id.recyclerview_id);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getApplicationContext(), giochi);
            myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            myrv.setAdapter(myAdapter);
        }
    }

    private void Query(final int i) {
        mGiochi.document(nomi.get(i))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Giochi g = documentSnapshot.toObject(Giochi.class);
                        giochi.add(g);
                        int f = i;
                        f++;
                        AggiungiGiochi(f);
                    }
                });
    }
}

