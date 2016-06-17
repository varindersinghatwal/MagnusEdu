package com.magnus.edutech;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.magnus.edutech.webservices.GlobalConstants;

public class HTML5WebView extends WebView {
	
	private Context 							mContext;
	
	private View 		mVideoProgressView;
	private View								mCustomView;
	private FrameLayout							mContentView;
	private FrameLayout							mBrowserFrameLayout;
	private FrameLayout							mLayout;
	
    static final String TAG = "HTML5WebView";
    private String name="";
	    
	private void init(Context context,String Name) {
		mContext = context;		
		Activity a = (Activity) mContext;
		name=Name;
		mLayout = new FrameLayout(context);
		
		mBrowserFrameLayout = (FrameLayout) LayoutInflater.from(a).inflate(R.layout.custom_screen, null);
		mContentView = (FrameLayout) mBrowserFrameLayout.findViewById(R.id.main_content);
	
		
		mLayout.addView(mBrowserFrameLayout, COVER_SCREEN_PARAMS);
		setWebViewClient(new MyWebViewClient());
	       
	    // Configure the webview
	    WebSettings s = getSettings();
	    s.setBuiltInZoomControls(true);
	    s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
	    s.setUseWideViewPort(true);
	    s.setLoadWithOverviewMode(true);
	    s.setSavePassword(true);
	    s.setSaveFormData(true);
	    s.setJavaScriptEnabled(true);
	    
	    // enable navigator.geolocation 
	    s.setGeolocationEnabled(true);
	    s.setGeolocationDatabasePath("/data/data/com.example.vimeotest/databases/");
	    
	    // enable Web Storage: localStorage, sessionStorage
	    s.setDomStorageEnabled(true);
	    
	    
	    if (mVideoProgressView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mVideoProgressView = inflater.inflate(R.layout.video_loading_progress, null);
        }
	   
	    mContentView.addView(this);
	    mContentView.addView(mVideoProgressView);
	}

	public HTML5WebView(Context context,String Name) {
		super(context);
		init(context,Name);
	}

	public HTML5WebView(Context context, AttributeSet attrs,String Name) {
		super(context, attrs);
		init(context,Name);
	}

	public HTML5WebView(Context context, AttributeSet attrs, int defStyle,String Name) {
		super(context, attrs, defStyle);
		init(context,Name);
	}
	
	public FrameLayout getLayout() {
		return mLayout;
	}
	
    public boolean inCustomView() {
		return (mCustomView != null);
	}
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		if ((mCustomView == null) && canGoBack()){
    			goBack();
    			return true;
    		}
    	}
    	return super.onKeyDown(keyCode, event);
    }

  
	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if(GlobalConstants.DEBUG)
			Log.i(TAG, "shouldOverrideUrlLoading: "+url);
	    	// don't override URL so that stuff within iframe can work properly
	        // view.loadUrl(url);
	        return false;
	    }

		@Override
		public void onLoadResource(WebView view, String url) {
            if(GlobalConstants.DEBUG)
			Log.d(TAG,"onLoadResource. url: "+url);
			super.onLoadResource(view, url);
		}
		
		 @Override
         public void onPageStarted(WebView view, String url, Bitmap favicon) {

          super.onPageStarted(view, url, favicon);
          mVideoProgressView.setVisibility(View.VISIBLE);
         }
		 @Override
         public void onPageFinished(WebView view, String url) {
			 mVideoProgressView.setVisibility(View.GONE);
			 ((Activity) mContext).setTitle(name);
			 mContentView.removeView(mVideoProgressView);
         }

         @Override
         public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
             Toast.makeText(((Activity) mContext), "Error:" + description, Toast.LENGTH_SHORT).show();

         }
		
		
	}
	
	static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS =
        new FrameLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
}