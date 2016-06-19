package com.magnus.edutech.webservices.lectureservices.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.view.component.ProgressBarClass;
import com.magnus.edutech.webservices.AsyncCallbackInterface;
import com.magnus.edutech.webservices.WebServiceHandler;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import java.util.List;


public class LectureServicesAsyncTask extends AsyncTask<Void, Void, String> {

    //Class
    private ProgressBarClass progressBarClass;
    //Variable
    private Context context;
    private List<NameValuePair> param;
    private String url;
    private String requestType;
    private String Response;
    private AsyncCallbackInterface asyncCallbackInterface;


    public LectureServicesAsyncTask(Context ctx, List<NameValuePair> param, String url, String message, String requestType, AsyncCallbackInterface asyncCallbackInterface) throws JSONException {
        this.context = ctx;
        this.param = param;
        this.url = url;
        this.requestType = requestType;
        this.asyncCallbackInterface = asyncCallbackInterface;
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
    }


}
