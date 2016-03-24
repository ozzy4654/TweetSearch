package kalan.ozan.tweetsearch.dialogs;


import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import kalan.ozan.tweetsearch.R;

public class SearchError extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setMessage(getArguments().getString(getString(R.string.msg_error_key)))
                .setTitle(R.string.invalid_search)
                .setPositiveButton(getString(R.string.ok_btn), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return alertDialog.create();
    }

    public static String changeString(String change){
        return change;
    }

    @Override
    public void onStart(){super.onStart();}

}
