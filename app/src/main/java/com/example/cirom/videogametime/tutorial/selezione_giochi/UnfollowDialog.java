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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.cirom.videogametime.tutorial.selezione_giochi.GiochiActivity.Title;
import static com.example.cirom.videogametime.utilizzo.Account.acct;

public class UnfollowDialog extends DialogFragment {
    private FirebaseFirestore mFirestore;
    private CollectionReference mScelte;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_dialog, null))
                .setMessage(R.string.richiesta_unfollow)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mFirestore = FirebaseFirestore.getInstance();
                        mScelte = mFirestore.collection("Account");
                        Account.idGiochiScelti.remove(GiochiActivity.id_gioco);
                        mScelte.document(acct.getId()).collection("Scelte").document("Giochi Scelti")
                                .update("scelte", FieldValue.arrayRemove(GiochiActivity.id_gioco));
                        Toast.makeText(getContext(), "Non segui più " + Title, Toast.LENGTH_SHORT).show();

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}