package com.magnus.edutech.webservices.userservices.responseparser;

import com.magnus.edutech.model.User;

import org.json.JSONObject;

/**
 * Created by joshi on 6/19/2016.
 */
public interface UsersJsonParser {


    // login response
    User parseUserLoginResponse(String response);
    // Registration response
    User parseUserRegistrationResponse(String response);
    //Forget password response
    User parseForgetPasswordResponse(String response);
    // getJson Object
    JSONObject getJsonObject(String response);
}
