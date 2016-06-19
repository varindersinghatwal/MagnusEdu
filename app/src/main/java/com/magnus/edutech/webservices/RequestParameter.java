package com.magnus.edutech.webservices;

import com.magnus.edutech.webservices.lectureservices.requestparameter.GetLecturesRequestParam;
import com.magnus.edutech.webservices.lectureservices.requestparameter.impl.GetLecturesRequestParamImpl;
import com.magnus.edutech.webservices.userservices.requestparameter.GetUsersRequestParam;
import com.magnus.edutech.webservices.userservices.requestparameter.impl.GetUsersRequestParamImpl;

/**
 * Created by joshi on 6/19/2016.
 */
public class RequestParameter {
    public RequestParameter() {
    }

    // Return parameter object
    public GetLecturesRequestParam getLectureRequestParamObject()
    {
        return  new GetLecturesRequestParamImpl();
    }

    public GetUsersRequestParam getUsersRequestParamObject()
    {
        return new GetUsersRequestParamImpl();
    }
}
