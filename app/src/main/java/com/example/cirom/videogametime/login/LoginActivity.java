package com.example.cirom.videogametime.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnOspite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnOspite = findViewById(R.id.ospite_btn);
        btnOspite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}