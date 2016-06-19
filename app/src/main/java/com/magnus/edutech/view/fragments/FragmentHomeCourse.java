package com.magnus.edutech.view.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.magnus.edutech.R;
import com.magnus.edutech.view.adapter.HomeFragmentGridViewAdapter;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.webservices.HelpingClass;

import java.util.List;

public class FragmentHomeCourse extends Fragment implements OnClickListener {
	
	//Class			
	private  HelpingClass helpingClass;
	private RecyclerView.LayoutManager mLayoutManager;
	
	//XML 
	private  RecyclerView recyclerViewHomeFragment;
	/*private EditText SearchEditBoxHF;
	ImageView SearchImageViewHF;
	private TextView noCourseFoundHF;*/
	//Variables
	
	private String CLASSNAME = FragmentHomeCourse.class.getName();
	private  RecyclerView.Adapter mAdapter;
	private  Context context;
	private  Typeface typefaceMedium ;
	private  FragmentActivity contextActivity;
	private List<Course> listOfCourse ;
	private View rootView ;
	
	public FragmentHomeCourse(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    	rootView = inflater.inflate(R.layout.layout_fragment_home, container, false);
        
        contextActivity = 	getActivity();
    	context = 			rootView.getContext() ;
        helpingClass  = new HelpingClass(context);
        
       /* SearchEditBoxHF = (EditText)rootView.findViewById(R.id.SearchEditBoxHF);
        SearchEditBoxHF.setOnClickListener(this);
        SearchImageViewHF = (ImageView)rootView.findViewById(R.id.SearchImageViewHF);
        SearchImageViewHF.setOnClickListener(this);
        noCourseFoundHF  = (TextView) rootView.findViewById(R.id.noCourseFoundHF);
        */
        recyclerViewHomeFragment = (RecyclerView) rootView.findViewById(R.id.recyclerViewHomeFragment);

      
        return rootView;
    }
    private void initVariable( )
   	{
    	populateList();
 	}
  
   
    
    public  void populateList() 
	{
    	    	 
		mLayoutManager = new GridLayoutManager(context, 2);
        recyclerViewHomeFragment.setLayoutManager(mLayoutManager);
		NotifiedList(null) ;
		
	}
	private void NotifiedList(Course course) 
	{
			listOfCourse  =helpingClass.fetchCoursesFromDb(course);
			if(listOfCourse !=null && listOfCourse.size()>0)
			{
				 mAdapter = new HomeFragmentGridViewAdapter(context,listOfCourse,contextActivity);
                recyclerViewHomeFragment.setAdapter(mAdapter);
			     mAdapter.notifyDataSetChanged();

                recyclerViewHomeFragment.setVisibility(View.VISIBLE);
			     /*noCourseFoundHF.setVisibility(View.INVISIBLE);*/
			}
			else
			{
				
				/* noCourseFoundHF.setText(context.getResources().getString(R.string.NoCourseFoundHF) + "\" " +SearchEditBoxHF.getText().toString().trim() +"\"");
			     noCourseFoundHF.setVisibility(View.VISIBLE);*/
			}
			
		
	}
	 @Override
	    public void onActivityCreated(Bundle savedInstanceState)    
	    {
	      super.onActivityCreated(savedInstanceState);
	      	// intialize the variables 
			initVariable();
	    }

	@Override
	public void onClick(View v) {

			switch(v.getId())
			{
				/*case R.id.SearchImageViewHF:
					  String searchText = SearchEditBoxHF.getText().toString().trim();
					  if(searchText !=null && ! searchText.equalsIgnoreCase(""))
					  {
						  Course course = new Course();
						  course.setFrom(GlobalConstants.NAME);
						  course.setName(searchText);
						  NotifiedList( course) ;
						  
					  }
					  else
					  {
						  NotifiedList(null) ;
					  }
					break;
				case R.id.SearchEditBoxHF:
					if(listOfCourse !=null && listOfCourse.size()>0)
					{
						  noCourseFoundHF.setVisibility(View.INVISIBLE);
					  }

					break;*/
			}
					
	}
}
