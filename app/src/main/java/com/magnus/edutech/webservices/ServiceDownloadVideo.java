package com.magnus.edutech.webservices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.magnus.edutech.DataBase.DatabaseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/*By :J.K.JOSHI 
	 * Date : Oct. ,2015
	 * start :->*/
public class ServiceDownloadVideo extends AsyncTask<Void, Void, String> 
	{

		Context context;
		String ServicesUrl=null; 
		int fromWhere=-1;
		public ProgressDialog progressDialog;
		HashMap<String, String> result;
		String videoName = "video";

    	JSONObject jsonData= new JSONObject();
    	
		public  SharedPreferences appSharedPreference;
		public  DatabaseHandler db;
		
		public ServiceDownloadVideo(Context ctx,JSONObject jsonData) throws JSONException
		{
			this.context=ctx;
			this.jsonData=jsonData;
			
			fromWhere = jsonData.getInt(GlobalConstants.FROM);
			db = new DatabaseHandler(context);
			videoName = videoName+jsonData.getString(GlobalConstants.SUBJECT_ID)+jsonData.getString(GlobalConstants.CATEGORY_ID)+jsonData.getString(GlobalConstants.VIDEOS_ID);
			
			
			progressDialog = new ProgressDialog(ctx);
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(jsonData.getString(GlobalConstants.MESSAGE));
			if( jsonData.getString(GlobalConstants.MESSAGE) !=null && !jsonData.getString(GlobalConstants.MESSAGE).equalsIgnoreCase(""))
			{
				progressDialog.show();
			}
			appSharedPreference = ctx.getApplicationContext().getSharedPreferences(GlobalConstants.PREFERENCE_NAME, 0);
		}
		
		
		
		

		@Override
		protected String doInBackground(Void... params) 
		{
			
			
			String JsonString=null;
			try {
				
					if(GlobalConstants.chkNetAvailableOrNot(context))
					{
						
						String videoURL = GlobalConstants.DOWNLOAD_VIDEO_URL+videoName+GlobalConstants.DOWNLOAD_VIDEO_EXTENSION;
						 DownloadFile("http://magnusedutech.org/admin/videos5630encry/video011.mp4",videoName+GlobalConstants.DOWNLOAD_VIDEO_EXTENSION);   
					}
					else
					{
						
					}
				
				} catch (Exception e) 
		       {
		    	   System.out.println(" ASYNC Sys : "+e);
		       }
			return JsonString;
		}

		@Override
		protected void onPostExecute(String resultJsonString)
		{
			super.onPostExecute(resultJsonString);
			if (progressDialog != null) 
			{
				if (progressDialog.isShowing()) 
					{
						progressDialog.dismiss();
						progressDialog = null;
					}
			}
						
				
						
		}
		
		 public boolean DownloadFile(String fileURL, String fileName) 
		 {
			 boolean isDownloadOrNot = false;
			 
		        try 
		        	{
		            	
		        		String RootDir =GlobalConstants.getVideoFolderLocation();
		           
		        		URL urlVideo = new URL(fileURL);
		        		HttpURLConnection connection = (HttpURLConnection) urlVideo.openConnection();
		        		connection.setRequestMethod("GET");
		        		connection.setDoOutput(true);
		        		connection.connect();
		        		FileOutputStream streamOutputFile = new FileOutputStream(new File(RootDir,fileName));
		        		InputStream		 streamInput = connection.getInputStream();
		            byte[] buffer = new byte[1024];
		            int len1 = 0;

		            while ((len1 = streamInput.read(buffer)) > 0) 
		            {                          
		            	streamOutputFile.write(buffer, 0, len1);               
		            }       
		            streamOutputFile.close();
		             
		            isDownloadOrNot =true;

		        } catch (Exception e) {

		            Log.d("Error....", e.toString());
		        }

		       return  isDownloadOrNot;
		        
		    }
			
			 public static   void showNoticeDialog(String title,String message, int hideShow,Context context) 
			   {
			      AlertDialog alert ;
					 AlertDialog.Builder builder = new AlertDialog.Builder(context);
					 builder.setMessage(message)
					 .setTitle(title)
					        .setCancelable(false)
					        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					            public void onClick(DialogInterface dialog, int id) 
					            {
					            	
					            	 dialog.dismiss();   
					               
					            }
					        });
					 alert = builder.create();
					 alert.show();
			   }
			
			
		}
