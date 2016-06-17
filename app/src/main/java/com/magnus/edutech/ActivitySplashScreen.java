package com.magnus.edutech;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.magnus.edutech.BackgroundService.LoadDataInBackGround;
import com.magnus.edutech.BackgroundService.RealPathUtil;
import com.magnus.edutech.webservices.GlobalConstants;
import com.magnus.edutech.webservices.HelpingClass;

import org.json.JSONException;

public class ActivitySplashScreen extends Activity implements OnClickListener {

	// Class
	static HelpingClass helpingClass;
	LoadDataInBackGround loadDataBackgroundService;
	RealPathUtil realPathUtil;
	static MainRoutingClass mainRoutingClass;
	// Variables
    private TextView progressMessage;
	static Context context;
	static Activity activity;
	private Typeface typeface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		setStatusBarColor(null, getResources().getColor(android.R.color.black));

		/* MainRountiong Class to route activities */
		context = this;
		activity = this;

		startService(new Intent(this, LoadDataInBackGround.class));
		loadDataBackgroundService = new LoadDataInBackGround();
		helpingClass = new HelpingClass(context);
		realPathUtil = new RealPathUtil(context);
		mainRoutingClass = new MainRoutingClass(context);


		typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
		progressMessage = (TextView) findViewById(R.id.progressMessage);
		progressMessage.setTypeface(typeface);

		// intialize variables
		initVariable();
	}

	private void initVariable() {
		//hide status bar
		realPathUtil.hideActionBarAndStatusBar(this);
		realPathUtil.creatingLocalDirctories();
		checkNetworkConnection();

	}


	public void checkNetworkConnection() {
		if (GlobalConstants.chkNetAvailableOrNot(context)) {
			int rowCount  = helpingClass.getRowCountInTable(GlobalConstants.TABLE_COURSES);
			if (rowCount== 0) {
				try {
					/*
					 *  http://magnusedutech.org/admin/videotree.php */
					loadDataBackgroundService.getAllDataRelatedToVideoFromServer(context);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            else
            {
                splashHandler();
            }


		} else {
			showSplashScreenDialog("Network Error", "Please Check Your Internet Connection.", 1);
		}

	}

	public static void  splashHandler() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				splashScreenCompletion();

			}
		}, 3000);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
	}

	public static void splashScreenCompletion() {
		boolean isLogin = GlobalConstants.get_Preferences_boolean(GlobalConstants.LOGGED_IN, context);
		Intent signUpIntent = null;
		if (isLogin) {
				signUpIntent = new Intent(context, ActivityMain.class);
		} else {
			signUpIntent = new Intent(context, ActivityLoginScreen.class);
		}

		if (context != null && signUpIntent != null) {
			context.startActivity(signUpIntent);
            activity.finish();
		}

	}

	/* SET STATUS BAR COLOR : START */
	public void setStatusBarColor(View statusBar, int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window w = getWindow();
			w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}

	/* SET STATUS BAR COLOR : END */
	// Dialog Box for Information
	/***
	 * informationAlertBox
	 * 
	 * @description giving infomation about particular task
	 * @parameters title - title for alert box message - message to be show in
	 *             alert box hideShow - 0 = hide 1 = show
	 * 
	 */
	static AlertDialog alert;

	public void showSplashScreenDialog(String title, String message, int hideShow) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message).setTitle(title).setCancelable(false).setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						checkNetworkConnection();
						dialog.dismiss();

					}
				});
		alert = builder.create();
		alert.show();
	}

	public void hideAlertBox(boolean showHide) {
		if (alert != null)
			alert.dismiss();

	}

	public static void updateUI(int status)
	{
		splashHandler();
	}
}
