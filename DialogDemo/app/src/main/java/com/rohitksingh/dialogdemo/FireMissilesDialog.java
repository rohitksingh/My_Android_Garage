package com.rohitksingh.dialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FireMissilesDialog extends DialogFragment {

    private YesNoClickListner listner;

    public FireMissilesDialog(YesNoClickListner listner){
        this.listner = listner;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("This is custom Dialog")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listner.yesClicked();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listner.noClicked();
                    }
                });
        return builder.create();
    }
}