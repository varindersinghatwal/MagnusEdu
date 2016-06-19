package com.magnus.edutech.view.Utility;

/**
 * Developer : Jugal Kishor Joshi
 * Date      : Feb 6 2016
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.magnus.edutech.R;
import com.magnus.edutech.view.Utility.interfaces.GenericConfirmationDialogBoxInterface;
import com.magnus.edutech.view.Utility.interfaces.GenericInformativeDialogBoxInterface;
import com.magnus.edutech.view.Utility.interfaces.GenericPromptDialogBoxInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public static long back_pressed;
    private final String TAG = Utilities.class.getName();

    public Utilities() {
    }
    /**
     * Network connectivity.
     */
    // Checking network connectivity
    public boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Get String as Per String Id
    public String getStringFromId(int id, Context context) {
        return context.getResources().getString(id);
    }

    // Validation Container
    public boolean isNotEmpty(final String val) {
        return val != null && !val.trim().equalsIgnoreCase("");
    }

    // email validation :
    public boolean isValidEmail(final String mailAddress) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (mailAddress != null) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(mailAddress);
            return matcher.matches();
        }
        return false;

    }

    //Phone Number validation
    public boolean isValidPhoneNumber(String phoneNumber) {
        String Regex = "[^\\d]";
        if (phoneNumber != null) {
            String PhoneDigits = phoneNumber.replaceAll(Regex, "");
            return PhoneDigits.length() == 10;
        }
        return false;
    }

    /***
     * Generic confirmation dialog box
     *
     * @description Confirmation box with message
     * @parameters mContext                      - Context of activity.
     * title                         - Title for confirmation box.
     * message                       - Message to be show in alert box.
     * positiveBtnCaption            - Positive button string.
     * negativeBtnCaption            - Negative button string.
     * isCancelable                  - Sets whether the dialog is cancelable or not. Default is true.
     * dialogBoxInterface            - Interface object that handles its click.
     */
    public void getGenericConfirmDialog(Context mContext, String title, String message, String positiveBtnCaption, String negativeBtnCaption, boolean isCancelable, final GenericConfirmationDialogBoxInterface dialogBoxInterface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        int imageResource = android.R.drawable.ic_dialog_alert;
        Drawable image = mContext.getResources().getDrawable(imageResource);

        builder.setTitle(title).setMessage(message)
                .setIcon(image)
                .setCancelable(false)
                .setPositiveButton(positiveBtnCaption, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogBoxInterface.PositiveMethod(dialog, id);
                    }
                })
                .setNegativeButton(negativeBtnCaption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialogBoxInterface.NegativeMethod(dialog, id);
                    }
                });

        AlertDialog alert = builder.create();
        alert.setCancelable(isCancelable);
        alert.show();
        if (isCancelable) {
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    dialogBoxInterface.NegativeMethod(null, 0);
                }
            });
        }
    }


    /***
     * Generic Informative dialog box with single button
     *
     * @description Confirmation box with message
     * @parameters mContext                      - Context of activity.
     * title                         - Title for confirmation box.
     * message                       - Message to be show in alert box.
     * positiveBtnCaption            - Positive button string.
     * isCancelable                  - Sets whether the dialog is cancelable or not. Default is true.
     * dialogBoxInterface            - Interface object that handles its click.
     */
    public void getGenericInformativeDialogBoxWithSingleButton(Context mContext, String title, String message, String positiveBtnCaption, boolean isCancelable, final GenericInformativeDialogBoxInterface dialogBoxInterface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        int imageResource = android.R.drawable.ic_dialog_alert;
        Drawable image = mContext.getResources().getDrawable(imageResource);

        builder.setTitle(title).setMessage(message)
                .setIcon(image)
                .setCancelable(false)
                .setPositiveButton(positiveBtnCaption, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogBoxInterface.PositiveMethod(dialog, id);
                    }
                });

        AlertDialog alert = builder.create();
        alert.setCancelable(isCancelable);
        alert.show();
        if (isCancelable) {
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    dialogBoxInterface.PositiveMethod(null, 0);
                }
            });
        }
    }

    /***
     * Generic confirmation dialog box
     *
     * @description Confirmation box with message
     * @parameters mContext                      - Context of activity.
     * title                         - Title for confirmation box.
     * message                       - Message to be show in alert box.
     * positiveBtnCaption            - Positive button string.
     * negativeBtnCaption            - Negative button string.
     * isCancelable                  - Sets whether the dialog is cancelable or not. Default is true.
     * dialogBoxInterface            - Interface object that handles its click.
     */
    public void getGenericPromptDialog(Context mContext,View promptView,String positiveBtnCaption, String negativeBtnCaption, boolean isCancelable, final GenericPromptDialogBoxInterface dialogBoxInterface) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // set prompts.xml to alertdialog builder
        builder.setView(promptView);

        int imageResource = android.R.drawable.ic_dialog_alert;
        Drawable image = mContext.getResources().getDrawable(imageResource);

        final EditText userInput = (EditText) promptView
                .findViewById(R.id.forgetPasswordEmail);

        builder/*.setTitle(title).setMessage(message)*/
                .setIcon(image)
                .setCancelable(false)
                .setPositiveButton(positiveBtnCaption, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String email = userInput.getText().toString().trim();
                        dialogBoxInterface.PositiveMethod(dialog, id,email);
                    }
                })
                .setNegativeButton(negativeBtnCaption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialogBoxInterface.NegativeMethod(dialog, id);
                    }
                });

              AlertDialog alert = builder.create();
                alert.setCancelable(isCancelable);
                alert.show();
                if (isCancelable) {
                    alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface arg0) {
                            dialogBoxInterface.NegativeMethod(null, 0);
                        }
                    });
                }
    }

    /***
     * Informative Alert Box
     *
     * @description giving information about particular task
     * @parameters title        - title for alert box
     * message      - message to be show in alert box
     * mContext     - context of Activity
     */
    public void showInformativeDialog(String title, String message, Context mContext) {
        int imageResource = android.R.drawable.ic_dialog_alert;
        Drawable image = mContext.getResources().getDrawable(imageResource);

        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();

                    }
                });
        alert = builder.create();
        alert.show();
    }



    // On activity back pressed.
    public void onActivityBackPressedExit(Activity activity) {
        if (back_pressed + 2000 > System.currentTimeMillis()) activity.onBackPressed();
        else
            Toast.makeText(activity.getApplicationContext(), getStringFromId(R.string.exitMessage, activity.getApplicationContext()), Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

}
