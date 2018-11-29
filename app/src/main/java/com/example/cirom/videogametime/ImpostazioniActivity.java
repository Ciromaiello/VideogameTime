package com.example.cirom.videogametime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        impUtility = ImpostazioniUtility.getImpostazioniUtility(getApplicationContext());
        listView = (ListView) findViewById(R.id.listView);
        namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, impUtility.getInfo());
        listView.setAdapter(namesAdapter);

    }


}
