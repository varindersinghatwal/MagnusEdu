package com.magnus.edutech.view.mapper;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.magnus.edutech.R;
import com.magnus.edutech.view.activities.LoginScreenActivity;
import com.magnus.edutech.view.activities.RegistrationScreenActivity;
import com.magnus.edutech.view.fragments.FragmentAboutUs;
import com.magnus.edutech.view.fragments.FragmentChaptersList;
import com.magnus.edutech.view.fragments.FragmentHomeCourse;
import com.magnus.edutech.view.fragments.FragmentVideosList;
import com.magnus.edutech.App.GlobalConstants;

import java.io.Serializable;


public class MainRoutingClass {
    public Context context;

    public MainRoutingClass(Context ctx) {
        context = ctx;
    }

    /**
     * Find route for Activity as per its constant in Constant file
     *
     * @param value
     */
    public void findActivityRoute(int value,Bundle bundle) {
        Intent intent = null;
        switch (value) {
            case GlobalConstants.ACTIVITY_LOGIN:
                intent = new Intent(context, LoginScreenActivity.class);
                break;
            case GlobalConstants.ACTIVITY_REGISTRATION:
                intent = new Intent(context, RegistrationScreenActivity.class);
                break;
          }

        if (context != null && intent != null)
        {
            if(bundle !=null)
            {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }
    }

    
    /**
     * Find route for Fragment  as per its constant in Constant file for new Order activity
     *
     * @param value
     */
    public void findFragmentRouteForNewOrder(int value, Serializable obj, FragmentActivity activity) {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        String className = "";
        switch (value) {
            case GlobalConstants.FRAGMENT_HOME_COURSE:
                fragment = new FragmentHomeCourse();
                className = String.valueOf(GlobalConstants.FRAGMENT_HOME_COURSE);

                break;
            case GlobalConstants.FRAGMENT_CHAPTERS_LIST:
                fragment = new FragmentChaptersList();
                className = String.valueOf(GlobalConstants.FRAGMENT_CHAPTERS_LIST);
                break;
            case GlobalConstants.FRAGMENT_VIDEOS_LIST:
            	fragment = new FragmentVideosList();
                className = String.valueOf(GlobalConstants.FRAGMENT_VIDEOS_LIST);
            	break;
            case GlobalConstants.FRAGMENT_ABOUT_US:
            	fragment = new FragmentAboutUs();
            	className = String.valueOf(GlobalConstants.FRAGMENT_ABOUT_US);
            	break;
           
        }
        if (fragment != null) {
            if (obj != null ) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalConstants.HAS_BUNDLE, obj);
                fragment.setArguments(bundle);
            }
            fragmentTransaction.addToBackStack(className);
            fragmentTransaction.add(R.id.frame_container, fragment,className);
            fragmentTransaction.commit();
        }
    }

}
