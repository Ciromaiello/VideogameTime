package com.example.cirom.videogametime.utilizzo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cirom.videogametime.R;

public class ImpostazioniActivity extends AppCompatActivity {

    private ListView listView;
    ImpostazioniUtility impUtility;
    ArrayAdapter<String> namesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        impUtility = ImpostazioniUtility.getImpostazioniUtility(getApplicationContext());
        listView = (ListView) findViewById(R.id.listView);
        namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, impUtility.getInfo());
        listView.setAdapter(namesAdapter);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

       if(item.getItemId() == android.R.id.home)
           {
           finish();
           }
        return super.onOptionsItemSelected(item);
      }

}
