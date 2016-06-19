package com.magnus.edutech.webservices;

import com.magnus.edutech.webservices.lectureservices.servercall.MakeALectureServerCall;
import com.magnus.edutech.webservices.lectureservices.servercall.impl.MakeALectureServerCallImpl;
import com.magnus.edutech.webservices.userservices.servercall.MakeAUsersServerCall;
import com.magnus.edutech.webservices.userservices.servercall.impl.MakeAUsersServerCallImpl;

/**
 * Created by joshi on 6/19/2016.
 */
public class MakeAServiceCall {

    public MakeAServiceCall() {
    }

    // Return parameter object
    public MakeALectureServerCall getLectureServerCallObject(RequestCallbackListener listener)
    {
        return  new MakeALectureServerCallImpl(listener);
    }

    // Return parameter object
    public MakeAUsersServerCall getUserServerCallObject(RequestCallbackListener listener)
    {
        return  new MakeAUsersServerCallImpl(listener);
    }
}
