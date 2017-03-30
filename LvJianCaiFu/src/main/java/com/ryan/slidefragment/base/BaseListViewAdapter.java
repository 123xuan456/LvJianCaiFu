package com.ryan.slidefragment.base;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

public abstract class BaseListViewAdapter<T, Q> extends BaseAdapter {

	public Context context;

	public List<T> mLists;

	public View Q;

	public BaseListViewAdapter(Context context, List<T> mLists) {
		this.context = context;
		this.mLists = mLists;
	}

	public BaseListViewAdapter(Context context, List<T> mLists, View q) {
		this.context = context;
		this.mLists = mLists;
		Q = q;
	}

	public BaseListViewAdapter() {
	}

	
	public int getCount() {
		return mLists.size();
	}

	
	public Object getItem(int position) {
		return mLists.get(position);
	}

	
	public long getItemId(int position) {
		return position;
	}

}
