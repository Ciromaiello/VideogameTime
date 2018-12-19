package com.example.cirom.videogametime.utilizzo;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;

public class NewsFragment extends Fragment {
    private final String TAG = "DEMO_MISC";
    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

}
