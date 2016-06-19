package com.magnus.edutech.webservices.lectureservices.responseparser;

import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

import java.util.List;

/**
 * Created by joshi on 6/19/2016.
 */
public interface LectureJsonParser {

    // parse subject list
    List<Course> getListOfCourse(String response);

    // parse  ChapterList
    List<Chapters> getListOfChapters(String response);

    // parse  ChapterList
    List<Videos> getListOfVideos(String response);
}
