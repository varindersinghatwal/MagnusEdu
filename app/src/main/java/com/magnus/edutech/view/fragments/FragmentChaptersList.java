package com.magnus.edutech.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.magnus.edutech.R;
import com.magnus.edutech.view.adapter.ChapterFragmentRecyleViewAdapter;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.webservices.HelpingClass;

import java.util.List;

public class FragmentChaptersList extends Fragment implements OnClickListener {

    // Class
    private HelpingClass helpingClass;
    private RecyclerView.LayoutManager mLayoutManager;
    // XML
    private RecyclerView recycleViewChapterList;
    /* private TextView noChapterFoundCHPF;
    private EditText SearchEditBoxCHPF;
	ImageView SearchImageViewCHPF;*/
    // Variables
    private RecyclerView.Adapter<ChapterFragmentRecyleViewAdapter.ViewHolder> mAdapter;
    private Context context;
    private FragmentActivity fragmentActivity;
    // Declaring Type Face Font
    private List<Chapters> arrayListOfCategories;
    private Course course;
    private View rootView;
    private String SUBJECT_ID = "";

    public FragmentChaptersList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_fragment_chapters, container, false);
        context = container.getContext();
        fragmentActivity = getActivity();
        helpingClass = new HelpingClass(context);
        /*
		SearchEditBoxCHPF = (EditText)rootView.findViewById(R.id.SearchEditBoxCHPF);
		SearchEditBoxCHPF.setOnClickListener(this);
		SearchImageViewCHPF = (ImageView)rootView.findViewById(R.id.SearchImageViewCHPF);
		SearchImageViewCHPF.setOnClickListener(this);
		noChapterFoundCHPF = (TextView) rootView.findViewById(R.id.noChapterFoundCHPF);*/
        recycleViewChapterList = (RecyclerView) rootView.findViewById(R.id.recycleViewChapterList);

        // GET BUNDLE FROM FRAGMENT
        Bundle bundle = getArguments();
        if (bundle != null) {
            course = (Course) bundle.getSerializable(GlobalConstants.HAS_BUNDLE);
            SUBJECT_ID = course.getSubject_id();
        }

        return rootView;
    }

    private void initVariable() {
        populateList();
    }

    private void populateList() {

        mLayoutManager = new LinearLayoutManager(context);
        recycleViewChapterList.setLayoutManager(mLayoutManager);
        if (SUBJECT_ID != null && !SUBJECT_ID.equalsIgnoreCase("")) {

            Chapters chapters = new Chapters();
            chapters.setSubject_id(SUBJECT_ID);
            chapters.setFrom(GlobalConstants.CATEGORY);
            NotifiedList(chapters);
        }


    }

    private void NotifiedList(Chapters chapters) {

        arrayListOfCategories = helpingClass.fetchChaptersFromDb(chapters);

        if (arrayListOfCategories != null && arrayListOfCategories.size() > 0) {

            mAdapter = new ChapterFragmentRecyleViewAdapter(context, arrayListOfCategories, fragmentActivity);
            recycleViewChapterList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            recycleViewChapterList.setVisibility(View.VISIBLE);
            /*noChapterFoundCHPF.setVisibility(View.INVISIBLE);*/
        } else {
           /* noChapterFoundCHPF.setText(context.getResources().getString(R.string.NoCourseFoundHF) + "\" " + SearchEditBoxCHPF.getText().toString().trim() + "\"");
            noChapterFoundCHPF.setVisibility(View.VISIBLE);*/
        }


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // intialize the variables
        initVariable();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.SearchImageViewCHPF:
                String searchText = SearchEditBoxCHPF.getText().toString().trim();
                if (searchText != null && !searchText.equalsIgnoreCase("")) {
                    Chapters chapters = new Chapters();
                    chapters.setSubject_id(SUBJECT_ID);
                    chapters.setFrom(GlobalConstants.NAME);
                    chapters.setName(searchText);
                    NotifiedList(chapters);

                } else {
                    Chapters chapters = new Chapters();
                    chapters.setSubject_id(SUBJECT_ID);
                    chapters.setFrom(GlobalConstants.CATEGORY);
                    NotifiedList(chapters);

                }
                break;
            case R.id.SearchEditBoxCHPF:
                if (arrayListOfCategories != null && arrayListOfCategories.size() > 0) {
                    noChapterFoundCHPF.setVisibility(View.INVISIBLE);
                }
                break;*/
        }

    }
}
