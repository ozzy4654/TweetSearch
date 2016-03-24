package kalan.ozan.tweetsearch.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kalan.ozan.tweetsearch.R;

public class ShowTweet extends DialogFragment {

    @Bind(R.id.tweet_id) TextView mTwtId;
    @Bind(R.id.user_name) TextView mUsrId;
    @Bind(R.id.tweet_text) TextView mTwtTxt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.show_tweet_dialog, null);
        ButterKnife.bind(this, v);

        mTwtId.setText(getArguments().getString(getString(R.string.twt_id)));
        mTwtTxt.setText(getArguments().getString(getString(R.string.twt_txt)));
        mUsrId.setText(getArguments().getString(getString(R.string.user_id)));

        alertDialog
                .setView(v)
                .setTitle(R.string.twt_prompt)
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
