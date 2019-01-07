package com.example.cirom.videogametime.utilizzo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import static com.example.cirom.videogametime.utilizzo.Account.idGiochiScelti;
import static com.example.cirom.videogametime.utilizzo.Account.acct;

public class GestioneProfiloFragment extends Fragment {

    private FirebaseFirestore mFirestore;
    private  ArrayList<Giochi> giochi ;
    private CollectionReference mGiochi;
    private CollectionReference mScelte;

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
        mFirestore=FirebaseFirestore.getInstance();
        mGiochi=mFirestore.collection("Giochi");
        mScelte=mFirestore.collection("Account");
        idGiochiScelti = new ArrayList<>();
        giochi = new ArrayList<>();
        LetturaId();
    }

    private void AggiungiGiochi(int i) {
        if(i<idGiochiScelti.size()) {
            Query(i);
        }
        else {
            RecyclerView myr = (RecyclerView) getView().findViewById(R.id.cardgiochi);
            final GiochiSceltiAdapter myAdapter = new GiochiSceltiAdapter(getContext(), giochi);
            myr.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            myr.setAdapter(myAdapter);
        }
    }

    private void Query(final int i) {
        mGiochi.document(idGiochiScelti.get(i))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Giochi g = documentSnapshot.toObject(Giochi.class);
                        giochi.add(g);
                        int f = i;
                        f++;
                        AggiungiGiochi(f);
                    }
                });
    }

    private void LetturaId()
    {
        mScelte.document(acct.getId()).collection("Scelte").document("GiochiScelti")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                GiochiScelti a = documentSnapshot.toObject(GiochiScelti.class);
                idGiochiScelti = a.getScelte();
                Log.e("WE", "le mie scelte sono "+a.getScelte());
                int i=0;
                AggiungiGiochi(i);
            }
        });
    }
}