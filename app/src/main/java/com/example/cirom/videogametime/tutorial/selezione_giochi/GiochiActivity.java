package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.example.cirom.videogametime.R;

public class GiochiActivity extends AppCompatActivity {

    private Button btnScelta;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giochi);
        btnScelta = findViewById(R.id.btnGiochi);
        btnScelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiochiActivity.this,ProfiloActivity.class);
                startActivity(intent);
            }
        });
    }
}
