package com.magnus.edutech.webservices;
import com.magnus.edutech.model.User;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by joshi on 6/17/2016.
 */
public interface UserServices {

    //User Registration
    List<NameValuePair> UserRegistration(User user);

    // User Login
    List<NameValuePair> UserLogin(User user);

    // Forget password
    List<NameValuePair> ForgetPassword(User user);

    // User payment status
    List<NameValuePair> UpdateUserStatus(User user);
}
