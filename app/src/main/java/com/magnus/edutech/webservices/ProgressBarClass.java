package com.magnus.edutech.webservices;

/**
 * Developer : Jugal Kishor Joshi
 * Date      : Feb 6 2016
 */

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressBarClass {

    public ProgressDialog progressDialog;
    Context context;

    //CONSTRUCTOR
    public ProgressBarClass(Context ctx) {
        this.context = ctx;
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    }

    // SHOW HIDE
    public void hideShowProgressBar(boolean showHide, String Messages) {
        if (progressDialog != null) {
            if (showHide) {
                if (!progressDialog.isShowing()) {
                    progressDialog.setMessage(Messages);
                    if (Messages != null && !Messages.equalsIgnoreCase("")) {
                        progressDialog.show();
                    }
                }
            } else {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }
    }
}