package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cirom.videogametime.R;

public class GiochiActivity extends AppCompatActivity {

    private TextView title,description,generi,piattaforme;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giochi);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        generi = (TextView) findViewById(R.id.generi);
        piattaforme = (TextView) findViewById(R.id.piattaforme);
        img = (ImageView) findViewById(R.id.gameimg);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Generi = intent.getExtras().getString("Generi");
        String Piattaforme = intent.getExtras().getString("Piattaforme");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Image") ;

        // Setting values
        title.setText(Title);
        generi.setText(Generi);
        piattaforme.setText(Piattaforme);
        description.setText(Description);
        img.setImageResource(image);
    }
}
