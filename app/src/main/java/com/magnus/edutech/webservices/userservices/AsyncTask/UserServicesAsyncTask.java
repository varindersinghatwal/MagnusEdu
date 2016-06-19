package com.magnus.edutech.webservices.userservices.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.magnus.edutech.view.component.ProgressBarClass;
import com.magnus.edutech.view.Utility.Utilities;
import com.magnus.edutech.webservices.AsyncCallbackInterface;
import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.webservices.WebServiceHandler;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import java.util.List;


public class UserServicesAsyncTask extends AsyncTask<Void, Void, String> {

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
    AsyncCallbackInterface asyncCallbackInterface;


    public UserServicesAsyncTask(Context ctx, List<NameValuePair> param, String url, String message, String requestType, AsyncCallbackInterface asyncCallbackInterface) throws JSONException {
        this.context = ctx;
        helpingClass = new HelpingClass(context);
        this.param = param;
        this.url = url;
        this.requestType = requestType;
        this.asyncCallbackInterface = asyncCallbackInterface;
        utilities = new Utilities();

        progressBarClass = new ProgressBarClass(ctx);
        progressBarClass.hideShowProgressBar(true, message);
    }


    @Override
    protected String doInBackground(Void... params) {
        try {
            Response = null;
            if (requestType.equalsIgnoreCase("POST")) {
                Response = WebServiceHandler.requestACallToServerPOST(context, url, param);
            } else if (requestType.equalsIgnoreCase("GET")) {
                Response = WebServiceHandler.requestCallToServerGET(context, url);
            }
        } catch (Exception e) {
            if (GlobalConstants.DEBUG)
                Log.e("UserServiceAsync : ", e.getMessage());
            e.printStackTrace();
        }

        return Response;
    }

    @Override
    protected void onPostExecute(String Response) {
        super.onPostExecute(Response);
        progressBarClass.hideShowProgressBar(false, "");
        asyncCallbackInterface.onPostExecute(Response);
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


}
