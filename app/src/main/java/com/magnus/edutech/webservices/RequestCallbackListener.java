package com.magnus.edutech.webservices;

/**
 * Created by joshi on 6/19/2016.
 */
public interface RequestCallbackListener {
    public void notifyToCaller(boolean isExecuted,Object obj);
}
