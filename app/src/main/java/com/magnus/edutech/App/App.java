package com.magnus.edutech.App;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context mContext;

   
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}