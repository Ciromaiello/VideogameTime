package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.cirom.videogametime.R;
import static com.example.cirom.videogametime.utilizzo.Account.acct;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewRecensioneActivity extends AppCompatActivity {

    private EditText titolo, recensione;
    private RatingBar stars;
    private FloatingActionButton inviaButton;
    private CollectionReference mCollection;
    private FirebaseFirestore mFirestore;
    GiochiActivity giochiActivity;
    Recensione rec;
    private String title, rece;
    private float numStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recensione);
        titolo = (EditText) findViewById(R.id.inputTitle);
        recensione = (EditText) findViewById(R.id.inputRece);
        stars = (RatingBar) findViewById(R.id.settingRating);
        inviaButton = (FloatingActionButton) findViewById(R.id.btnSendRece);
        mFirestore = FirebaseFirestore.getInstance();
        mCollection = mFirestore.collection("Giochi");
        inviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = titolo.getText().toString();
                rece = recensione.getText().toString();
                numStars = stars.getRating();
                rec = new Recensione(numStars, rece, title, acct.getId());
                giochiActivity = new GiochiActivity();
                mCollection.document(giochiActivity.id_gioco).collection("Recensioni").document(acct.getId()).set(rec);
                Intent intent = new Intent(NewRecensioneActivity.this, ProfiloActivity.class);
                startActivity(intent);
            }
        });
    }
}