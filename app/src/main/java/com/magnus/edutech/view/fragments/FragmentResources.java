package com.magnus.edutech.view.fragments;

/**
 * Created by jugal kishor joshi
 */

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

public class FragmentResources extends Fragment {

    private TextView comingSoonResource;
    private Typeface typeface;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fragement_resources, container, false);
        context = rootView.getContext();
        typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);

        comingSoonResource = (TextView) rootView.findViewById(R.id.comingSoonResource);
        comingSoonResource.setTypeface(typeface);
      
        return rootView;
    }





}
