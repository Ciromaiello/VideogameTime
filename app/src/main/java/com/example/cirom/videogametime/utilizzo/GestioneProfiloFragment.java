package com.example.cirom.videogametime.utilizzo;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Recensione;
import com.example.cirom.videogametime.tutorial.selezione_giochi.RecyclerViewAdapter;
import com.example.cirom.videogametime.tutorial.selezione_giochi.SceltaDalDatabase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRegistrar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.cirom.videogametime.utilizzo.Account.genQuery;
import static com.example.cirom.videogametime.utilizzo.Account.getPersonEmail;
import static com.example.cirom.videogametime.utilizzo.Account.giochiscelti;
import static com.example.cirom.videogametime.utilizzo.Account.mSettings;
import static com.example.cirom.videogametime.utilizzo.Account.Accesso;
import static com.example.cirom.videogametime.utilizzo.Account.acct;
import static com.example.cirom.videogametime.utilizzo.Account.mGoogleApiClient;

public class GestioneProfiloFragment extends Fragment {
    private final String TAG = "DEMO_MISC";
    private FirebaseFirestore mFirestore;
    private CollectionReference mAccount;
    public  ArrayList<Giochi> giochi ;


    public GestioneProfiloFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.fragment_gestioneprofilo, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        scelta();
        /*    Log.e(TAG, "account" + acct.getId());
            mAccount.document(acct.getId()).collection("Scelte")
                    .get()
                    .(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                                    SceltaDalDatabase g = documentSnapshot.toObject(SceltaDalDatabase.class);
                                    ArrayList<String> S = g.getGiochi();

                        }
                    });

            RecyclerView myr = (RecyclerView) view.findViewById(R.id.cardgiochi);
            final RecyclerViewAdapter myAdapter1 = new RecyclerViewAdapter(getContext(), giochi);
            myr.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            myr.setAdapter(myAdapter1);

        }
    }

*/
        }

    private void scelta(){
      RecyclerView myr = (RecyclerView) getView().findViewById(R.id.cardgiochi);
      final RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), Account.getGiochiscelti());
      myr.setLayoutManager(new GridLayoutManager(getActivity(), 3));
      myr.setAdapter(myAdapter);
                        }

}