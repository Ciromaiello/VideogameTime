package com.example.cirom.videogametime;
//codice esempio per firebase per non andare ogni volta in un altro progetto

/*import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Prova {
package com.example.peppe.provafirebase;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

    public class MainActivity extends AppCompatActivity {

        private Button PS4;
        private Button XboxOne;

        private FirebaseDatabase mDatabase;
        private TextView mName;

        private ArrayList<String> Giochi =new ArrayList<>();
        private int i;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            PS4 =  findViewById(R.id.PS4);
            XboxOne= findViewById(R.id.XboxOne);
            mName =  findViewById(R.id.Name);

            // mDatabase = FirebaseDatabase.getInstance().getReference("Piattaforme").child("PS4").child("Sparatutto");  //se metto qualcosa di particolare dentro getReference mi riferisco ad un particolare ramo


            PS4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    /* INSERIRE CLASSE SU DATABASE **/
               /* mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference refGioco = mDatabase.getReference("Giochi").push();
                String id_giochi = refGioco.getKey();
                refGioco.setValue(new Gioco("Fifa18", "PS4", "Sport", "Buggato", 9, id_giochi));
                */

               /*     //creo le liste dove salvare le piattaforme e i generi selezionati
                    final List<String> piattaforme= new ArrayList<>();
                    final List<String> generi= new ArrayList<>();

                    //esempio di aggiunta delle cose
                    piattaforme.add("PS4");
                    generi.add("Sparatutto");

                    //solite robe del database
                    mDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference refPiattaforma = mDatabase.getReference("Piattaforme");

                    //in tempo reale che deve fare/prendere dal database
                    refPiattaforma.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                            //faccio i foreach per piattaforme, generi per piattaforma, giochi per genere
                            for (Iterator<String> p = piattaforme.iterator(); p.hasNext();){
                                String piattaforma= p.next();      //mi va alla piattaforma scelta
                                for (Iterator<String> g=generi.iterator(); g.hasNext();){
                                    String genere= g.next();
                                    for (DataSnapshot gioco : dataSnapshot.child(piattaforma).child(genere).getChildren()) //elemento fondamentale per la ricerca nei figli che voglio
                                    {
                                        String j = gioco.getKey();
                                        if (!Giochi.contains(j))     //controllo per vedere se esiste già il gioco
                                            Giochi.add(j);
                                    }
                                }
                            }

                            mName.setText("Giochi :" +Giochi.get(0));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError)
                        {

                        }
                    });



                /*mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String s = dataSnapshot.getValue().toString();
                        Giochi.add(i,s);
                        i++;
                        mName.setText("Name :" +Giochi);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/
                    // mDatabase.child("Account").setValue("io"); //child sta per Nome e Value per valore nel database, fare child.child fa sottorami


       /*XboxOne.setOnClickListener(new View.OnClickListener()
       {

            @Override
            public void onClick(View v) {

                mDatabase = FirebaseDatabase.getInstance().getReference("Piattaforme").child("XboxOne");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String s = dataSnapshot.getValue().toString();
                        Giochi.add(i,s);
                        i++;
                        mName.setText("Name :" +Giochi);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                // mDatabase.child("Account").setValue("io"); //child sta per Nome e Value per valore nel database, fare child.child fa sottorami
            }
    });*/

