package com.magnus.edutech.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnus.edutech.R;
import com.magnus.edutech.model.NavDrawerItem;
import com.magnus.edutech.webservices.GlobalConstants;

import java.util.ArrayList;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private Typeface typeface;
	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
		typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		//if (position < navDrawerItems.size() - 1) {

			if (convertView == null) {
				
				convertView = mInflater.inflate(R.layout.layout_drawer_list_item, null);
			}

			ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

			imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
			txtTitle.setText(navDrawerItems.get(position).getTitle());
			txtTitle.setTypeface(typeface);

		 /*}else {
					convertView = mInflater.inflate(R.layout.layout_drawer_list_item_logout, null);
		}*/

		return convertView;
	}

}
