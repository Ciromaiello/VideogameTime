package com.example.cirom.videogametime.login;

import android.content.Context;
import android.content.Intent;



import android.content.SharedPreferences;
import android.net.Uri;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cirom.videogametime.R;

import com.example.cirom.videogametime.tutorial.MainActivity;
import com.example.cirom.videogametime.utilizzo.Account;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.mGoogleApiClient;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;

public class LoginActivity extends AppCompatActivity {


    private Button btnOspite;
    private Button button;
    private static final int RC_SIGN_IN = 3456;
    private FirebaseAuth mAuth;
    boolean NuovoAccesso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnOspite = findViewById(R.id.ospite_btn);
        button = findViewById(R.id.button);

        //tutto il codice seguente serve per l'accesso con google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //   .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Account.setmGoogleApiClient(mGoogleApiClient);

        //editor.putBoolean("NuovoAccesso",false);
        //editor.apply();

        /*istanziamo un nuovo oggetto GoogleSignInOptions con il parametro di default
        DEFAULT_SIGN_IN che permette di ottenere le informazioni di base dell’utente
        e passiamo questo oggetto al metodo addAPI() per la creazione di un nuovo oggetto
        GoogleApiClient che permetterà di accedere.
        */


        btnOspite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mAuth = FirebaseAuth.getInstance();


    }

    //metodo che gestisce l'autenticazione corretta o meno
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();

            // updateUI(true);
     /*   } else {
            updateUI(false);
        }*/
        }
    }
    //metodo che gestisce l'accesso vero e proprio
    private void signIn() {
        /*if (mSettings.getBoolean("NuovoAccesso", false)) {
            {
                startActivity(new Intent(this, MainActivity.class));
                editor.putBoolean("NuovoAccesso", true);
            }*/
            Log.d("signIn", "start");
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(Account.mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
            Log.d("signIn", "finish");
    }

    //metodo che all'accesso nasconde il bottone e alla disconnessione lo fa apparire
    /*
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            button.setVisibility(View.GONE);
            //ipotetico tasto bottone logout button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.VISIBLE);
            //bottone logout  button.setVisibility(View.GONE);
        }
    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                Accesso=true;
                FirstLogin();

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("signIn", "Google sign in failed", e);
                // ...

            }
        }
    }
    /* codice che serve a prendere informazioni sull'account
    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
if (acct != null) {
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();
    }

*/

    @Override
    public void onStart () {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("signIn", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("signIn", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("signIn", "signInWithCredential:failure", task.getException());
                            //   Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void FirstLogin()
    {//FTI se è true significa che è il primo login, false significa che non è il primo login

        if(mSettings.getBoolean("FTI",true))
        {
            mSettings.edit().putBoolean("FTI",false).apply();
            startActivity(new Intent(this,MainActivity.class));
        }
        else
        {
            startActivity(new Intent(this,ProfiloActivity.class));
        }
    }
}