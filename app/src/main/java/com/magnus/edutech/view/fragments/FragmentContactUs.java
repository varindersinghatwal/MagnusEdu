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

public class FragmentContactUs extends Fragment {

    private TextView textViewContactUSHeader,textViewContactUSBody;
    private Typeface typeface;
    private Context context;

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fragement_contact_us, container, false);

      context = rootView.getContext();
      typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);

      textViewContactUSHeader = (TextView) rootView.findViewById(R.id.textViewContactUSHeader);
      textViewContactUSHeader.setTypeface(typeface);
      textViewContactUSBody = (TextView) rootView.findViewById(R.id.textViewContactUSBody);
      textViewContactUSBody.setTypeface(typeface);

        return rootView;
    }





}
