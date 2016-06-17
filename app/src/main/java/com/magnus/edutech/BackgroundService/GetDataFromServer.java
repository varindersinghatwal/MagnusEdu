package com.magnus.edutech.BackgroundService;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.magnus.edutech.model.User;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.webservices.GlobalConstants;
import com.magnus.edutech.webservices.ServiceDownloadVideo;
import com.magnus.edutech.webservices.ServicesLoginRegistration;

import org.json.JSONException;
import org.json.JSONObject;


public class GetDataFromServer {

    //Classes
    private ServicesLoginRegistration servicesLoginRegistration;
    private ServiceDownloadVideo serviceDownloadVideos;
    private Context context;

    public GetDataFromServer(Context context) {
        this.context = context;
       }

    public void CheckLoginAfterRegistrationFromServer(Context context, User user) throws NumberFormatException, JSONException {
        if (user == null) {
            return;
        }
        JSONObject JSON_FOR_GET_VIDEO_DETAIL = new JSONObject();
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.FROM, GlobalConstants.LOGIN_AFTER_REGISTRATION);
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.MESSAGE, "Login ....");
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.USER_NAME, user.getEmail());
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.PASSWORD, user.getPassword());
        JSON_FOR_GET_VIDEO_DETAIL.put("RequestType", "POST");

        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_GET_VIDEO_DETAIL);

        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FOR_GET_VIDEO_DETAIL, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

    /***
     * @throws NumberFormatException
     * @throws JSONException
     */

    public void CheckLoginFromServer(Context context, User user) throws NumberFormatException, JSONException {
        if (user == null) {
            return;
        }
        JSONObject JSON_FOR_GET_VIDEO_DETAIL = new JSONObject();
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.FROM, GlobalConstants.LOGIN);
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.MESSAGE, "Login ....");
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.USER_NAME, user.getEmail());
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.PASSWORD, user.getPassword());
        JSON_FOR_GET_VIDEO_DETAIL.put("RequestType", "POST");

        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_GET_VIDEO_DETAIL);

        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FOR_GET_VIDEO_DETAIL, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

    /***
     * @throws NumberFormatException
     * @throws JSONException
     */

    public void forgetPassword(Context context, User user) throws NumberFormatException, JSONException {
        if (user == null) {
            return;
        }
        JSONObject JSON_FORGET_PASSWORD = new JSONObject();
        JSON_FORGET_PASSWORD.put(GlobalConstants.FROM, GlobalConstants.FORGET_PASSWORD);
        JSON_FORGET_PASSWORD.put(GlobalConstants.MESSAGE, "Sending your password on registered email. ");
        JSON_FORGET_PASSWORD.put(GlobalConstants.EMAIL, user.getEmail());
        JSON_FORGET_PASSWORD.put("RequestType", "POST");
        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FORGET_PASSWORD);

        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FORGET_PASSWORD, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

    /***
     * @throws NumberFormatException
     * @throws JSONException
     */

    public void RegisterUserToFromServer(Context context, User user) throws NumberFormatException, JSONException {

        if (user == null) {
            return;
        }
        JSONObject JSON_FOR_REGISTRATION = new JSONObject();
        JSON_FOR_REGISTRATION.put(GlobalConstants.FROM, GlobalConstants.REGISTRATION);
        JSON_FOR_REGISTRATION.put(GlobalConstants.MESSAGE, "Registering ....");
        JSON_FOR_REGISTRATION.put(GlobalConstants.FULL_NAME, user.getFullName());
        JSON_FOR_REGISTRATION.put(GlobalConstants.EMAIL, user.getEmail());
        JSON_FOR_REGISTRATION.put(GlobalConstants.MOBILE, user.getMobile());
        JSON_FOR_REGISTRATION.put(GlobalConstants.PASSWORD, user.getPassword());
        JSON_FOR_REGISTRATION.put(GlobalConstants.RE_PASSWORD, user.getRePassword());

        JSON_FOR_REGISTRATION.put("RequestType", "POST");

        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_REGISTRATION);

        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FOR_REGISTRATION, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

    /***
     * @throws NumberFormatException
     * @throws JSONException
     */

    public void getAllDataRelatedToVideoFromServer(Context context) throws NumberFormatException, JSONException {

        JSONObject JSON_FOR_GET_VIDEO_DETAIL = new JSONObject();
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.FROM, GlobalConstants.GET_VIDEOS);
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.MESSAGE, "");
        JSON_FOR_GET_VIDEO_DETAIL.put("RequestType", "GET");

        if (GlobalConstants.DEBUG) {
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_GET_VIDEO_DETAIL);
        }


        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FOR_GET_VIDEO_DETAIL, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

    /***
     * download Video From Server
     *
     * @throws NumberFormatException
     * @throws JSONException
     */


    public void downloadVideoFromServer(Context context, Videos videos) throws NumberFormatException, JSONException {
        JSONObject JSON_FOR_GET_VIDEO_DETAIL = new JSONObject();

        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.FROM, GlobalConstants.DOWNLOAD_VIDEOS);
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.SUBJECT_ID, videos.getSubject_id());
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.CATEGORY_ID, videos.getCategory_id());
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.VIDEOS_ID, videos.getVideo_id());
        JSON_FOR_GET_VIDEO_DETAIL.put(GlobalConstants.MESSAGE, videos.getMessage());
        JSON_FOR_GET_VIDEO_DETAIL.put("RequestType", "GET");

        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_GET_VIDEO_DETAIL);

        serviceDownloadVideos = new ServiceDownloadVideo(context, JSON_FOR_GET_VIDEO_DETAIL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            serviceDownloadVideos.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            serviceDownloadVideos.execute();

    }

    /***
     * arun37kumar@gmail.com
     * arun123
     *
     * @throws NumberFormatException
     * @throws JSONException
     */

    public void updateStatusToServer(Context context, User user) throws NumberFormatException, JSONException {

        JSONObject JSON_FOR_STATUS_UPDATE = new JSONObject();
        JSON_FOR_STATUS_UPDATE.put(GlobalConstants.FROM, GlobalConstants.STATUS_UPDATE);
        JSON_FOR_STATUS_UPDATE.put(GlobalConstants.MESSAGE, "");
        JSON_FOR_STATUS_UPDATE.put(GlobalConstants.CLIENT_ID, user.getClintId());
        JSON_FOR_STATUS_UPDATE.put("RequestType", "POST");

        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_STATUS_UPDATE);

        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FOR_STATUS_UPDATE, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

    /***
     * update Status to Server
     *
     * @throws NumberFormatException
     * @throws JSONException
     */

    public void updateStatusToServerAfterPayment(Context context, String Amount) throws NumberFormatException, JSONException {

        String ClientId = GlobalConstants.get_Preferences(GlobalConstants.CLIENT_ID, context);
        JSONObject JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT = new JSONObject();
        JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT.put(GlobalConstants.FROM, GlobalConstants.UPDATE_ON_SERVER);
        JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT.put(GlobalConstants.MESSAGE, " Updating on server ....");
        JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT.put(GlobalConstants.CLIENT_ID, ClientId);
        JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT.put(GlobalConstants.AMOUNT, Amount);
        JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT.put("RequestType", "POST");

        if (GlobalConstants.DEBUG)
            System.out.println(" PERSON_DATA  :- \n " + JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT);

        servicesLoginRegistration = new ServicesLoginRegistration(context, JSON_FOR_STATUS_UPDATE_AFTER_PAYMENT, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            servicesLoginRegistration.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            servicesLoginRegistration.execute();


    }

}