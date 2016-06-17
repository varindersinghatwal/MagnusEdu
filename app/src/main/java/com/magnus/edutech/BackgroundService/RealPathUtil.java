package com.magnus.edutech.BackgroundService;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.magnus.edutech.webservices.GlobalConstants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// Get Path From android System 
	  public class RealPathUtil 
	  	{
		  	private Context context;
		  	public RealPathUtil(Context context2) 
		  	{
		  		this.context=context;
		  	}
	
		 
		  	public String generateUniqueFileName(String className,String FilePath)
			{
		  		String Extension =getFileExt(FilePath);
		  		String filename=null;
		  		if(Extension!=null)
		  		{
		  			
			        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-hhmmss");
			        Random random =new Random();
			        filename = className+"-"+String.format("%s.%s", sdf.format( new Date() ),random.nextInt(9))+"."+Extension;
		  		}
		  				        
		        return filename;
			}
		  	
		  	public  String getFileExt(String fileName)
		  	{      
		  	     return fileName.substring((fileName.lastIndexOf(".") + 1), fileName.length());
		  	}
		  	/**
		  	 * Get a file path from a Uri. This will get the the path for Storage Access
		  	 * Framework Documents, as well as the _data field for the MediaStore and
		  	 * other file-based ContentProviders.
		  	 *
		  	 * @param context The context.
		  	 * @param uri The Uri to query.
		  	 * @author paulburke
		  	 */
		  	@TargetApi(Build.VERSION_CODES.KITKAT)
			public  String getPath(final Context context, final Uri uri) 
		  		{
		  			final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		  			
		  			// DocumentProvider
		  			if (isKitKat && DocumentsContract.isDocumentUri(context, uri))
		  				{
		  					// ExternalStorageProvider
		  					if (isExternalStorageDocument(uri))
		  							{
		  								final String docId = DocumentsContract.getDocumentId(uri);
		  								final String[] split = docId.split(":");
		  								final String type = split[0];

		  								if ("primary".equalsIgnoreCase(type))
		  									{
		  										return Environment.getExternalStorageDirectory() + "/" + split[1];
		  									}

		  								// TODO handle non-primary volumes
		  							}
				  	        // DownloadsProvider
				  	        else if (isDownloadsDocument(uri)) 
				  	        		{
				  	        			final String id = DocumentsContract.getDocumentId(uri);
				  	        			final Uri contentUri = ContentUris.withAppendedId(
				  	                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
				  	        			return getDataColumn(context, contentUri, null, null);
				  	        		}
		  					// MediaProvider
				  	        else if (isMediaDocument(uri)) 
				  	        		{
				  	        			final String docId = DocumentsContract.getDocumentId(uri);
				  	        			final String[] split = docId.split(":");
				  	        			final String type = split[0];
				  	        			Uri contentUri = null;
				  	        			if ("image".equals(type))
				  	        				{
				  	        					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				  	        				} 
				  	        			else if ("video".equals(type))
				  	        				{
				  	        					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				  	        				} 
				  	        			else if ("audio".equals(type)) 
				  	        				{
				  	        					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				  	        				}

				  	        			final String selection = "_id=?";
				  	        			final String[] selectionArgs = new String[] 
				  	        						{
				  	        							split[1]
				  	        						};

				  	        			return getDataColumn(context, contentUri, selection, selectionArgs);
				  	        		}
		  				}
		  			// MediaStore (and general)
		  			else if ("content".equalsIgnoreCase(uri.getScheme())) 
		  				{
		  					return getDataColumn(context, uri, null, null);
		  				}
		  			// File
		  			else if ("file".equalsIgnoreCase(uri.getScheme())) 
		  				{
		  					return uri.getPath();
		  				}

		  	    return null;
		  	}

		  	/**
		  	 * Get the value of the data column for this Uri. This is useful for
		  	 * MediaStore Uris, and other file-based ContentProviders.
		  	 *
		  	 * @param context The context.
		  	 * @param uri The Uri to query.
		  	 * @param selection (Optional) Filter used in the query.
		  	 * @param selectionArgs (Optional) Selection arguments used in the query.
		  	 * @return The value of the _data column, which is typically a file path.
		  	 */
		  	public  String getDataColumn(Context context, Uri uri, String selection,
		  	        String[] selectionArgs) 
		  		{
		  			Cursor cursor = null;
		  			final String column = "_data";
		  			final String[] projection = 
		  					{
		  						column
		  					};

		  	    try {
		  	    		cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
		  	                null);
		  	    		if (cursor != null && cursor.moveToFirst()) 
		  	        	{
		  	            	final int column_index = cursor.getColumnIndexOrThrow(column);
		  	            	return cursor.getString(column_index);
		  	        	}
		  	    	} 
		  	   finally 
		  	   		{
		  		   		if (cursor != null)
		  	            cursor.close();
		  	   		}
		  	    return null;
		  	}
            
		  	public boolean creatingLocalDirctories()
		  	{
		  		boolean done = false;
		  		if(isExternalStorageAvailable() && isExternalStorageWritable() )
		  		{
		  			File myDirectory = new File(Environment.getExternalStorageDirectory(), GlobalConstants.APPLICATION_FOLDER_NAME);

		  			if(!myDirectory.exists())
		  			{                                 
		  			    myDirectory.mkdirs();
		  			  	String absolutePathDic = myDirectory.getAbsolutePath()+"/"+GlobalConstants.APPLICATION_SUBFOLDER_VIDEOS;
						if(GlobalConstants.DEBUG)
						System.out.println("Cehck Path : "+absolutePathDic);
		  			 
		  			     
		  			     File 	myDirectorySubFolder1 = new File(absolutePathDic);
			  			if(!myDirectorySubFolder1.exists())
				  			{
			  				  done =true;
			  				  myDirectorySubFolder1.mkdirs();
			  				  GlobalConstants.setVideoFolderLocation(absolutePathDic);
				  			}
			  			else
			  				{
			  				  done =false;
			  				}
			  			
			  			
			  			
			  			absolutePathDic = myDirectory.getAbsolutePath()+"/"+GlobalConstants.APPLICATION_SUBFOLDER_OTHERS;
                        if(GlobalConstants.DEBUG)
			  			 System.out.println("Cehck Path subfolder: "+absolutePathDic);
			  			 File 	myDirectorySubFolder2 = new File(absolutePathDic);  
		  			    if(!myDirectorySubFolder2.exists())
			  			{
		  			    	myDirectorySubFolder2.mkdirs();
		  			    	done =true;
		  			    	GlobalConstants.setOtherFolderLocation(absolutePathDic);
			  			}
		  			    else
		  			    {
		  				  done =false;
		  			    }
		  			}
		  			else
		  			{  
		  				String absolutePathDic = myDirectory.getAbsolutePath()+"/"+GlobalConstants.APPLICATION_SUBFOLDER_VIDEOS; 
		  							GlobalConstants.setVideoFolderLocation(absolutePathDic);
		  						
		  					   absolutePathDic = myDirectory.getAbsolutePath()+"/"+GlobalConstants.APPLICATION_SUBFOLDER_OTHERS; 
		  					  
		  					 GlobalConstants.setOtherFolderLocation(absolutePathDic);
		  			
		  				done =false;
		  			}
		  		}	
		  	
		  		return done ;
		  	}
		  	
		  	
		  	public boolean  isFileExist(String path)
		  	{
		  		boolean  isHasfile =false;
		  		File myDirectory = new File(path);

	  			if(myDirectory.exists())
	  			{        
	  				return true;
	  			}
	  			return isHasfile;
		  	}
		  	
		  	
		  	
		  	
		  	
		  	
		  	
		  	public boolean copyAndSaveFileToExternalCard(String inputPath,String NewfileName,String from )
		  	{
		  		boolean save =false;
		  		String folderName = "";
		  		if(from.equalsIgnoreCase(GlobalConstants.VIDEOS))
		  		{
		  			folderName = GlobalConstants.getVideoFolderLocation();
		  		}
		  		else
		  		{
		  			folderName = GlobalConstants.getOtherFolderLocation();
		  		}
		  		if(isExternalStorageAvailable() && isExternalStorageWritable() )
		  		{
		  			File myDirectory = new File(folderName);

		  			if(!myDirectory.exists())
		  			{                                 
		  			  myDirectory.mkdirs();
		  			}
		  			
		  			String absolutePathDic = myDirectory.getAbsolutePath()+"/";

                    if(GlobalConstants.DEBUG)
                    System.out.println("Chekc Absolute PAth : "+absolutePathDic);
		  			if(inputPath !=null && absolutePathDic !=null)
		  			{
		  				save = copyFile(inputPath,  absolutePathDic,NewfileName) ;
			  			
		  			}
		  			else
		  			{
		  				save =false;
		  			}
		  			
		  		}
		  		else
		  		{
		  			save =false;
		  		}
		  		
		  		
		  		return save;
		  		
		  	}
		  	public boolean saveBitampAsFileToExternalCard(Bitmap bitmap,String NewfileName,String from) throws IOException
		  	{
		  		boolean save =false;
		  		String folderName = "";
		  		
		  		if(from.equalsIgnoreCase(GlobalConstants.VIDEOS))
		  		{
		  			folderName = GlobalConstants.getVideoFolderLocation();
		  		}
		  		else
		  		{
		  			folderName = GlobalConstants.getOtherFolderLocation();
		  		}
		  		
		  		if(isExternalStorageAvailable() && isExternalStorageWritable() )
		  		{
		  			File myDirectory = new File(folderName);

		  			if(!myDirectory.exists())
		  			{                                 
		  			  myDirectory.mkdirs();
		  			}
		  			
		  			String absolutePathDic = myDirectory.getAbsolutePath()+"/";

                    if(GlobalConstants.DEBUG)
		  			System.out.println("Chekc Absolute PAth : "+absolutePathDic);
		  			if(bitmap !=null && absolutePathDic !=null)
		  			{
		  				 ByteArrayOutputStream stream = new ByteArrayOutputStream();
		  				 bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		  		         byte[] byteArray = stream.toByteArray(); // convert camera photo to byte array

		  		         // save it in your external storage.
		  		        FileOutputStream fo = new FileOutputStream(new File(absolutePathDic + NewfileName));
		  		        fo.write(byteArray);
		  		        fo.flush();
		  		        fo.close();
		  		       save =true;
		  			}
		  			else
		  			{
		  				save =false;
		  			}
		  			
		  		}
		  		else
		  		{
		  			save =false;
		  		}
		  		
		  		
		  		return save;
		  		
		  	}
		  	
		  	
			public boolean saveOtherAsFileToExternalCard(byte[] arraybyte,String NewfileName,String from) throws IOException
		  	{
		  		boolean save =false;
		  		String folderName = "";
		  		if(from.equalsIgnoreCase(GlobalConstants.VIDEOS))
		  		{
		  			folderName = GlobalConstants.getVideoFolderLocation();
		  		}
		  		else
		  		{
		  			folderName = GlobalConstants.getOtherFolderLocation();
		  		}
		  		
		  		if(isExternalStorageAvailable() && isExternalStorageWritable() )
		  		{
		  			File myDirectory = new File(folderName);

		  			if(!myDirectory.exists())
		  			{                                 
		  			  myDirectory.mkdirs();
		  			}
		  			
		  			String absolutePathDic = myDirectory.getAbsolutePath()+"/"+NewfileName; 

		  			if(arraybyte !=null && absolutePathDic !=null)
		  			{
		  				FileOutputStream out = new FileOutputStream(absolutePathDic);
		  				out.write(arraybyte);
		  				out.close();
		  		       save =true;
		  			}
		  			else
		  			{
		  				save =false;
		  			}
		  			
		  		}
		  		else
		  		{
		  			save =false;
		  		}
		  		
		  		
		  		return save;
		  		
		  	}
		  	
		  	
		  	private boolean copyFile(String inputPath, String outputPath ,String FileName ) 
		  		{
		  			boolean save =false;
		  			InputStream in = null;
		  			OutputStream out = null;
		  			try 
		  				{
		  				 //create output directory if it doesn't exist
		  		        File dir = new File (outputPath); 
		  		        if (!dir.exists())
		  		        {
		  		            dir.mkdirs();
		  		        }
		  					in = new FileInputStream(inputPath);        
		  					out = new FileOutputStream(outputPath+FileName);
		  					byte[] buffer = new byte[1024];
		  					int read;
		  					while ((read = in.read(buffer)) != -1) 
		  						{
		  							out.write(buffer, 0, read);
		  						}
		  					in.close();
		  					in = null;
		  					// write the output file (You have now copied the file)
		  					out.flush();
		  					out.close();
		  					out = null;        
		  					save =true;
		  				} 
		  			catch (FileNotFoundException fnfe1) 
		  				{
		  				 	save =false;
		  					Log.e("tag1", fnfe1.getMessage());
		  				}
		  	        catch (Exception e) 
		  				{
		  	        		save =false;
		  	        		Log.e("tag2", e.getMessage());
		  				}
		  		return save;

		  	}
		  	/**
		  	 * @param uri The Uri to check.
		  	 * @return Whether the Uri authority is ExternalStorageProvider.
		  	 */
		  	public  boolean isExternalStorageDocument(Uri uri)
		  	{
		  	    return "com.android.externalstorage.documents".equals(uri.getAuthority());
		  	}

		  	/**
		  	 * @param uri The Uri to check.
		  	 * @return Whether the Uri authority is DownloadsProvider.
		  	 */
		  	public  boolean isDownloadsDocument(Uri uri) {
		  	    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
		  	}

		  	/**
		  	 * @param uri The Uri to check.
		  	 * @return Whether the Uri authority is MediaProvider.
		  	 */
		  	public  boolean isMediaDocument(Uri uri) {
		  	    return "com.android.providers.media.documents".equals(uri.getAuthority());
		  	}
		  	
		  	
		  	  	
		  	public File getAlbumStorageDir(Context context, String albumName) {
		  	    // Get the directory for the app's private pictures directory. 
		  	    File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName);
		  	    if (!file.mkdirs()) {
		  	        Log.e("SHP : ", "Directory not created");
		  	    }
		  	    return file;
		  	}
		  	
		  	public  boolean isExternalStorageAvailable() {
		  	    
		  	    // Retrieving the external storage state
		  	    String state = Environment.getExternalStorageState();
		  	    
		  	    // Check if available
		  	    if (Environment.MEDIA_MOUNTED.equals(state)
		  	        || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		  	      return true;
		  	    }
		  	    
		  	    return false;
		  	  }
		  	  
		  	  /**
		  	   * @return True if the external storage is writable.
		  	   * False otherwise.
		  	   */
		  	/* Checks if external storage is available for read and write */
		  	public boolean isExternalStorageWritable() {
		  	    String state = Environment.getExternalStorageState();
		  	    if (Environment.MEDIA_MOUNTED.equals(state)) {
		  	        return true;
		  	    }
		  	    return false;
		  	}	
		  	private int getversion()
			{
				int currentApiVersion;
			    try
			    {
			         currentApiVersion = Build.VERSION.SDK_INT;
			    }
			    catch(NumberFormatException e)
			    {
			        //API 3 will crash if SDK_INT is called
			        currentApiVersion = 3;
			    }
				return currentApiVersion;
			}
		  	
		  	public String getUniqueIdLong()
		  	{
		  		Random r =new Random();
		  		long systemTime = System.currentTimeMillis();
			  	long unixtime=(long) (systemTime+r.nextDouble()*60*60*24*365);
			  	
			  	return String.valueOf(unixtime);
		  	}

			public void hideActionBarAndStatusBar(Activity activity)
			{
				/*if (Build.VERSION.SDK_INT < 16) {
					activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				} else {
					View decorView = activity.getWindow().getDecorView();
					int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
					decorView.setSystemUiVisibility(uiOptions);
					ActionBar actionBar =activity.getActionBar();
					actionBar.hide();
				}*/
			}
		  	
	  	}