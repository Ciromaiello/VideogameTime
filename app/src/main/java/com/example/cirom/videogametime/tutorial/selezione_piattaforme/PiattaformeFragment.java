package com.example.cirom.videogametime.tutorial.selezione_piattaforme;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_generi.Generi;
import com.example.cirom.videogametime.tutorial.selezione_generi.GeneriAdapter;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.Piattaforme;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class PiattaformeFragment extends Fragment {

    ArrayList<Piattaforme> piattaforme;
    private RecyclerView card;
    boolean Selezionato = false;
    private DatabaseReference mDatabase;

    public PiattaformeFragment() {
        //Costruttore
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_piattaforme, container, false);
        /**final MainActivity mainActivity = (MainActivity) getActivity();

        piattaformeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_DOWN) {
                    View Pager = mainActivity.findViewById(R.id.pager);
                    CustomViewPager viewPager = (CustomViewPager) Pager.findViewById(R.id.pager);
                    if (!Selezionato) {
                        viewPager.setEnabled(false);
                    } else {
                        viewPager.setEnabled(true);
                    }
                    return false;
                }
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "touched down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("TAG", "moving: (" + x + ", " + y + ")");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "touched up");
                        break;
                }
                return true;
            }
        });*/
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        card = (RecyclerView) view.findViewById(R.id.card);
        card.setLayoutManager(new LinearLayoutManager(getActivity()));
        card.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        piattaforme = new ArrayList<>();
        mDatabase.child("piattaforme").child("nomi_piattaforme").addChildEventListener(new ChildEventListener() {
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
    }

    public boolean canSwipe() {
        for(Piattaforme console : piattaforme)
        {
            if(console.isSelected1()) {
                return true;
            }
        }
        return false;
    }
}
