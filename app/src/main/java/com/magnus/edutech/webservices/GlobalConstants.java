package com.magnus.edutech.webservices;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class GlobalConstants 

{
	
	

	public static  String VIDEO_FOLDER	 				      			   = "" ;
	public static  String OTHER_FOLDER	 							   		= "" ;
	
	// Application Folder Name 
	public static final String APPLICATION_FOLDER_NAME 					= "Magnus" ;
	public static final String APPLICATION_SUBFOLDER_VIDEOS				= "Videos" ;
	public static final String APPLICATION_SUBFOLDER_OTHERS    			= "Others" ;
	public static final  String FONT_PATH_LIGHT 						= "fonts/Roboto-Medium.ttf";/*arial.ttf*/
	
	// PREFERENCE NAME
		public static final  String PREFERENCE_NAME 						= "MAGNUS_1.0";
		public static final  String LOGGED_IN 								= "logged_in";
	
	// REQUEST VALUE : 
	
		public static final  String TRUE 									="TRUE";
		public static final  String FALSE 									="FALSE";
		public static final  String HAS_BUNDLE								="has_bundle";
		
		public static final int ACTIVITY_LOGIN								= 20;
		public static final int ACTIVITY_REGISTRATION						= 30;

	// For fragments
	public static final int FRAGMENT_HOME_COURSE				            = 42;
	public static final int FRAGMENT_CHAPTERS_LIST                          = 43;
	public static final int FRAGMENT_VIDEOS_LIST		                    = 44;
	public static final int FRAGMENT_ABOUT_US					            = 45;
	
	// Login Service 
	public static final  String EMAIL		 							="email";
	public static final  String USER_NAME		 						="username";
    public static final  String MOBILE		 				            ="mobile";
	public static final  String PASSWORD		 				    	="password";
    public static final  String RE_PASSWORD		 				    	="repassword";
    public static final  String FULL_NAME		 				    	="fullname";
	public static final  String FROM						    		="from";
	public static final  String MESSAGE						    		="message";
    public static final  String AMOUNT						    		="amount";
	public static final  boolean DEBUG						    		=false;

	
	// Registration Service

	
	// User Cluster Detail 
	// Key 
	public static final  String SUBJECT				   				     ="subject";
	public static final  String VIDEOS				   				     ="videos";
	public static final  String CATEGORY				   				 ="category";
	//ID
	public static final  String SUBJECT_ID				   				 ="subject_id";
	public static final  String NAME				   				     ="name";
	public static final  String CATEGORY_ID				   				 ="category_id";
	public static final  String VIDEOS_ID				   				 ="video_id";
	public static final  String PRODUCT_ID				   				 ="product_id";
	public static final  String URL				   				         ="url";
	
	public static final  String PURCHASED				   			     ="purchased";
	public static final  String RESULT				   					 ="result";
	public static final  String VIDEOS_COUNT		   					 ="videosCount";
	public static final  String STATUS		   					 		="status";
	public static final  String CLIENT_ID		   					 	="client_id";
	public static final  String DONWLOAD				   				 ="download";
    public static final  String PAID_STATUS				   				 ="paid_status";

	
	
	
	
	
	
	// 
	public static final  int GET_VIDEOS				   			    		=10;
	public static final  int DOWNLOAD_VIDEOS		   			    		=11;
	public static final  int LOGIN				   			    			=13;
	public static final  int STATUS_UPDATE				   			 		=15;
	public static final  int UPDATE_ON_SERVER			   			 		=16;
	public static final  int REGISTRATION				   			    	=31;
    public static final  int LOGIN_AFTER_REGISTRATION				   		=32;
	public static final  int FORGET_PASSWORD						   		=33;
	
	
	
	
	 // Time Out 
		public static final  int TIMEOUT_MILLISEC       					= 16000;
	// API URL http://magnusedutech.org/admin/paidclient.php
		public static final  String MAIN_URL	 						   		= "http://magnusedutech.org/admin/";
		public static final  String GET_VIDEO_DATA 						   		= MAIN_URL+"videotree.php";
		public static final  String UPDATE_STATUS_API					   		= MAIN_URL+"mobile_paid_status.php";
		public static final  String LOGIN_API 						   			= MAIN_URL+"mobile_login.php";
        public static final  String REGISTRATION_API                            = MAIN_URL+"mobile_register.php";
        public static final  String FORGET_PASSWORD_API                         = MAIN_URL+"mobile_forgot_password.php";
        public static final  String UPDATE_ON_SERVER_API			   			 =MAIN_URL+"mobile_transaction_entry.php";
        public static final  String DOWNLOAD_VIDEO_URL 						   	= MAIN_URL+"videos5630encry/";
		public static final  String DOWNLOAD_VIDEO_EXTENSION 					= ".mp4";
		
		
		//Database Tables 
		public static String TABLE_COURSES  =  "table_course_info";
		public static String TABLE_CHAPTERS =  "table_chapter_info";
		public static String TABLE_VIDEOS   =  "tbale_video_info";
		
		
		
		 public static String STRING_TABLE_COURSE =
		            "CREATE TABLE " + TABLE_COURSES + "("
		                    + SUBJECT_ID + " TEXT,"
		                    + PRODUCT_ID + " TEXT,"
		                    + NAME + " TEXT,"
		                    + PURCHASED + " INTEGER )";
				
		 public static String STRING_TABLE_CHAPTER =
		            "CREATE TABLE " + TABLE_CHAPTERS + "("
		                    + CATEGORY_ID + " TEXT,"
		                    + SUBJECT_ID + " TEXT,"
		                    + NAME + " TEXT,"
		                    + PURCHASED + " INTEGER )";
		 
		 public static String STRING_TABLE_VIDEO =
		            "CREATE TABLE " + TABLE_VIDEOS + "("
		            		+ VIDEOS_ID + " TEXT,"
		                    + CATEGORY_ID + " TEXT,"
		                    + SUBJECT_ID + " TEXT,"
		                    + NAME + " TEXT,"
		                    + PRODUCT_ID + " TEXT,"
		                    + URL + " TEXT,"
		                    + DONWLOAD + " INTEGER,"
		                    + PURCHASED + " INTEGER )";
		
		 
		
		// Shared Preference 
			public static  SharedPreferences appSharedPreference;
			public static void save_Preferences(String key,String value,Context app_context)
			{
				appSharedPreference = app_context.getSharedPreferences(GlobalConstants.PREFERENCE_NAME, 0);
				SharedPreferences.Editor editor = appSharedPreference.edit();
				editor.putString(key, value);
				editor.commit();
			}
			
			public static void save_Preferences_boolean(String key,boolean value,Context app_context)
			{
				appSharedPreference = app_context.getSharedPreferences(GlobalConstants.PREFERENCE_NAME, 0);
				SharedPreferences.Editor editor = appSharedPreference.edit();
				editor.putBoolean(key, value);
				editor.commit();
			}
			public static String get_Preferences(String key,Context app_context)
			{
				appSharedPreference = app_context.getSharedPreferences(GlobalConstants.PREFERENCE_NAME, 0);
				return  appSharedPreference.getString(key,"null");
			}
			public static boolean get_Preferences_boolean(String key,Context app_context)
			{
				appSharedPreference = app_context.getSharedPreferences(GlobalConstants.PREFERENCE_NAME, 0);
				return  appSharedPreference.getBoolean(key,false);
			}
			
			public static boolean chkNetAvailableOrNot(Context ctx) 
			{
				ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

				if (connectivity != null) 
				{
					NetworkInfo[] info = connectivity.getAllNetworkInfo();

					if (info != null) {
						for (int i = 0; i < info.length; i++) {
							
							if (info[i].getState() == NetworkInfo.State.CONNECTED)
							{
								return true;
							}
						}
					}
				}
				
				
				return false;
			}
			public static Boolean CheckIfInterNetWorkingOrNot(Context ctx) {
			    try { 
			        Process p1 = Runtime.getRuntime().exec("ping -c 1    www.google.com");
			        int returnVal = p1.waitFor();
			        boolean reachable = (returnVal==0);
			        if(reachable)
			        {
			            System.out.println("Internet access");
			            return reachable;
			        } 
			        else
			        { 
			            System.out.println("No Internet access");
			        } 

			    } catch (Exception e) {

			        e.printStackTrace();
			    } 
			    return false; 
			} 
			
			// email validation : 
			
			public static boolean stringEmptyOrNot(final String val) 
			{
				return val!=null && ! val.equalsIgnoreCase("");
		    }
			
				    public static boolean emailValidator(final String mailAddress) {

				            Pattern pattern;
				            Matcher matcher;

				            final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				            pattern = Pattern.compile(EMAIL_PATTERN);
				            matcher = pattern.matcher(mailAddress);
				            return matcher.matches();

				        }
			
			
					
			// Get String as Per String Id 
			public static String getStringFromId(int id,Context context)
			{
				return context.getResources().getString(id);		
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
			 	public static void setVideoFolderLocation(String value)
				{
			 		VIDEO_FOLDER =value;
				}
				
				public static String getVideoFolderLocation()
				{
					return VIDEO_FOLDER+"/";
				}
				
				public static void setOtherFolderLocation(String value)
				{
						OTHER_FOLDER = value;
				}
				
				public static String getOtherFolderLocation()
				{
					return OTHER_FOLDER+"/";
				}
				
				/***
			     *  Informative Alert Box
			     *
			     * @description
			     *     giving information about particular task
			     * @parameters
			     *     title        - title for alert box
			     *     message      - message to be show in alert box
			     *     mContext     - context of Activity
			     *
			     */
			    public static void showInformativeDialog(String title, String message , Context mContext)
			    {
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
			
}
