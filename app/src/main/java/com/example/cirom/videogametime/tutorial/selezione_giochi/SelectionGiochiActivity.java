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
import android.view.View;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_generi.Generi;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class SelectionGiochiActivity extends AppCompatActivity {

    private FloatingActionButton btnScelta;
    ArrayList<Giochi> giochi;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<String> genQuery = intent.getExtras().getStringArrayList("Generi");
        Log.e("e", "I generi " + genQuery.toString());
        setContentView(R.layout.activity_selection_giochi);
        btnScelta = findViewById(R.id.btnGiochi);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        giochi = new ArrayList<>();
        Query query = mDatabase.child("giochi").orderByChild("nome");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Giochi game = dataSnapshot.getValue(Giochi.class);
                giochi.add(game);
                RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
                RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getApplicationContext(), giochi);
                myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                myrv.setAdapter(myAdapter);
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
        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionGiochiActivity.this, ProfiloActivity.class);
                startActivity(intent);
            }
        });
    }
}