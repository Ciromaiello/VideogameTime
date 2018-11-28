package com.example.cirom.videogametime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GiochiActivity extends AppCompatActivity {

    private Button btnScelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utenti_accesso);
        btnScelta = findViewById(R.id.ospite_btn);
        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiochiActivity.this,ProfiloActivity.class);
                startActivity(intent);
            }
        });
    }
}
