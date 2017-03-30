package com.ryan.slidefragment.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.slidefragment.domain.Type;
import com.ryan.slidefragment.generaldemo.R;



public class GridViewAdapter extends BaseAdapter{
	private ArrayList<Type> list;
	private Type type;
	private Context context;
	Holder view;

	public GridViewAdapter(Context context, ArrayList<Type> list) {
		this.list = list;
		this.context = context;
	}

	public int getCount() {
		if (list != null && list.size() > 0)
			return list.size();
		else
			return 0;
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("NewApi") 
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_gridview, null);
			view = new Holder(convertView);
			convertView.setTag(view);
		} else {
			view = (Holder) convertView.getTag();
		}
		if (list != null && list.size() > 0) {
			type = list.get(position);
			view.icon.setBackground(context.getResources().getDrawable(
					type.getIcon()));
			view.name.setText(type.getTypename());
		}

		return convertView;
	}

	private class Holder {
		private ImageView icon;
		private TextView name;

		public Holder(View view) {
			icon = (ImageView) view.findViewById(R.id.iv_grid_item);
			name = (TextView) view.findViewById(R.id.tv_grid_item);
		}
	}

}
