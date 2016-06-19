package com.magnus.edutech.webservices.userservices.servercall.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.R;
import com.magnus.edutech.model.User;
import com.magnus.edutech.webservices.AsyncCallbackInterface;
import com.magnus.edutech.webservices.JsonParser;
import com.magnus.edutech.webservices.RequestCallbackListener;
import com.magnus.edutech.webservices.RequestParameter;
import com.magnus.edutech.webservices.userservices.asynctask.UserServicesAsyncTask;
import com.magnus.edutech.webservices.userservices.requestparameter.GetUsersRequestParam;
import com.magnus.edutech.webservices.userservices.responseparser.UsersJsonParser;
import com.magnus.edutech.webservices.userservices.servercall.MakeAUsersServerCall;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import java.util.List;

/**
 * Created by joshi on 6/19/2016.
 */
public class MakeAUsersServerCallImpl implements MakeAUsersServerCall {

    private boolean isExecuted = false;
    private RequestCallbackListener requestCallbackListener;
    private RequestParameter requestParameter;
    private GetUsersRequestParam getUsersRequestParam;
    private List<NameValuePair> param;
    private UserServicesAsyncTask userServicesAsyncTask;
    //JSON PARSE
    private JsonParser jsonParser;
    private UsersJsonParser usersJsonParser;

    public MakeAUsersServerCallImpl(RequestCallbackListener ev) {
        this.requestCallbackListener = ev;
        requestParameter = new RequestParameter();
    }

    @Override
    public void serverCallUserLogin(Context context, User user) {
        getUsersRequestParam = requestParameter.getUsersRequestParamObject();
        param = getUsersRequestParam.UserLogin(user);
        callService(context, param, GlobalConstants.LOGIN_API, context.getString(R.string.loginLoading), user);
    }

    @Override
    public void serverCallUserLoginResponse(String response) {
        jsonParser = new JsonParser();
        usersJsonParser = jsonParser.getUserParserObject();
        User user = usersJsonParser.parseUserLoginResponse(response);
        if (user != null) {
            isExecuted = true;
        } else {
            isExecuted = false;
        }
        requestCallbackListener.notifyToCaller(isExecuted, user);
    }

    @Override
    public void serverCallUserRegistration(Context context, User user) {
        getUsersRequestParam = requestParameter.getUsersRequestParamObject();
        param = getUsersRequestParam.UserLogin(user);
        callService(context, param, GlobalConstants.REGISTRATION_API, context.getString(R.string.registrationLoading), user);
    }

    @Override
    public void serverCallUserRegistrationResponse(String response, User registeredUser) {
        jsonParser = new JsonParser();
        usersJsonParser = jsonParser.getUserParserObject();
        User user = usersJsonParser.parseUserLoginResponse(response);
        if (user != null) {
            isExecuted = true;
            user.setEmail(registeredUser.getEmail());
            user.setPassword(registeredUser.getPassword());
        } else {
            isExecuted = false;
        }
        requestCallbackListener.notifyToCaller(isExecuted, user);
    }

    @Override
    public void serverCallForgetPassword(Context context, User user) {
        getUsersRequestParam = requestParameter.getUsersRequestParamObject();
        param = getUsersRequestParam.UserLogin(user);
        callService(context, param, GlobalConstants.FORGET_PASSWORD_API, context.getString(R.string.registrationLoading), user);
    }

    @Override
    public void serverCallForgetPasswordResponse(String response) {

    }

    private void callService(Context context, List<NameValuePair> param, final String url, String message, final User user) {
        try {

            userServicesAsyncTask = new UserServicesAsyncTask(context, param, url, message, GlobalConstants.POST, new AsyncCallbackInterface() {
                @Override
                public void onPostExecute(String response) {
                    switch (url) {
                        case GlobalConstants.LOGIN_API:
                            serverCallUserLoginResponse(response);
                            break;
                        case GlobalConstants.REGISTRATION_API:
                            serverCallUserRegistrationResponse(response, user);
                            break;
                        case GlobalConstants.FORGET_PASSWORD_API:
                            serverCallForgetPasswordResponse(response);
                            break;
                    }

                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                userServicesAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            else
                userServicesAsyncTask.execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
