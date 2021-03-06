package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

import static com.example.cirom.videogametime.utilizzo.Account.acct;


public class RecensioniGiocoFragment extends Fragment {

    private RecyclerView list;
    private FloatingActionButton aggiungiReceButton;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;
    GiochiActivity giochiActivity;
    ArrayList<Recensione> recensiones;
    ArrayList<String> ids;

    public RecensioniGiocoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recensioni_gioco, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aggiungiReceButton = (FloatingActionButton) view.findViewById(R.id.aggiungiRecButton);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirestore = FirebaseFirestore.getInstance();
        recensiones = new ArrayList<>();
        ids = new ArrayList<>();
        mGiochi = mFirestore.collection("Giochi");
        giochiActivity = new GiochiActivity();
        mGiochi.document(giochiActivity.id_gioco).collection("Recensioni")
                .orderBy("data", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot document : task.getResult()) {
                            Recensione r = document.toObject(Recensione.class);
                            ids.add(r.getId());
                            recensiones.add(r);
                        }
                        aggiungiRecensione();
                    }
                });
        aggiungiReceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account.posizione_recensione = true;
                if(Account.fatto) {
                    if(ids.contains(acct.getId())) {
                        SostituisciRecensioneDialog newFragment = new SostituisciRecensioneDialog();
                        newFragment.show(getFragmentManager(), "Scelta");
                    }
                    else {
                        if (Account.utente) {
                            startActivity(new Intent(getContext(), NewRecensioneActivity.class));
                            getActivity().finish();}
                        else {
                            Toast.makeText(getActivity(), R.string.recensione_ospite, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getActivity(), R.string.funzionalità_tutorial, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void aggiungiRecensione() {
        RecensioniGiocoAdapter myAdapter = new RecensioniGiocoAdapter(getContext(), recensiones);
        list.setAdapter(myAdapter);
    }
}
