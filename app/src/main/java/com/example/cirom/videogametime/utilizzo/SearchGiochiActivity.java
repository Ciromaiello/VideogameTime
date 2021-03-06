package com.example.cirom.videogametime.utilizzo;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import javax.annotation.Nullable;


public class SearchGiochiActivity extends AppCompatActivity {
    EditText search_edit_text;
    RecyclerView recyclerView;
    private CollectionReference mGiochi;
    private DatabaseReference mDatabase;
    FirebaseUser firebaseUser;
    private FirebaseFirestore mFirestore;
    ArrayList<String> giochiNameList;
    ArrayList<String> giochiPicList;
    ArrayList<String> key;
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
        search_edit_text = findViewById(R.id.search_edit_text);
        recyclerView =  findViewById(R.id.recyclerView);
        reset_String = findViewById(R.id.id_remove);
        mFirestore = FirebaseFirestore.getInstance();
        mGiochi = mFirestore.collection("Giochi");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
         * Create a array list for each node you want to use
         * */
        key = new ArrayList<>();
        giochiNameList = new ArrayList<>();
        giochiPicList = new ArrayList<>();

        reset_String.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key.clear();
                giochiNameList.clear();
                giochiPicList.clear();
                recyclerView.removeAllViews();
                search_edit_text.setText(null);
            }

        });

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                searchAdapter = new SearchAdapter(SearchGiochiActivity.this, giochiNameList, giochiPicList,key);
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
                    key.clear();
                    giochiNameList.clear();
                    giochiPicList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }

    private void setAdapter(final String searchedString) {
        key.clear();
        giochiNameList.clear();
        giochiPicList.clear();
        recyclerView.removeAllViews();
        mGiochi.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("errore", e);
                    } else {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Giochi g = document.toObject(Giochi.class);
                        String uid = document.getId();
                        String giochi_name = g.getNome();
                        String giochi_pic = g.getImmagine();
                        if (giochi_name.toLowerCase().contains(searchedString.toLowerCase())) {
                            giochiNameList.add(giochi_name);
                            giochiPicList.add(giochi_pic);
                            key.add(uid);
                            searchAdapter = new SearchAdapter(SearchGiochiActivity.this, giochiNameList, giochiPicList, key);
                            recyclerView.setAdapter(searchAdapter);
                            }
                    }
                }
            }
        });
    }
}


