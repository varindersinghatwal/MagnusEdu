package com.magnus.edutech.webservices.userservices.responseparser.impl;

import android.util.Log;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.model.User;
import com.magnus.edutech.webservices.userservices.responseparser.UsersJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by joshi on 6/19/2016.
 */
public class UsersJsonParseImpl implements UsersJsonParser {

    private String message = null;
    private User user = null;

    @Override
    public User parseUserLoginResponse(String response) {
        JSONObject jsonObject = getJsonObject(response);
        user = null;
        message = null;
        try {
            if (jsonObject != null) {
                user = new User();
                user.setFrom(GlobalConstants.LOGIN);
                if (jsonObject.has(GlobalConstants.STATUS) && jsonObject.getInt(GlobalConstants.STATUS) == 1) {
                    user.setClintId(jsonObject.getString(GlobalConstants.CLIENT_ID));
                    user.setIs_query_execute(true);
                } else {
                    if (jsonObject.has(GlobalConstants.MESSAGE))
                        message = jsonObject.getString(GlobalConstants.MESSAGE);
                    user.setIs_query_execute(false);
                    user.setMessage(message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User parseUserRegistrationResponse(String response) {
        JSONObject jsonObject = getJsonObject(response);
        user = null;
        message = null;
        try {
            if (jsonObject != null) {
                user = new User();
                user.setFrom(GlobalConstants.REGISTRATION);
                if (jsonObject.has(GlobalConstants.STATUS) && jsonObject.getInt(GlobalConstants.STATUS) == 1) {
                    user.setIs_query_execute(true);
                } else {
                    if (jsonObject.has(GlobalConstants.MESSAGE))
                        message = jsonObject.getString(GlobalConstants.MESSAGE);
                    user.setIs_query_execute(false);
                    user.setMessage(message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User parseForgetPasswordResponse(String response) {
        JSONObject jsonObject = getJsonObject(response);
        user = null;
        message = null;
        try {
            if (jsonObject != null) {
                user = new User();
                user.setFrom(GlobalConstants.FORGET_PASSWORD);
                if (jsonObject.has(GlobalConstants.STATUS) && jsonObject.getInt(GlobalConstants.STATUS) == 1) {
                    user.setIs_query_execute(true);
                } else {
                    if (jsonObject.has(GlobalConstants.MESSAGE))
                        message = jsonObject.getString(GlobalConstants.MESSAGE);
                    user.setIs_query_execute(false);
                    user.setMessage(message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public JSONObject getJsonObject(String response) {
        JSONObject jsonObject = null;
        if (response != null) {
            String dataString = response.replace("&#039;", "\'");
            dataString = dataString.replace("&039;", "\'");
            try {
                jsonObject = new JSONObject(dataString.replace("&amp;", "&"));
            } catch (JSONException e) {
                Log.e("Parse JSON", e.getMessage());
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
