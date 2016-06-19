package com.magnus.edutech.webservices.userservices.servercall.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.R;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.model.javaSingletonClasses.LecturesSingleton;
import com.magnus.edutech.webservices.AsyncCallbackInterface;
import com.magnus.edutech.webservices.JsonParser;
import com.magnus.edutech.webservices.RequestCallbackListener;
import com.magnus.edutech.webservices.RequestParameter;
import com.magnus.edutech.webservices.lectureservices.asynctask.LectureServicesAsyncTask;
import com.magnus.edutech.webservices.lectureservices.requestparameter.GetLecturesRequestParam;
import com.magnus.edutech.webservices.lectureservices.responseparser.LectureJsonParser;
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
    private LectureServicesAsyncTask userServicesAsyncTask;
    private RequestParameter requestParameter;
    private GetLecturesRequestParam getLecturesRequestParam;
    private List<NameValuePair> param;
    LecturesSingleton lecturesSingleton;

    public MakeAUsersServerCallImpl(RequestCallbackListener ev) {
        this.requestCallbackListener = ev;
        requestParameter = new RequestParameter();
        lecturesSingleton = LecturesSingleton.getInstance();
    }

    //Subjects
    @Override
    public void serverCallSubjects(Context context, Course course) {
        try {
            getLecturesRequestParam = requestParameter.getRequestParamObject();
            param = getLecturesRequestParam.getSubjectsParam(course);
            userServicesAsyncTask = new LectureServicesAsyncTask(context, param, GlobalConstants.GET_SUBJECTS_API, "", GlobalConstants.GET, new AsyncCallbackInterface() {
                @Override
                public void onPostExecute(String response) {
                    serverCallSubjectsResponse(response);
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

    @Override
    public void serverCallSubjectsResponse(String response) {
        JsonParser jsonParser = new JsonParser();
        LectureJsonParser lectureJsonParser = jsonParser.getLectureParseObject();
        List<Course> courseList = lectureJsonParser.getListOfCourse(response);
        if (courseList != null) {
            lecturesSingleton.setCourseList(courseList);
            isExecuted = true;
        } else {
            isExecuted = false;
        }
        requestCallbackListener.notifyToCaller(isExecuted, response);
    }

    //Categories
    @Override
    public void serverCallCategories(Context context, Chapters chapters) {
        try {
            getLecturesRequestParam = requestParameter.getRequestParamObject();
            param = getLecturesRequestParam.getChaptersParam(chapters);
            userServicesAsyncTask = new LectureServicesAsyncTask(context, null, GlobalConstants.GET_CATEGORIES_API,
                    context.getString(R.string.server_loading), GlobalConstants.POST,
                    new AsyncCallbackInterface() {
                        @Override
                        public void onPostExecute(String response) {
                            serverCallSubjectsResponse(response);
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

    @Override
    public void serverCallCategoriesResponse(String response) {
        JsonParser jsonParser = new JsonParser();
        LectureJsonParser lectureJsonParser = jsonParser.getLectureParseObject();
        List<Chapters> chaptersList = lectureJsonParser.getListOfChapters(response);
        if (chaptersList != null) {
            lecturesSingleton.setChaptersList(chaptersList);
            isExecuted = true;
        } else {
            isExecuted = false;
        }
        requestCallbackListener.notifyToCaller(isExecuted, response);
    }

    //Videos
    @Override
    public void serverCallVideos(Context context, Videos videos) {
        try {
            getLecturesRequestParam = requestParameter.getRequestParamObject();
            param = getLecturesRequestParam.getVideosParam(videos);
            userServicesAsyncTask = new LectureServicesAsyncTask(context, null, GlobalConstants.GET_VIDEOS_API,
                    context.getString(R.string.server_loading), GlobalConstants.POST,
                    new AsyncCallbackInterface() {
                        @Override
                        public void onPostExecute(String response) {
                            serverCallSubjectsResponse(response);
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

    @Override
    public void serverCallVideosResponse(String response) {
        JsonParser jsonParser = new JsonParser();
        LectureJsonParser lectureJsonParser = jsonParser.getLectureParseObject();
        List<Videos> videosList = lectureJsonParser.getListOfVideos(response);
        if (videosList != null) {
            lecturesSingleton.setVideosList(videosList);
            isExecuted = true;
        } else {
            isExecuted = false;
        }
        requestCallbackListener.notifyToCaller(isExecuted, response);
    }
}
