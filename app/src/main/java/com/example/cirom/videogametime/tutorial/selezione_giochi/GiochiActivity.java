package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cirom.videogametime.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiochiActivity extends AppCompatActivity {

    private TextView nome,descrizione,generi,piattaforme;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giochi);

        nome = (TextView) findViewById(R.id.title);
        descrizione = (TextView) findViewById(R.id.description);
        generi = (TextView) findViewById(R.id.generi);
        piattaforme = (TextView) findViewById(R.id.piattaforme);
        img = (ImageView) findViewById(R.id.gameimg);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        ArrayList<String> Generi = intent.getExtras().getStringArrayList("Generi");
        ArrayList<String> Piattaforme = intent.getExtras().getStringArrayList("Piattaforme");
        String Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Image") ;

        // Setting values
        nome.setText(Title);
        String output1 = "";
        for (int i = 0; i < Generi.size(); i++) {
            if(i>0) {
                output1 += ", ";
            }
            output1 += Generi.get(i);
        }
        generi.setText(output1);
        String output2 = "";
        for (int j = 0; j < Piattaforme.size(); j++) {
            if(j>0) {
                output2 += ", ";
            }
            output2 += Piattaforme.get(j);
        }
        piattaforme.setText(output2);
        descrizione.setText(Description);
        Picasso.with(getApplicationContext()).load(image).into(img);
    }
}
