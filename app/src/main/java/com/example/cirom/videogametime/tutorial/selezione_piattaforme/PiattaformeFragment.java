package com.example.cirom.videogametime.tutorial.selezione_piattaforme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_generi.Generi;
import com.example.cirom.videogametime.tutorial.selezione_generi.GeneriAdapter;
import com.example.cirom.videogametime.tutorial.selezione_generi.GeneriFragment;
import com.example.cirom.videogametime.tutorial.selezione_giochi.SceltaDalDatabase;
import com.example.cirom.videogametime.tutorial.selezione_giochi.SelectionGiochiActivity;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.Piattaforme;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeAdapter;
import com.example.cirom.videogametime.utilizzo.Account;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;
import static com.example.cirom.videogametime.utilizzo.Account.consoleQuery;
import static com.example.cirom.videogametime.utilizzo.Account.genQuery;

public class PiattaformeFragment extends Fragment {

    ArrayList<Piattaforme> piattaforme;
    private FloatingActionButton btnForGeneri;
    private RecyclerView card;
    private DatabaseReference mDatabase;
    private FirebaseFirestore mFirestore;
    private CollectionReference mPiattaforme;

    public PiattaformeFragment() {
        //Costruttore
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_piattaforme, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnForGeneri = (FloatingActionButton) view.findViewById(R.id.btnForGeneri);
        card = (RecyclerView) view.findViewById(R.id.card);
        card.setLayoutManager(new LinearLayoutManager(getActivity()));
        card.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        piattaforme = new ArrayList<>();
        consoleQuery = new ArrayList<>();
        Account.nomipi = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        mPiattaforme = mFirestore.collection("Piattaforme");
        mDatabase.child("piattaforme").orderByChild("nome").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Piattaforme console = dataSnapshot.getValue(Piattaforme.class);
                piattaforme.add(console);
                PiattaformeAdapter adapter = new PiattaformeAdapter(getContext(), piattaforme);
                card.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnForGeneri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkato = false;
                consoleQuery = new ArrayList<>();
                for (Piattaforme console : piattaforme) {
                    if (console.isSelected1()) {
                        checkato = true;
                        consoleQuery.add(console.getNome());
                    }
                }
                if (!checkato) {
                    Toast.makeText(getActivity(),"Seleziona almeno una piattaforma", Toast.LENGTH_SHORT).show();
                }
                else {
                    int i = 0;
                    AggiungiGiochiPiattaforme(i);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_main, new GeneriFragment()).commit();
                }
            }
        });
    }

    private void AggiungiGiochiPiattaforme(int i) {
        Log.e("PIATTAFORME", "Sono nell'aggiungi piattaforme");
        if(i<consoleQuery.size())
            QueryPiattaforme(i);
        else {
            Log.e("NOMIPI", "I NOMIPI SONO:"+ Account.nomipi);
            Log.e("FRAGMENT", "Vado a generi fragment");
        }
    }

    private void QueryPiattaforme(final int i) {
        mPiattaforme.document(consoleQuery.get(i))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        SceltaDalDatabase s = documentSnapshot.toObject(SceltaDalDatabase.class);
                        ArrayList<String> S= s.getGiochi();
                        Account.nomipi.addAll(S);
                        int f = i;
                        f++;
                        AggiungiGiochiPiattaforme(f);
                    }
                });
    }
}
