package com.example.cirom.videogametime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AccessoUtenti extends AppCompatActivity {

    private Button btnOspite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utenti_accesso);
        btnOspite = findViewById(R.id.ospite_btn);
        btnOspite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccessoUtenti.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
