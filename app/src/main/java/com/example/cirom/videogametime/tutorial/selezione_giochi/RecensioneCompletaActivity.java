package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.CircleTransform;
import com.squareup.picasso.Picasso;
import static com.example.cirom.videogametime.utilizzo.Account.acct;

public class RecensioneCompletaActivity extends AppCompatActivity {

    private TextView titolo, recensione, nome_account, time, gioco;
    private RatingBar stars;
    private ImageView img_account;
    private Button elimina, modifica;
    static String Idgioco, Gioco, Immagine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione_completa);

        titolo = findViewById(R.id.titolo);
        recensione = findViewById(R.id.recensione_id);
        nome_account = findViewById(R.id.nome_account);
        time =findViewById(R.id.time_id);
        gioco = findViewById(R.id.nome_gioco);
        stars = findViewById(R.id.stelline_id);
        img_account =findViewById(R.id.image_account_rec);
        elimina = findViewById(R.id.id_elimina);
        modifica = findViewById(R.id.id_modifica);

        Intent intent = getIntent();
        Immagine = intent.getExtras().getString("img");
        Idgioco = intent.getExtras().getString("idgioco");
        String Id = intent.getExtras().getString("id");
        String Title = intent.getExtras().getString("Title");
        String Recensione = intent.getExtras().getString("Recensione");
        String PersonImm = intent.getExtras().getString("PersonImm");
        String PersonName = intent.getExtras().getString("PersonName");
        String Time = intent.getExtras().getString("Time");
        float Stars = intent.getExtras().getFloat("Stars");
        Gioco = intent.getExtras().getString("Gioco");

        titolo.setText(Title);
        recensione.setText(Recensione);
        nome_account.setText(PersonName);
        stars.setRating(Stars);
        time.setText(Time);
        gioco.setText(Gioco);
        Picasso.with(getApplicationContext()).load(PersonImm).transform(new CircleTransform(35,10)).into(img_account);
        if(Id.equals(acct.getId())) {
            elimina.setVisibility(View.VISIBLE);
            modifica.setVisibility(View.VISIBLE);
            modifica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModificaDialog newFragment = new ModificaDialog();
                    newFragment.show(getSupportFragmentManager(), "Scelta");
                }
            });
            elimina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EliminaDialog newFragment = new EliminaDialog();
                    newFragment.show(getSupportFragmentManager(), "Scelta");
                }
            });
        }

    }
}
