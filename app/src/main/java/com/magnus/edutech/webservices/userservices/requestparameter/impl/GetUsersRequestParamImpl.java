package com.magnus.edutech.webservices.userservices.requestparameter.impl;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.webservices.userservices.requestparameter.GetUsersRequestParam;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshi on 6/19/2016.
 */
public class GetUsersRequestParamImpl implements GetUsersRequestParam {

   List<NameValuePair> param;

    @Override
    public List<NameValuePair> getSubjectsParam(Course course) {
        return null;
    }

    @Override
    public List<NameValuePair> getChaptersParam(Chapters chapters) {
        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.SUBJECT_ID, chapters.getSubject_id()));
        return param;
    }

    @Override
    public List<NameValuePair> getVideosParam(Videos videos) {
        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.SUBJECT_ID, videos.getSubject_id()));
        param.add(new BasicNameValuePair(GlobalConstants.CATEGORY_ID, videos.getCategory_id()));
        return param;
    }
}
