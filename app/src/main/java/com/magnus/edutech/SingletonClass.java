package com.magnus.edutech;

import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

/**
 * Created by joshi on 6/17/2016.
 */
public class SingletonClass {

    private static SingletonClass singleton;
    public static Course course;
    public static Chapters chapters;
    public static Videos videos;

    public static Course getCourse() {
        return course;
    }

    public static void setCourse(Course course) {
        SingletonClass.course = course;
    }

    public static Videos getVideos() {
        return videos;
    }

    public static void setVideos(Videos videos) {
        SingletonClass.videos = videos;
    }

    public static Chapters getChapters() {
        return chapters;
    }

    public static void setChapters(Chapters chapters) {
        SingletonClass.chapters = chapters;
    }

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private SingletonClass(){ }

    /* Static 'instance' method */
    public static SingletonClass getInstance( ) {
        if(singleton == null) {
            singleton = new SingletonClass();
        }
        return singleton;
    }



}
