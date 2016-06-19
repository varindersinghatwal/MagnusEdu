package com.magnus.edutech.webservices.userservices.Impl;

/**
 * Created by joshi on 6/17/2016.
 */

import com.magnus.edutech.model.User;
import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.webservices.UserServices;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class UserServicesImpl implements UserServices {

    List<NameValuePair> param ;

    @Override
    public List<NameValuePair> UserRegistration(User user) {

        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.FULL_NAME, user.getFullName()));
        param.add(new BasicNameValuePair(GlobalConstants.EMAIL, user.getEmail()));
        param.add(new BasicNameValuePair(GlobalConstants.MOBILE, user.getMobile()));
        param.add(new BasicNameValuePair(GlobalConstants.PASSWORD, user.getPassword()));
        param.add(new BasicNameValuePair(GlobalConstants.RE_PASSWORD, user.getRePassword()));
        return param;
    }

    @Override
    public List<NameValuePair> UserLogin(User user) {

        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.USER_NAME, user.getEmail()));
        param.add(new BasicNameValuePair(GlobalConstants.PASSWORD, user.getPassword()));
        return param;
    }

    @Override
    public List<NameValuePair> ForgetPassword(User user) {
        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.EMAIL, user.getEmail()));
        return param;
    }

    @Override
    public List<NameValuePair> UpdateUserStatus(User user) {
        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.CLIENT_ID, user.getEmail()));
        param.add(new BasicNameValuePair(GlobalConstants.AMOUNT,user.getAmountPaid()));

        return null;
    }
}
