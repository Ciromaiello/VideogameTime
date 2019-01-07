package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.Account;
import com.example.cirom.videogametime.utilizzo.ProfiloActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.cirom.videogametime.utilizzo.Account.acct;

public class EliminaDialog extends DialogFragment {
    private FirebaseFirestore mFirestore;
    private CollectionReference mScelte;
    private CollectionReference mGiochi;
    private Giochi giochi;
    private Intent intent;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        giochi = new Giochi();
        builder.setView(inflater.inflate(R.layout.fragment_dialog, null))
                .setMessage(R.string.richiesta_elimina)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mFirestore = FirebaseFirestore.getInstance();
                        mScelte = mFirestore.collection("Account");
                        mGiochi = mFirestore.collection("Giochi");
                        mScelte.document(acct.getId())
                                .update("Giochi", FieldValue.arrayRemove(RecensioneCompletaActivity.Idgioco));
                        mGiochi.document(RecensioneCompletaActivity.Idgioco)
                                .collection("Recensioni").document(acct.getId()).delete();
                        if(Account.posizione_recensione) {
                            intent = new Intent(getActivity(), GiochiActivity.class);
                            intent.putExtra("Title",GiochiActivity.Title);
                            intent.putExtra("Description",GiochiActivity.Description);
                            intent.putExtra("Generi",GiochiActivity.Generi);
                            intent.putExtra("Piattaforme",GiochiActivity.Piattaforme);
                            intent.putExtra("Image",GiochiActivity.image);
                            intent.putExtra("id", GiochiActivity.id);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        else {
                            intent = new Intent(getActivity(), ProfiloActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}