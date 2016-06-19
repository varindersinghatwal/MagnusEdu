package com.magnus.edutech.view.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.magnus.edutech.utility.javautility.Encrypter;
import com.magnus.edutech.R;
import com.magnus.edutech.view.utility.Utilities;
import com.magnus.edutech.App.GlobalConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by joshi on 10/6/16.
 */
public class VideoPlayScreenActivity extends Activity {
   
    String videosLocalPath =null;
    String strDir = "";
    Context context;
    private final static int IV_LENGTH = 16; // Default length with Default 128
    // key AES encryption
    private final static int DEFAULT_READ_WRITE_BLOCK_BUFFER_SIZE = 1024;

    private final static String ALGO_RANDOM_NUM_GENERATOR = "SHA1PRNG";
    private final static String ALGO_SECRET_KEY_GENERATOR = "AES";
    private final static String ALGO_VIDEO_ENCRYPTOR = "AES/CBC/PKCS5Padding";
    String videoDirctory ;
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video);
        context=this;
        
       
        Intent intent = getIntent();
        if(videosLocalPath ==null && intent !=null)
        {
        	videosLocalPath = intent.getStringExtra(GlobalConstants.URL);
        }
        videoDirctory = Utilities.getVideoFolderLocation();
        new MakeDecryptCopy().execute();
    }

    private class MakeDecryptCopy extends AsyncTask<Void,Void,Void> {

        private String jsonStr;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(VideoPlayScreenActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

           
        	if(videosLocalPath !=null)
        	{
        		File streamOutPutThisFile = new File(videoDirctory, "videotest.mp4");
                File streamInputThisFile = new File(videosLocalPath);
                try {
                    byte[] keyData = {0x00,0x32,0x22,0x11,0x00,0x00,0x00,0x00,0x00,0x23,0x00,0x00,0x00,0x00,0x00,0x00};
                    SecretKey key = new SecretKeySpec(keyData, 0, keyData.length, ALGO_SECRET_KEY_GENERATOR);
                    byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C,
                            0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C,
                            0x07, 0x72, 0x6F, 0x5A };
                    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
                    //Encrypter.encrypt(key, paramSpec, assetManager.open("video11.mp4"), new FileOutputStream(outFile));
                    Encrypter.decrypt(key, paramSpec, new FileInputStream(streamInputThisFile), new FileOutputStream(streamOutPutThisFile));
                } catch (Exception e) {
                    e.printStackTrace();
                }

        	}
        	else
        	{
        		 GlobalConstants.showNoticeDialog("Error : ","Unable to play or download this video", 1, context);
        	}
            
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            VideoView view = (VideoView)findViewById(R.id.video_view);

            String path = videoDirctory + "/videotest.mp4";
            view.setVideoURI(Uri.parse(path));
            view.setMediaController(new MediaController(VideoPlayScreenActivity.this,true));
            view.canSeekBackward();
            view.canSeekForward();
            view.canPause();
            view.setKeepScreenOn(true);
            view.start();

        }
    }

}
