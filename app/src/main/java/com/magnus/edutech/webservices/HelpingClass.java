/*
package com.magnus.edutech.webservices;


import android.content.Context;


import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

import java.util.List;
public class HelpingClass {

	Context context;
	public static DatabaseHandler db;

	public HelpingClass(Context ctx) {
		this.context = ctx;
		db = new DatabaseHandler(context);
	}

	//Get Table row count 
	public int getRowCountInTable(String tableName)
	{
		return db.getRowCountInTable(tableName);
	}
	// Get All Subjects
	public List<Course> fetchCoursesFromDb(Course course) {
		return db.FETCH_COURSE_LIST(course);
	}

	// Get All Chapters
	public List<Chapters> fetchChaptersFromDb(Chapters chapters) {
		return db.FETCH_CHAPTER_LIST(chapters);
	}

	// Get All Videos
	public List<Videos> fetchVideosFromDb(Videos videos) {
				return db.FETCH_VIDEO_LIST(videos);
	}

	// Get Subject info for particular subject id .
	public Course getSubjectInfoPerSubjectIdFromDB(Course course) {
		return db.TABLE_COURSE_INFO(1, course);
	}
	// updating values into tables
	public void updateIntoTables(Object obj) {
		int queryType = 2;

		if (obj instanceof Course) {

		} else if (obj instanceof Chapters) {

		} else if (obj instanceof Videos) {
			*/
/* db.TABLE_CUSTOMER_INFO(queryType, (Customer) obj); *//*


		}
	}
	public boolean updateVideoStatusForClient(){

        List<Course> courseList =fetchCoursesFromDb(null);
        if(courseList ==null)
        {
            return false;
        }
        for(int i=0;i<courseList.size();i++)
        {
            Videos videos = new Videos();
            videos.setSubject_id(courseList.get(i).getSubject_id());
            videos.setPurchased(true);
            updateIntoTables(videos);
        }

        GlobalConstants.save_Preferences_boolean(GlobalConstants.PURCHASED,true,context);
		return true;
	}
	*/
/* case GlobalConstants.LOGIN_AFTER_REGISTRATION:
                            try {

                                        param.add(new BasicNameValuePair(GlobalConstants.USER_NAME, jsonData.getString(GlobalConstants.USER_NAME)));
                                        param.add(new BasicNameValuePair(GlobalConstants.PASSWORD, jsonData.getString(GlobalConstants.PASSWORD)));
                                        jsonObject = WebServiceHandler.requestACallToServerPOST(context, GlobalConstants.LOGIN_API, param);
                                    } catch (JSONException e) {
                                        Log.e("Create Para : ", e.getMessage());
                                        e.printStackTrace();
                                    }

                                    break;
                                case GlobalConstants.STATUS_UPDATE:
                                    try {

                                        param.add(new BasicNameValuePair(GlobalConstants.CLIENT_ID, jsonData.getString(GlobalConstants.CLIENT_ID)));
                                        jsonObject = WebServiceHandler.requestACallToServerPOST(context, GlobalConstants.UPDATE_STATUS_API, param);
                                    } catch (JSONException e) {
                                        Log.e("STATUS_UPDATE : ", e.getMessage());
                                        e.printStackTrace();
                                    }
                                    break;*//*


}
*/

 /* if (status != 500) {
            if (status == 400) {
                utilities.showInformativeDialog(context.getString(R.string.err_server_header), context.getString(R.string.err_server_body), context);
                return;
            }
            ActivitySplashScreen.updateUI(status);
            return;
        }
        handleServerResponse(jsonObject);
}

    /*private void handleServerResponse(JSONObject jsonObject) {

        if (jsonObject != null) {
            try {
                if (jsonObject.has(GlobalConstants.STATUS) && jsonObject.getInt(GlobalConstants.STATUS) == 1) { //status 1 handling

                    switch (fromWhere) {
                        case GlobalConstants.LOGIN:
                            String ClientId = jsonObject.getString(GlobalConstants.CLIENT_ID);
                            GlobalConstants.save_Preferences(GlobalConstants.CLIENT_ID, ClientId, context);
                            GlobalConstants.save_Preferences_boolean(GlobalConstants.LOGGED_IN, true, context);
                            ActivityLoginScreen.updateUi();
                            break;
                        case GlobalConstants.REGISTRATION:
                            loadDataInBackGround = new LoadDataInBackGround();
                            User user = new User(jsonData.getString(GlobalConstants.EMAIL), jsonData.getString(GlobalConstants.PASSWORD));
                            loadDataInBackGround.CheckLoginAfterRegistrationFromServer(context, user);
                            break;
                        case GlobalConstants.LOGIN_AFTER_REGISTRATION:
                            String ClientIdAfterReg = jsonObject.getString(GlobalConstants.CLIENT_ID);
                            GlobalConstants.save_Preferences(GlobalConstants.CLIENT_ID, ClientIdAfterReg, context);
                            GlobalConstants.save_Preferences_boolean(GlobalConstants.LOGGED_IN, true, context);
                            ActivityRegistrationScreen.updateUi();
                            break;
                        case GlobalConstants.FORGET_PASSWORD:
                            ActivityLoginScreen.updateUiForForgetPassword();
                            break;
                        case GlobalConstants.STATUS_UPDATE:

                            if (jsonObject.getInt(GlobalConstants.PAID_STATUS) == 1) {
                                helpingClass.updateVideoStatusForClient();
                            }

                            break;
                        case GlobalConstants.UPDATE_ON_SERVER:
                            helpingClass.updateVideoStatusForClient();
                            break;
                    }

                } else { // status 0 handling
                    String message = context.getString(R.string.err_server_body);
                    switch (fromWhere) {

                        case GlobalConstants.LOGIN:
                            message = jsonObject.getString(GlobalConstants.MESSAGE);
                            utilities.showInformativeDialog(context.getString(R.string.err_login_header), message, context);
                            break;
                        case GlobalConstants.REGISTRATION:
                            message = jsonObject.getString(GlobalConstants.MESSAGE);
                            utilities.showInformativeDialog(context.getString(R.string.err_registration_header), message, context);
                            break;
                        case GlobalConstants.FORGET_PASSWORD:
                            message = context.getString(R.string.err_forget_password);
                            utilities.showInformativeDialog(context.getString(R.string.err_registration_header), message, context);
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            utilities.showInformativeDialog(context.getString(R.string.err_server_header), context.getString(R.string.err_server_body), context);
        }


    }*/