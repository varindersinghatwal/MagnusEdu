package com.magnus.edutech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebSettings.PluginState;

import com.magnus.edutech.webservices.GlobalConstants;

/**
 * Created by varinderatwal on 05/11/15.
 */
public class ActivityShowVideosFromVimeo extends Activity {

	//Xml
	
	private Context context;
	private  String VideoUrl = null;
	private  String VideoName = "Magnus Edu";
	private HTML5WebView webView;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		context = this;
		Intent intent = getIntent();
		if (VideoUrl == null && intent != null) {
			VideoUrl = intent.getStringExtra(GlobalConstants.URL);
			VideoName = intent.getStringExtra(GlobalConstants.NAME);
		}
		
		webView = new HTML5WebView(this,VideoName);

		if (VideoUrl != null) {

			String[] subSringSrray = VideoUrl.split("/");
			if (subSringSrray != null && subSringSrray.length > 0) {
				String VIDEO_ID = subSringSrray[3];

				// Auto playing vimeo videos in Android webview
				webView.getSettings().setJavaScriptEnabled(true);
				webView.getSettings().setAllowFileAccess(true);
				webView.getSettings().setAppCacheEnabled(true);
				webView.getSettings().setDomStorageEnabled(true);
				webView.getSettings().setPluginState(PluginState.OFF);
				webView.getSettings().setAllowFileAccess(true);

				webView.loadUrl("http://player.vimeo.com/video/" + VIDEO_ID
						+ "?player_id=player&autoplay=1&title=0&byline=0&portrait=0&api=1&maxheight=480&maxwidth=800");
				setContentView(webView.getLayout());
			} else {
				GlobalConstants.showNoticeDialog("Error : ", "Can't play this video", 1, context);
			}

		} else {
			GlobalConstants.showNoticeDialog("Error : ", "Can't play this video", 1, context);
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		webView.destroyDrawingCache();
		finish();
	}

	public static int convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		int dp = (int) (px / (metrics.densityDpi / 160f));
		return dp;
	}

}
