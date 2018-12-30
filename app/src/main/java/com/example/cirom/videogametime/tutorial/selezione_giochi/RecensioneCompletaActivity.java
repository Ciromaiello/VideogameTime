package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.CircleTransform;
import com.squareup.picasso.Picasso;

public class RecensioneCompletaActivity extends AppCompatActivity {

    private TextView titolo, recensione, nome_account, time, gioco;
    private RatingBar stars;
    private ImageView img_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione_completa);

        titolo = (TextView) findViewById(R.id.titolo);
        recensione = (TextView) findViewById(R.id.recensione_id);
        nome_account = (TextView) findViewById(R.id.nome_account);
        time = (TextView) findViewById(R.id.time_id);
        gioco = (TextView) findViewById(R.id.nome_gioco);
        stars = (RatingBar) findViewById(R.id.stelline_id);
        img_account = (ImageView) findViewById(R.id.image_account_rec);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Recensione = intent.getExtras().getString("Recensione");
        String PersonImm = intent.getExtras().getString("PersonImm");
        String PersonName = intent.getExtras().getString("PersonName");
        String Time = intent.getExtras().getString("Time");
        float Stars = intent.getExtras().getFloat("Stars");
        String Gioco = intent.getExtras().getString("Gioco");

        titolo.setText(Title);
        recensione.setText(Recensione);
        nome_account.setText(PersonName);
        stars.setRating(Stars);
        time.setText(Time);
        gioco.setText(Gioco);
        Picasso.with(getApplicationContext()).load(PersonImm).transform(new CircleTransform(35,10)).into(img_account);
    }
}
