package com.example.linda.originalcharacterapp.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class WarningFragment  extends DialogFragment {

    Boolean confirmation;
    Context mContext;
    public WarningFragment() {
        mContext = getActivity();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setTitle("Really?");
            alertDialogBuilder.setMessage("Are you sure?");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmation = true;
                    Toast.makeText(getContext(), "CLick-Click!!",Toast.LENGTH_LONG).show();
                }
            });

            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmation = false;
                    dialog.dismiss();
                }
            });


            return alertDialogBuilder.create();

    }

    }
