package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.cirom.videogametime.R;

public class SostituisciRecensioneDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_dialog, null))
                .setMessage(R.string.richiesta_sostituzione)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(getContext(), NewRecensioneActivity.class));
                        getActivity().finish();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
