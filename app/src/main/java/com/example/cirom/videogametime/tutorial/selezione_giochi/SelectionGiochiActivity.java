package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.Account;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import static com.example.cirom.videogametime.utilizzo.Account.idGiochiScelti;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;


public class SelectionGiochiActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FloatingActionButton btnScelta;
    ArrayList<Giochi> giochi;
    ArrayList<Giochi> giochiscelti;
    private ArrayList<String> nomi;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_giochi);
        btnScelta = findViewById(R.id.btnGiochi);
        giochi = new ArrayList<>();
        giochiscelti = new ArrayList<>();
        Account.giochiscelti=new ArrayList<>();
        nomi = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mGiochi = mFirestore.collection("Giochi");
        idGiochiScelti=new ArrayList<>();
        Controllo();
        int i = 0;
        AggiungiGiochi(i);
        AggiungiGiochiScelti();
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
            final RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getApplicationContext(), giochi);
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
    public void AggiungiGiochiScelti()
    {
        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("FFFFF", " Account.utente è " + Account.utente);
                if(Account.utente) {
                    int i = 0;
                    for(int j=0;j<nomi.size();j++) {
                        if(giochi.get(j).isSelezionato())
                            idGiochiScelti.add(giochi.get(j).getId_gioco());
                        else
                            i++;
                    }
                    if(i==nomi.size()) {
                        Toast.makeText(getApplication(),R.string.un_gioco, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(SelectionGiochiActivity.this, ProfiloActivity.class);
                        Account.fatto=true;
                        mSettings.edit().putBoolean(mAuth.getUid(),false).apply();
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplication(),R.string.blocco_ospite, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

