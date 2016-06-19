package com.magnus.edutech.view.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magnus.edutech.view.mapper.MainRoutingClass;
import com.magnus.edutech.R;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.App.GlobalConstants;

import java.util.List;

/**
 * Created by Joshi
 */
public class ChapterFragmentRecyleViewAdapter extends RecyclerView.Adapter<ChapterFragmentRecyleViewAdapter.ViewHolder> {

	//Class
	private MainRoutingClass mainRoutingClass;
	
	//Xml
	
	// Variable 
	private List<Chapters> listChapters;
	private TypedArray typedArrayIconParameter;
	private Context context;
	private Typeface typefaceContentMedium;
	private FragmentActivity fragmentActivity;
	
	
	
	public ChapterFragmentRecyleViewAdapter(Context ctx, List<Chapters> listChapters,FragmentActivity fragmentActivity) {
		super();
		this.listChapters = listChapters;
		context = ctx;
		this.fragmentActivity=fragmentActivity;
		mainRoutingClass = new MainRoutingClass(ctx);
		typefaceContentMedium = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
	}

	/*public ArrayList<HashMap<String, String>> getSubjectArrayList() {
		return listSubjects;
	}*/

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_adapter_fragment_chapters_list,
				viewGroup, false);
		ViewHolder viewHolder = new ViewHolder(v);
		return viewHolder;
	}

	/**
	 * On Bind View Holder :
	 * 
	 * @functionality : handle Grid View Click
	 * @nameParameter : Name Of parameter shown on gridList
	 * @iconParameter : Icon of parameter shown on gridList
	 * @iconBackground : Background Icon for parameter shown on gridList.
	 *
	 */

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		
		Chapters chapters= listChapters.get(position);
		viewHolder.categoryName.setText(chapters.getName());


		viewHolder.noOfVideoInsideThisCategory.setText(chapters.getCount()+"");
		viewHolder.noOfVideoInsideThisCategory.setTypeface(typefaceContentMedium);
		/*if(position==0||position%2==0)
		{
			viewHolder.rlCategoryItem.setBackgroundColor(context.getResources().getColor(R.color.colorChapterItemLayoutEven));;
		}
		else
		{
			viewHolder.rlCategoryItem.setBackgroundColor(context.getResources().getColor(R.color.white));;
		}*/
		viewHolder.rlCategoryItem.setTag(position);
		viewHolder.rlCategoryItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)

			{
				int position = (Integer) view.getTag();

				Chapters chapters = listChapters.get(position);
				
				if(chapters.getCount()>0)
				{
					mainRoutingClass.findFragmentRouteForNewOrder(GlobalConstants.FRAGMENT_VIDEOS_LIST, chapters, fragmentActivity);
				}
				else
				{
					GlobalConstants.showInformativeDialog("No Videos", "Comming soon ..." ,context);
				}
				
				
			}
		});

	}

	@Override
	public int getItemCount() {

		return listChapters.size();
	}

	/**
	 * Its a inner class to RecyclerViewAdapter Class. This ViewHolder class
	 * implements View.OnClickListener to handle click events. If the
	 * itemType==1 ; it implies that the view is a single row_item with TextView
	 * and ImageView. This ViewHolder describes an item view with respect to its
	 * place within the RecyclerView. For every item there is a ViewHolder
	 * associated with it .
	 */

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		LinearLayout rlCategoryItem;

		TextView categoryName, noOfVideoInsideThisCategory;

		public ViewHolder(View itemView) {
			super(itemView);

			rlCategoryItem = (LinearLayout) itemView.findViewById(R.id.rlCategoryItem);
			categoryName = (TextView) itemView.findViewById(R.id.categoryName);
			categoryName.setTypeface(typefaceContentMedium);
			noOfVideoInsideThisCategory = (TextView) itemView.findViewById(R.id.noOfVideoInsideThisCategory);
			noOfVideoInsideThisCategory.setTypeface(typefaceContentMedium);
		}

		@Override
		public void onClick(View v) {

		}
	}

}