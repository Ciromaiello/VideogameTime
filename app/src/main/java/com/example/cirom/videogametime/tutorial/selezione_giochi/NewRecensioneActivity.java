package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import com.example.cirom.videogametime.R;
import static com.example.cirom.videogametime.utilizzo.Account.acct;

import com.example.cirom.videogametime.utilizzo.Account;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Date;

public class NewRecensioneActivity extends AppCompatActivity {

    private EditText titolo, recensione;
    private RatingBar stars;
    private FloatingActionButton inviaButton;
    private CollectionReference mCollection;
    private CollectionReference mAccount;
    private FirebaseFirestore mFirestore;
    GiochiActivity giochiActivity;
    Recensione rec1, rec2;
    private String title, rece;
    private float numStars;
    private Giochi giochi;
    private Date data;

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
        mAccount = mFirestore.collection("Account");
        giochi = new Giochi();
        inviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = titolo.getText().toString();
                rece = recensione.getText().toString();
                numStars = stars.getRating();
                data = new Date();
                giochiActivity = new GiochiActivity();
                rec1 = new Recensione(numStars, rece, title, acct.getId(), acct.getPhotoUrl().toString(), acct.getDisplayName(), data, giochiActivity.Title, giochiActivity.image, giochiActivity.id_gioco);
                rec2 = new Recensione(numStars, rece, title, acct.getId(), acct.getPhotoUrl().toString(), acct.getDisplayName(), data, RecensioneCompletaActivity.Gioco, RecensioneCompletaActivity.Immagine, RecensioneCompletaActivity.Idgioco);
                if(Account.posizione_recensione) {
                    Account.refresh_recensioni = true;
                    mCollection.document(giochiActivity.id_gioco)
                            .collection("Recensioni")
                            .document(acct.getId())
                            .set(rec1);
                    mAccount.document(acct.getId()).update("Giochi", FieldValue.arrayUnion(giochiActivity.id_gioco));
                    mCollection.document(giochiActivity.id_gioco)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    giochi = documentSnapshot.toObject(Giochi.class);
                                    Intent intent = new Intent(NewRecensioneActivity.this, GiochiActivity.class);
                                    intent.putExtra("Title",giochi.getNome());
                                    intent.putExtra("Description",giochi.getDescrizione());
                                    intent.putExtra("Generi",giochi.getGeneri());
                                    intent.putExtra("Piattaforme",giochi.getPiattaforme());
                                    intent.putExtra("Image",giochi.getImmagine());
                                    intent.putExtra("id", giochi.getId_gioco());
                                    startActivity(intent);
                                    finish();
                                }
                            });
                }
                else {
                    mCollection.document(RecensioneCompletaActivity.Idgioco)
                            .collection("Recensioni")
                            .document(acct.getId())
                            .set(rec2);
                    mAccount.document(acct.getId()).update("Giochi", FieldValue.arrayUnion(RecensioneCompletaActivity.Idgioco));
                    Intent intent = new Intent(NewRecensioneActivity.this, ProfiloActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}