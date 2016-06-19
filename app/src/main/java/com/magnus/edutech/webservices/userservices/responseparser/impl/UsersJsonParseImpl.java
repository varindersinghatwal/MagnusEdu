package com.magnus.edutech.webservices.userservices.responseparser.impl;

import android.util.Log;

import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.webservices.userservices.responseparser.UsersJsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshi on 6/19/2016.
 */
public class UsersJsonParseImpl implements UsersJsonParser {

    /* {"result":[{"subject_id":"1","name":"Ethics, Integrity and Aptitude"},
    {"subject_id":"2","name":"Essay"},{"subject_id":"3","name":"History and Culture- Ancient India"}]}*/
    @Override
    public List<Course> getListOfCourse(String response) {
        JSONArray jsonArrayResults = getJsonObject(response);
        List<Course> courseList = null;
        if (jsonArrayResults != null) {
            courseList = new ArrayList<Course>();
            for (int i = 0; i < jsonArrayResults.length(); i++) {
                try {
                    JSONObject subjectInfo = jsonArrayResults.getJSONObject(i);
                    Course course = new Course(subjectInfo.getString(GlobalConstants.SUBJECT_ID), subjectInfo.getString(GlobalConstants.NAME));
                    course.setPurchased(false);
                    courseList.add(course);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return courseList;
    }

    @Override
    public List<Chapters> getListOfChapters(String response) {
        JSONArray jsonArrayResults = getJsonObject(response);
        List<Chapters> chaptersList = null;
        if (jsonArrayResults != null) {
            chaptersList = new ArrayList<Chapters>();
            for (int i = 0; i < jsonArrayResults.length(); i++) {
                try {
                    JSONObject categoryInfo = jsonArrayResults.getJSONObject(i);
                    Chapters chapters = new Chapters(categoryInfo.getString(GlobalConstants.CATEGORY_ID),
                            categoryInfo.getString(GlobalConstants.SUBJECT_ID),
                            categoryInfo.getString(GlobalConstants.NAME));
                    chaptersList.add(chapters);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return chaptersList;
    }

    @Override
    public List<Videos> getListOfVideos(String response) {
        JSONArray jsonArrayResults = getJsonObject(response);
        List<Videos> videosList = null;
        if (jsonArrayResults != null) {
            videosList = new ArrayList<Videos>();
            for (int i = 0; i < jsonArrayResults.length(); i++) {
                try {
                    JSONObject videosInfo = jsonArrayResults.getJSONObject(i);
                    Videos videos = new Videos(videosInfo.getString(GlobalConstants.VIDEOS_ID),
                            videosInfo.getString(GlobalConstants.CATEGORY_ID),
                            videosInfo.getString(GlobalConstants.SUBJECT_ID),
                            videosInfo.getString(GlobalConstants.NAME),
                            videosInfo.getString(GlobalConstants.URL));
                    videos.setProduct_id("");
                    if (i < 2) {
                        videos.setPurchased(true);
                    } else {
                        videos.setPurchased(false);
                    }
                    videos.setDownloaded(false);
                    videosList.add(videos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return videosList;
    }

    @Override
    public JSONArray getJsonObject(String response) {
        JSONArray jsonArrayResults = null;
        if (response != null) {
            String dataString = response.replace("&#039;", "\'");
            dataString = dataString.replace("&039;", "\'");
            try {
                JSONObject jsonObject = new JSONObject(dataString.replace("&amp;", "&"));
                jsonArrayResults = jsonObject.getJSONArray("result");

            } catch (JSONException e) {
                Log.e("Parse JSON", e.getMessage());
                e.printStackTrace();
            }
        }
        return jsonArrayResults;
    }
}
