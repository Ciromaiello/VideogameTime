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
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.login.LoginActivity;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Gioco;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.acct;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;


public class ProfiloActivity extends AppCompatActivity {

    private final String TAG = "DEMO_MISC";
    private BottomNavigationView navigation;
    private FirebaseAuth firebaseAuth;
    private CollectionReference giochiref;
    private FirebaseFirestore mFirestore;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_high, menu);
       return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Switch per individuare la voce di menu scelta
        switch (item.getItemId()) {
            case R.id.action_search:
                // L'utente ha scelto "Cerca"
                Log.v("e", "Menu-> Cerca");
                Intent i = new Intent(this,SearchGiochiActivity.class);
                startActivity(i);

                return true;

            case R.id.action_settings:
                // L'utente ha scelto "impostazioni"
                Log.v("e", "Menu-> Impostazioni");
                Intent l = new Intent(this,ImpostazioniActivity.class);
                startActivity(l);
                return true;

            case R.id.action_logout:
                // L'utente ha scelto "logout"
                Log.v("e", "Menu-> Logout");
                signOut();

                return true;

            default:
                // Scelta non riconosciuta, passo il controllo al metodo della classe base
                return super.onOptionsItemSelected(item);
        }
    }



    private void signOut()
    {


        if(mSettings.getBoolean("Checked",true)){Accesso=false;}
        else{Accesso=true;
            FirebaseAuth.getInstance().signOut();}
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null) {
            mSettings.edit().putBoolean("cont",false).apply();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        else if(FirebaseAuth.getInstance().getCurrentUser() != null && mSettings.getBoolean("cont",true)){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        Account.utente = true;
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
        }
        else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void aggiungiGioco(String nome, ArrayList<String> generi, ArrayList<String> piattaforme) {
        Gioco g = new Gioco(nome,generi,piattaforme);
        giochiref.add(g);
    }
}
