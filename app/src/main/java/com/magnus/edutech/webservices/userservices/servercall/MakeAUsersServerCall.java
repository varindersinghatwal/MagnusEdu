package com.magnus.edutech.webservices.userservices.servercall;

import android.content.Context;

import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

/**
 * Created by joshi on 6/19/2016.
 */
public interface MakeAUsersServerCall {

    // Server Call for subjects
    public void serverCallSubjects(Context context, Course course);
    public void serverCallSubjectsResponse(String response);

    // Server Call for Chapters
    public void serverCallCategories(Context context, Chapters chapters);
    public void serverCallCategoriesResponse(String response);

    // Server Call for Videos
    public void serverCallVideos(Context context, Videos videos);
    public void serverCallVideosResponse(String response);

}
