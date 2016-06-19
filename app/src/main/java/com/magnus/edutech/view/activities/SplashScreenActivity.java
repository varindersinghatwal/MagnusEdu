package com.magnus.edutech.view.activities;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.R;
import com.magnus.edutech.view.utility.Utilities;
import com.magnus.edutech.view.utility.interfaces.GenericInformativeDialogBoxInterface;
import com.magnus.edutech.webservices.MakeAServiceCall;
import com.magnus.edutech.webservices.RequestCallbackListener;
import com.magnus.edutech.webservices.lectureservices.servercall.MakeALectureServerCall;

public class SplashScreenActivity extends Activity implements OnClickListener, RequestCallbackListener {

    // Variables

    private Context context;
    private Activity activity;
    private Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setStatusBarColor(null, getResources().getColor(android.R.color.black));
        context = this;
        activity = this;

        utilities = new Utilities();
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);

        TextView progressMessage = (TextView) findViewById(R.id.progressMessage);
        progressMessage.setTypeface(typeface);

        // intialize variables
        initVariable();
    }

    private void initVariable() {
        //hide status bar
        /*realPathUtil.hideActionBarAndStatusBar(this);*/
        checkNetworkConnection();
    }

    //check connection and make a server call for subjects
    public void checkNetworkConnection() {
        if (utilities.isNetworkAvailable(context)) {
            MakeAServiceCall makeAServiceCall = new MakeAServiceCall();
            MakeALectureServerCall makeALectureServerCall = makeAServiceCall.getLectureServerCallObject(this);
            makeALectureServerCall.serverCallSubjects(context, null);
        } else {
            utilities.getGenericInformativeDialogBoxWithSingleButton(context, context.getString(R.string.error_network_header), context.getString(R.string.error_network_body), context.getString(R.string.alert_ok), false, new GenericInformativeDialogBoxInterface() {
                @Override
                public void PositiveMethod(DialogInterface dialog, int id) {
                    checkNetworkConnection();
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
    }

    private void splashHandler() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashScreenCompletion();

            }
        }, 3000);
    }

    private void splashScreenCompletion() {
        boolean isLogin = Utilities.get_Preferences_boolean(GlobalConstants.LOGGED_IN, context);
        Intent signUpIntent = null;
        if (isLogin) {
            signUpIntent = new Intent(context, HomeScreenActivity.class);
        } else {
            signUpIntent = new Intent(context, LoginScreenActivity.class);
        }

        if (context != null && signUpIntent != null) {
            context.startActivity(signUpIntent);
            activity.finish();
        }

    }

    /* SET STATUS BAR COLOR : START */
    private void setStatusBarColor(View statusBar, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    //Notification after request completed
    @Override
    public void notifyToCaller(boolean isExecuted, String response) {
        if (GlobalConstants.DEBUG)
            Log.d("UpdateUI", response);
        if (isExecuted)
            splashHandler();
        else
            utilities.getGenericInformativeDialogBoxWithSingleButton(context, context.getString(R.string.err_server_header), context.getString(R.string.err_server_body), context.getString(R.string.alert_ok), false, new GenericInformativeDialogBoxInterface() {
                @Override
                public void PositiveMethod(DialogInterface dialog, int id) {
                    checkNetworkConnection();
                }
            });
    }
}
