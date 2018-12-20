package com.example.cirom.videogametime.utilizzo;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_generi.GeneriFragment;
import com.example.cirom.videogametime.tutorial.selezione_giochi.SceltaDalDatabase;
import com.example.cirom.videogametime.tutorial.selezione_piattaforme.Piattaforme;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.cirom.videogametime.utilizzo.Account.consoleQuery;

public class SearchGiochiActivity extends AppCompatActivity {
    EditText search_edit_text;
    RecyclerView recyclerView;
    private CollectionReference mGiochi;
    private DatabaseReference mDatabase;
    FirebaseUser firebaseUser;
    private FirebaseFirestore mFirestore;
    ArrayList<String> giochiNameList;
    ArrayList<String> giochiPicList;
    SearchAdapter searchAdapter;
    public ImageView reset_String;


    public SearchGiochiActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        reset_String = findViewById(R.id.id_remove);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
         * Create a array list for each node you want to use
         * */
        giochiNameList = new ArrayList<>();
        giochiPicList = new ArrayList<>();

        reset_String.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                giochiNameList.clear();
                giochiPicList.clear();
                recyclerView.removeAllViews();
                search_edit_text.setText(null);
            }

        });

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                searchAdapter = new SearchAdapter(SearchGiochiActivity.this, giochiNameList, giochiPicList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    giochiNameList.clear();
                    giochiPicList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }




    private void setAdapter(final String searchedString) {

        mDatabase.child("giochi").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 * Clear the list for every new search
                 * */

                giochiNameList.clear();
                giochiPicList.clear();
                recyclerView.removeAllViews();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String giochi_name = snapshot.child("nome").getValue(String.class);
                    String giochi_pic = snapshot.child("immagine").getValue(String.class);

                    if (giochi_name.toLowerCase().contains(searchedString.toLowerCase())) {
                        giochiNameList.add(giochi_name);
                        giochiPicList.add(giochi_pic);

                    }

                }

                searchAdapter = new SearchAdapter(SearchGiochiActivity.this, giochiNameList, giochiPicList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

