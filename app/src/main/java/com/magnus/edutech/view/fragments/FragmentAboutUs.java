package com.magnus.edutech.view.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magnus.edutech.R;
import com.magnus.edutech.App.GlobalConstants;

/**
 * Created by Joshi on 10/6/16.
 */
public class FragmentAboutUs extends Fragment {

    private TextView textViewAboutUSHeader,textViewAboutUSBody;

    private Typeface typeface;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fragement_about_us, container, false);
        context = rootView.getContext();
        typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);

        textViewAboutUSHeader = (TextView) rootView.findViewById(R.id.textViewAboutUSHeader);
        textViewAboutUSHeader.setTypeface(typeface);
        textViewAboutUSBody = (TextView) rootView.findViewById(R.id.textViewAboutUSBody);
        textViewAboutUSBody.setTypeface(typeface);


        return rootView;
    }
}
