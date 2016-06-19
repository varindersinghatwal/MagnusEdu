package com.magnus.edutech.model.javaSingletonClasses;

/**
 * Created by joshi on 6/18/2016.
 */
public class LecturesSingleton {
    private static LecturesSingleton ourInstance = new LecturesSingleton();

    public static LecturesSingleton getInstance() {
        return ourInstance;
    }

    private LecturesSingleton() {
    }
}
