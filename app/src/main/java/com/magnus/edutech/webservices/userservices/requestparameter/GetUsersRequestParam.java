package com.magnus.edutech.webservices.userservices.requestparameter;

import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by joshi on 6/19/2016.
 */
public interface GetUsersRequestParam {

    List<NameValuePair> getSubjectsParam(Course course);
    //get  parameter list for Categories server call
    List<NameValuePair> getChaptersParam(Chapters chapters);
    //get parameter list for Video server call
    List<NameValuePair> getVideosParam(Videos videos);

}
