package com.example.cirom.videogametime.tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.PiattaformeFragment;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main, new PiattaformeFragment()).commit();

    }
}
