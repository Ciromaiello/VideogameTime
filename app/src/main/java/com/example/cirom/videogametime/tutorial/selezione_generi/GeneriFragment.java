package com.example.cirom.videogametime.tutorial.selezione_generi;

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
import com.example.cirom.videogametime.tutorial.selezione_giochi.SelectionGiochiActivity;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.Piattaforme;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeAdapter;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;
import static com.example.cirom.videogametime.utilizzo.Account.consoleQuery;
import static com.example.cirom.videogametime.utilizzo.Account.genQuery;

public class GeneriFragment extends Fragment{

    ArrayList<Generi> generi;
    DataSnapshot snapshot;
    private RecyclerView list;
    private FloatingActionButton btnGetSelected;
    private FloatingActionButton btnForPiattaforme;
    private DatabaseReference mDatabase;

    public GeneriFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_generi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnForPiattaforme = (FloatingActionButton) view.findViewById(R.id.btnForPiattaforme);
        btnGetSelected = (FloatingActionButton) view.findViewById(R.id.btnGetSelected);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
        genQuery = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        generi = new ArrayList<>();
        mDatabase.child("generi").orderByChild("genere").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Generi gen = dataSnapshot.getValue(Generi.class);
                generi.add(gen);
                GeneriAdapter adapter = new GeneriAdapter(generi);
                list.setAdapter(adapter);
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

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkato = false;
                for (Generi gen : generi) {
                    if (gen.isSelected()) {
                        checkato = true;
                        genQuery.add(gen.getGenere());
                    }
                }
                if (!checkato)
                    Toast.makeText(getActivity(),"Seleziona almeno un genere", Toast.LENGTH_SHORT).show();
                else
                {
                    startActivity(new Intent(getContext(), SelectionGiochiActivity.class));
                }

            }
        });
        btnForPiattaforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_main, new PiattaformeFragment()).commit();
            }
        });
    }
}
