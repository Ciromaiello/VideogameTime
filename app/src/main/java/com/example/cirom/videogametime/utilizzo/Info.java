package com.example.cirom.videogametime.utilizzo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cirom.videogametime.R;

public class Info extends AppCompatActivity {
    private MediaPlayer ring;
    private ImageView img;
    private ImageView logo;
    private TextView copyright;
    private TextView peppe;
    private TextView toni;
    private TextView ciro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        ring= MediaPlayer.create(this,R.raw.eheh);
        ring.start();
        img=findViewById(R.id.HEHE);
        img.setImageResource(R.drawable.stadio);
        logo=findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo);
        copyright=findViewById(R.id.copyright);
        copyright.setText(R.string.copyright);
        peppe=findViewById(R.id.peppe);
        peppe.setText(R.string.peppe);
        toni=findViewById(R.id.toni);
        toni.setText(R.string.toni);
        ciro =findViewById(R.id.ciro);
        ciro.setText(R.string.ciro);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(ring.isPlaying()) {
            ring.stop();
        }
    }
}
