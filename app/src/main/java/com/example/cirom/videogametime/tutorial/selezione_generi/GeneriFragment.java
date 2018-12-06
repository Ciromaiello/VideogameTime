package com.example.cirom.videogametime.tutorial.selezione_generi;

import android.content.Intent;
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
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.SelectionGiochiActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class GeneriFragment extends Fragment{

    ArrayList<Generi> generi;
    private RecyclerView list;
    private FloatingActionButton btnGetSelected;
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
        btnGetSelected = (FloatingActionButton) view.findViewById(R.id.btnGetSelected);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("generi").child("nomi_generi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                generi = new ArrayList<>();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String value = postSnapshot.getValue(String.class);
                    Generi gen = new Generi();
                    gen.setTextgeneri(value);
                    generi.add(gen);
                }
                GeneriAdapter adapter = new GeneriAdapter(generi);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Generi gen : generi) {
                    if (gen.isSelected()) {
                        if (stringBuilder.length() > 0)
                            stringBuilder.append(", ");
                        stringBuilder.append(gen.getTextgeneri());
                        Intent intent = new Intent(getContext(),SelectionGiochiActivity.class);
                        startActivity(intent);
                    }
                    /**else {
                        Toast.makeText(getActivity(),"Seleziona almeno un genere", Toast.LENGTH_SHORT).show();
                    }*/
                }
                /**
                  Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
                  Se vogliamo un pop-up di quelli selezionati. Errore se non sono selezionati.
                 */
            }
        });
    }
}
