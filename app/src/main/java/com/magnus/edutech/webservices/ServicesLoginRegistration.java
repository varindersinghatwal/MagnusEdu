package com.magnus.edutech.webservices;

import android.content.Context;
import android.os.AsyncTask;

import com.magnus.edutech.ActivityLoginScreen;
import com.magnus.edutech.ActivityRegistrationScreen;
import com.magnus.edutech.ActivitySplashScreen;
import com.magnus.edutech.BackgroundService.LoadDataInBackGround;
import com.magnus.edutech.R;
import com.magnus.edutech.model.User;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ServicesLoginRegistration extends AsyncTask<Void, Void, String> {

    //Class
    private ProgressBarClass progressBarClass;
    private Utilities utilities;
    private LoadDataInBackGround loadDataInBackGround;
    private HelpingClass helpingClass;
    //Variable
    private Context context;
    private int status = 500;
    private List<NameValuePair> param;
    private String url;
    private String requestType;
    private String Response;


    public ServicesLoginRegistration(Context ctx, List<NameValuePair> param, String url, String message, String requestType) throws JSONException {
        this.context = ctx;
        helpingClass = new HelpingClass(context);
        this.param = param;
        this.url = url;
        this.requestType = requestType;
        utilities = new Utilities();

        progressBarClass = new ProgressBarClass(ctx);
        progressBarClass.hideShowProgressBar(true, message);
    }


    @Override
    protected JSONObject doInBackground(Void... params) {
        try {
            Response = null;
            if (requestType.equalsIgnoreCase("POST")) {
                Response = WebServiceHandler.requestACallToServerPOST(context, url, param);
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
                                    break;*/

            } else if (requestType.equalsIgnoreCase("GET")
            {
                Response = WebServiceHandler.requestCallToServerGET(context, url);
            }
        } catch (Exception e) {
            System.out.println(" ASYNC Sys : " + e);
        }

        return Response;
    }

    @Override
    protected void onPostExecute(String  Response) {
        super.onPostExecute(Response);
        progressBarClass.hideShowProgressBar(false, "");


       /* if (status != 500) {
            if (status == 400) {
                utilities.showInformativeDialog(context.getString(R.string.err_server_header), context.getString(R.string.err_server_body), context);
                return;
            }
            ActivitySplashScreen.updateUI(status);
            return;
        }
        handleServerResponse(jsonObject);*/
    }

    private void handleServerResponse(JSONObject jsonObject) {

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


    }


}
