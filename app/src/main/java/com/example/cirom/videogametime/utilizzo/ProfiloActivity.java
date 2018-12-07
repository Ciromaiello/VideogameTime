package com.example.cirom.videogametime.utilizzo;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.login.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.acct;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;


public class ProfiloActivity extends AppCompatActivity {

    private final String TAG = "DEMO_MISC";
    private BottomNavigationView navigation;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigation = findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.profilo_activity, new ProfiloFragment()).commit();
        navigation.setSelectedItemId(R.id.menu_account);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_account:
                        selectedFragment = new ProfiloFragment();
                        break;
                    case R.id.menu_home:
                        selectedFragment = new NewsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.profilo_activity, selectedFragment).commit();
                return true;
            }
        });
        Credenziali();
        mSettings = getBaseContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null && !Accesso)
        {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    private void Credenziali() {
        acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            Account.personName = acct.getDisplayName();
            Account.personGivenName = acct.getGivenName();
            Account.personFamilyName = acct.getFamilyName();
            Account.personEmail = acct.getEmail();
            Account.personId = acct.getId();
            Account.personPhoto = acct.getPhotoUrl();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
