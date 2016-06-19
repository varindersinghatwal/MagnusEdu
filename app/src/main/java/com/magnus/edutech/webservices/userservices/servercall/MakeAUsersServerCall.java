package com.magnus.edutech.webservices.userservices.servercall;

import android.content.Context;

import com.magnus.edutech.model.User;

/**
 * Created by joshi on 6/19/2016.
 */
public interface MakeAUsersServerCall {

    // Server Call for User Login
    public void serverCallUserLogin(Context context, User user);
    public void serverCallUserLoginResponse(String response);

    // Server Call for User Registration
    public void serverCallUserRegistration(Context context, User user);
    public void serverCallUserRegistrationResponse(String response,User user);

    // Server Call for Forget Password
    public void serverCallForgetPassword(Context context,User user);
    public void serverCallForgetPasswordResponse(String response);

}
