package com.magnus.edutech.BackgroundService;

import android.content.Context;

import com.magnus.edutech.App.App;
import com.magnus.edutech.model.User;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.webservices.HelpingClass;

import org.json.JSONException;


public class LoadDataInBackGround {

    //Classes
    private static final String SERVICE_NAME = LoadDataInBackGround.class.getName();
    private GetDataFromServer GetDataFromServer;
    HelpingClass helpingClass;
    private Context context;

    public LoadDataInBackGround() {
        context = App.mContext;
        helpingClass = new HelpingClass(context);
        GetDataFromServer = new GetDataFromServer(context);
    }

    /***
     * Login after registration
     */
    public void CheckLoginAfterRegistrationFromServer(Context context, User user) throws NumberFormatException, JSONException {
        GetDataFromServer.CheckLoginAfterRegistrationFromServer(context, user);
    }

    /***
     * Login
     */
    public void CheckLoginFromServer(Context context, User user) throws NumberFormatException, JSONException {
        GetDataFromServer.CheckLoginFromServer(context, user);

    }

    /***
     * Registration
     */
    public void RegisterUserToFromServer(Context context, User user) throws NumberFormatException, JSONException {
        GetDataFromServer.RegisterUserToFromServer(context, user);

    }

    /***
     * Forget password
     */
    public void forgetPassword(Context context, User user) throws NumberFormatException, JSONException {
        GetDataFromServer.forgetPassword(context, user);

    }

    /***
     *
     */
    public void updateStatusToServer(Context context, User user) throws NumberFormatException, JSONException {
        GetDataFromServer.updateStatusToServer(context, user);

    }

    /***
     * Update client status for payment
     */
    public void updateStatusToServerAfterPayment(Context context, String Amount) throws NumberFormatException, JSONException {
        GetDataFromServer.updateStatusToServerAfterPayment(context, Amount);
    }

    /***
     * Course, Chapter , Videos from server
     */
    public void getAllDataRelatedToVideoFromServer(Context context) throws NumberFormatException, JSONException {
        GetDataFromServer.getAllDataRelatedToVideoFromServer(context);

    }

    /***
     * Download Videos
     */
    public void downloadVideoFromServer(Context context, Videos videos) throws NumberFormatException, JSONException {
        GetDataFromServer.downloadVideoFromServer(context, videos);
    }


}