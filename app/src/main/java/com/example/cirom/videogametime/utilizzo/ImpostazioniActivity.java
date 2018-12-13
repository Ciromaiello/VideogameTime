package com.example.cirom.videogametime.utilizzo;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.login.LoginActivity;
import com.example.cirom.videogametime.tutorial.MainActivity;
import com.squareup.picasso.Picasso;

public class ImpostazioniActivity extends AppCompatActivity {

    private ListView listView;
    ImpostazioniUtility impUtility;
    ArrayAdapter<String> namesAdapter;
    private ImageView imgProfilo;
    private TextView nomeutente;
    private TextView email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);
        nomeutente = findViewById(R.id.nomeAccount);
        email = findViewById(R.id.nomeEmail);
        nomeutente.setText(Account.getPersonName());
        email.setText(Account.getPersonEmail());
        imgProfilo = findViewById(R.id.profiloimg);
        Picasso.with(this).load(Account.personPhoto).transform(new CircleTransform(35, 10)).into(imgProfilo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        impUtility = ImpostazioniUtility.getImpostazioniUtility(getApplicationContext());
        listView = findViewById(R.id.listView);
        namesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, impUtility.getInfo());
        listView.setAdapter(namesAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        Intent i = new Intent(view.getContext(),MainActivity.class);
                        startActivityForResult(i,0);


                    case 1:
                        Intent l = new Intent(view.getContext(),ProfiloActivity.class);
                        startActivityForResult(l,1);


                    case 2:
                }
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
