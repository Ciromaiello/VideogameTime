package com.example.cirom.videogametime.utilizzo;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.login.LoginActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRegistrar;
import com.google.firebase.auth.FirebaseUser;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;
import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.acct;
import static com.example.cirom.videogametime.utilizzo.Account.mGoogleApiClient;

public class GestioneProfiloFragment extends Fragment {
    private final String TAG = "DEMO_MISC";



    public GestioneProfiloFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gestioneprofilo, container, false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_high, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Switch per individuare la voce di menu scelta
        switch (item.getItemId()) {
            case R.id.action_search:
                // L'utente ha scelto "Cerca"
                Log.v(TAG, "Menu-> Cerca");
                return true;

            case R.id.action_settings:
                // L'utente ha scelto "impostazioni"
                Log.v(TAG, "Menu-> Impostazioni");
                Intent i = new Intent(getContext(),ImpostazioniActivity.class);
                startActivity(i);
                return true;

            case R.id.action_logout:
                // L'utente ha scelto "logout"
                Log.v(TAG, "Menu-> Logout");
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
            Intent intent = new Intent(getContext(),LoginActivity.class);
            startActivity(intent);
            getActivity().finish();

    }

}
