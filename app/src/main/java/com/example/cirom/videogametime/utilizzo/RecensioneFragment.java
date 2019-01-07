package com.example.cirom.videogametime.utilizzo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.GiochiActivity;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Recensione;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import static com.example.cirom.videogametime.utilizzo.Account.acct;

public class RecensioneFragment extends Fragment {

    private RecyclerView list;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;
    private CollectionReference mAccount;
    GiochiActivity giochiActivity;
    ArrayList<Recensione> recensiones;


    public RecensioneFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recensione, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirestore = FirebaseFirestore.getInstance();
        recensiones = new ArrayList<>();
        mGiochi = mFirestore.collection("Giochi");
        mAccount = mFirestore.collection("Account");
        giochiActivity = new GiochiActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        recensiones.clear();
        list = (RecyclerView) getView().findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setHasFixedSize(true);
        Query();
    }



    private void Query() {
        Account.nomire = new ArrayList<>();
        mAccount.document(acct.getId())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ArrayList<String> S = (ArrayList<String>) documentSnapshot.get("Giochi");
                        if(S!=null) {
                            Account.nomire.addAll(S);
                            int i = Account.nomire.size()-1;
                            prendiRecensione(i);
                        }
                    }
                });
    }

    private void prendiRecensione(final int i) {
        if(i<0){
            aggiungiRecensione();
        }
        else {
            mGiochi.document(Account.nomire.get(i)).collection("Recensioni")
                    .document(acct.getId())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Recensione r = documentSnapshot.toObject(Recensione.class);
                            Log.e("FFFFF", "la recensione è " + r.getTitolo());
                            if(!recensiones.contains(r)) {
                                recensiones.add(r);
                                int f = i;
                                f--;
                                prendiRecensione(f);
                            }
                        }
                    });
        }
    }

    private void aggiungiRecensione() {
        RecensioniAdapter myAdapter = new RecensioniAdapter(getContext(), recensiones);
        list.setAdapter(myAdapter);
    }
}