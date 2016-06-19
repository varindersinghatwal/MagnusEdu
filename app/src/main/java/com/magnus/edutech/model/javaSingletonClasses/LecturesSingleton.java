package com.magnus.edutech.model.javaSingletonClasses;

import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

import java.util.List;

/**
 * Created by joshi on 6/18/2016.
 */
public class LecturesSingleton {

    private  List<Course> courseList;
    private  List<Chapters> chaptersList;
    private  List<Videos> videosList;
    private static LecturesSingleton ourInstance = new LecturesSingleton();

    public static LecturesSingleton getInstance() {
        return ourInstance;
    }

    private LecturesSingleton() {
    }

    public  List<Course> getCourseList() {
        return courseList;
    }

    public  void setCourseList(List<Course> courseList) {
        courseList = courseList;
    }

    public  List<Chapters> getChaptersList() {
        return chaptersList;
    }

    public  void setChaptersList(List<Chapters> chaptersList) {
        chaptersList = chaptersList;
    }

    public  List<Videos> getVideosList() {
        return videosList;
    }

    public  void setVideosList(List<Videos> videosList) {
        videosList = videosList;
    }
}
