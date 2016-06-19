package com.magnus.edutech.webservices.lectureservices.requestcreator.Impl;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.webservices.lectureservices.requestcreator.ChapterServices;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshi on 6/18/2016.
 */
public class ChapterServiceImpl implements ChapterServices {

    List<NameValuePair> param;

    @Override
    public List<NameValuePair> getChapters(Chapters chapters) {
        param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair(GlobalConstants.SUBJECT_ID, chapters.getSubject_id()));
        return param;
    }
}
