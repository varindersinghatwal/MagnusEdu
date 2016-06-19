package com.magnus.edutech.view.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magnus.edutech.activities.HomeScreenActivity;
import com.magnus.edutech.view.mapper.MainRoutingClass;
import com.magnus.edutech.R;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.webservices.HelpingClass;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Edwin on 28/02/2015.
 */
public class HomeFragmentGridViewAdapter extends RecyclerView.Adapter<HomeFragmentGridViewAdapter.ViewHolder> {

    //Class
    private HelpingClass helpingClass;
    private MainRoutingClass mainRoutingClass;

    //Variables
    private List<Course> listOfCourse;
    private Context context;
    private Typeface typefaceContent;
    // (arbitrary) request code for the purchase flow
    private final int RC_REQUEST = 10001;
    private final String TAG = "MangusApp";
    TypedArray courseIcon;
    private HashMap<String, String> productMap = null;
    private FragmentActivity fragmentActivity;
    private HomeScreenActivity mainActivity;

    public HomeFragmentGridViewAdapter(Context ctx, List<Course> listOfCourse, FragmentActivity fragmentActivity) {
        super();
        this.listOfCourse = listOfCourse;
        context = ctx;
        mainActivity = (HomeScreenActivity) ctx;
        typefaceContent = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
        this.fragmentActivity = fragmentActivity;
        courseIcon = context.getResources().obtainTypedArray(R.array.course_icon);
        helpingClass = new HelpingClass(context);
        mainRoutingClass = new MainRoutingClass(ctx);
        }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_adapter_fragement_home_gridview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    /**
     * On Bind View Holder :
     *
     * @functionality         : handle Grid View Click
     * @nameParameter :  Name Of parameter shown on gridList
     * @iconParameter         :  Icon of parameter shown on gridList
     * @iconBackground :  Background Icon for parameter  shown on gridList.
     */

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Course course = listOfCourse.get(position);

        viewHolder.textNameSubject.setText(course.getName());


        viewHolder.rlItemListTP.setTag(position);
        viewHolder.rlItemListTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {
                int position = (Integer) view.getTag();
                Course course = listOfCourse.get(position);
                mainRoutingClass.findFragmentRouteForNewOrder(GlobalConstants.FRAGMENT_CHAPTERS_LIST, course, fragmentActivity);
            }
        });


        if(position<6)
        {
            viewHolder.imageViewCourseIcon.setImageResource(courseIcon.getResourceId(position,6));
        }
        else
        {
            viewHolder.imageViewCourseIcon.setImageResource(courseIcon.getResourceId(6,6));
        }
    	/* if(mapSubjectInfo.get(GlobalConstants.IS_PURCHASED).equalsIgnoreCase("1"))
    	 {
    		 viewHolder.imageViewPurchaseSubject.setVisibility(View.GONE);
    	 }
    	 else
    	 {
    		 viewHolder.imageViewPurchaseSubject.setVisibility(View.VISIBLE);
    	 }
    	 
    	 viewHolder.imageViewPurchaseSubject.setTag(position);
    	 viewHolder.imageViewPurchaseSubject.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             
             {
            	 int position  = (Integer) view.getTag();
            	 
            	 HashMap<String , String> mapSubjectInfoOnclick = listSubjects.get(position);
            	 setPresentProductID(mapSubjectInfoOnclick);
            	 callForPurchasingItem(mapSubjectInfoOnclick);
            	 
            	 
            	
            	 String subjectID =mapSubjectInfoOnclick.get(GlobalConstants.SUBJECT_ID);
                 Intent intent = new Intent(context,ActivityCategory.class);
                 intent.putExtra(GlobalConstants.SUBJECT_ID,subjectID);
                 context.startActivity(intent);
                 
             }
         });*/

    }

    @Override
    public int getItemCount() {

        return listOfCourse.size();
    }


    /**
     * Its a inner class to RecyclerViewAdapter Class.
     * This ViewHolder class implements View.OnClickListener to handle click events.
     * If the itemType==1 ; it implies that the view is a single row_item with TextView and ImageView.
     * This ViewHolder describes an item view with respect to its place within the RecyclerView.
     * For every item there is a ViewHolder associated with it .
     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        RelativeLayout rlItemListTP;
        ImageView imageViewCourseIcon;
        TextView textNameSubject;
    	/* ImageView imageViewPurchaseSubject;*/

        public ViewHolder(View itemView) {
            super(itemView);


            rlItemListTP = (RelativeLayout) itemView.findViewById(R.id.rlItemListTP);
            textNameSubject = (TextView) itemView.findViewById(R.id.textNameSubject);
            imageViewCourseIcon = (ImageView) itemView.findViewById(R.id.imageViewCourseIcon);
            textNameSubject.setTypeface(typefaceContent);
             /*imageViewPurchaseSubject = (ImageView) itemView.findViewById(R.id.imageViewPurchaseSubject);*/
        }

        /**
         * This defines onClick for every item with respect to its position.
         *
         * @functionality : handle Grid View Click
         * @status : 1 	- Active
         * 0 	- Not Active
         * @CHANGEORNOT     : True - Changes
         * false- No Changes
         * @Result :  Updating ArrayList
         */

        @Override
        public void onClick(View v) {

        }
    }


    public void setPresentProductID(HashMap<String, String> productId) {
        productMap = productId;
    }

    public HashMap<String, String> getPresentProductID() {
        return productMap;
    }

    public void notifyAllSetAfterPayment() {

        listOfCourse = helpingClass.fetchCoursesFromDb(null);

        notifyDataSetChanged();
    }

}