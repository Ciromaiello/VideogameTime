package com.example.cirom.videogametime.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.MainActivity;
import com.example.cirom.videogametime.utilizzo.Account;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
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
import static com.example.cirom.videogametime.utilizzo.Account.isAccesso;
import static com.example.cirom.videogametime.utilizzo.Account.mGoogleApiClient;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;

public class LoginActivity extends AppCompatActivity {


    private Button btnOspite;
    private Button button;
    private CheckBox checkBox;
    private static final int RC_SIGN_IN = 3456;
    private FirebaseAuth mAuth;
    public GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnOspite = findViewById(R.id.ospite_btn);
        button = findViewById(R.id.button);
        checkBox = findViewById(R.id.checkBox_login);
        Account.utente = false;

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
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSettings.edit().putBoolean("Checked",!mSettings.getBoolean("Checked",true)).apply();

            }

        });
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
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
    }

    //metodo che gestisce l'autenticazione corretta o meno
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
        }
    }

    //metodo che gestisce l'accesso vero e proprio
    private void signIn() {
        Log.d("signIn", "start");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(Account.mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d("signIn", "finish");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }
            catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("signIn", "Google sign in failed", e);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        if(isAccesso()){signOut();}
        mSettings.edit().putBoolean("cont",true).apply();
        if(mSettings.getBoolean("Checked",true)){
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
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
                            FirstLogin();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("signIn", "signInWithCredential:failure", task.getException());
                            //   Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }


       private void signOut() {
           Account.utente = false;
           mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
    }

    public void FirstLogin()
    {//se è true significa che è il primo login, false significa che non è il primo login

        if(mSettings.getBoolean(mAuth.getUid(),true)) {
            Account.utente = true;
            mSettings.edit().putBoolean("cont",false).apply();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else {
            Account.utente = true;
            mSettings.edit().putBoolean("cont",false).apply();
            startActivity(new Intent(this,ProfiloActivity.class));
            finish();
        }
    }
}