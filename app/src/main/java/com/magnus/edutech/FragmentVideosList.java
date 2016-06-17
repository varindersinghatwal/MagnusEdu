package com.magnus.edutech;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.magnus.edutech.adapter.VideosFragmentRecycleViewAdapter;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.webservices.GlobalConstants;
import com.magnus.edutech.webservices.HelpingClass;

import java.util.List;

public class FragmentVideosList extends Fragment {

    // Class
    private HelpingClass helpingClass;
    private RecyclerView.LayoutManager mLayoutManager;
    // XML
    private RecyclerView recycleViewVideosGrid;

    // Variables
    private RecyclerView.Adapter<VideosFragmentRecycleViewAdapter.ViewHolder> mAdapter;
    private Context context;
    private Chapters chapters;
    private List<Videos> ListOfVideos;

    private String SUBJECT_ID = "";
    private String CATEGORY_ID = "";
    private View rootView;

    public FragmentVideosList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_fragment_videos, container, false);
        context = container.getContext();

        helpingClass = new HelpingClass(context);
        recycleViewVideosGrid = (RecyclerView) rootView.findViewById(R.id.recycleViewVideosGrid);

        // GET BUNDLE FROM FRAGMENT
        Bundle bundle = getArguments();
        if (bundle != null) {
            chapters = (Chapters) bundle.getSerializable(GlobalConstants.HAS_BUNDLE);
            SUBJECT_ID = chapters.getSubject_id();
            CATEGORY_ID = chapters.getCategory_id();
        }
        initVariable();
        return rootView;
    }

    private void initVariable() {
        populateList();
    }

    private void populateList() {

        mLayoutManager = new LinearLayoutManager(context);
        recycleViewVideosGrid.setLayoutManager(mLayoutManager);
        NotifiedList();

    }

    private void NotifiedList() {
        if (SUBJECT_ID != null && !SUBJECT_ID.equalsIgnoreCase("") && CATEGORY_ID != null && !CATEGORY_ID.equalsIgnoreCase("")) {

            Videos videos = new Videos();
            videos.setSubject_id(SUBJECT_ID);
            videos.setCategory_id(CATEGORY_ID);
            videos.setFrom(GlobalConstants.VIDEOS);

            ListOfVideos = helpingClass.fetchVideosFromDb(videos);

            if (ListOfVideos != null && ListOfVideos.size() > 0) {
                mAdapter = new VideosFragmentRecycleViewAdapter(context, ListOfVideos);
                recycleViewVideosGrid.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } else {

                Toast.makeText(context, "No videos available ", Toast.LENGTH_LONG).show();
            }
        }

    }

	/*
	    private class VideoClickListener implements GridView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int articleNum, long id) {
	            Intent i = new Intent(ActivityVideosGrid.this, ActivityVideo.class);
	            i.putExtra("videoId",(articleNum+1));
	            i.putExtra("categoryId",categoryId);
	            i.putExtra("subjectId",subjectId);
	            startActivity(i);
	            //Mobile- 9872723432 Mr sanjay
	        }
	    }

	    private class PdfClickListener implements GridView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int articleNum, long id) {
	            Intent i = new Intent(ActivityVideosGrid.this, PdfFragment.class);
	            startActivity(i);
	        }
	    }
	*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // intialize the variables
       /* initVariable();*/
    }

    @Override
    public void onResume() {
        initVariable();
        super.onResume();
    }
}
