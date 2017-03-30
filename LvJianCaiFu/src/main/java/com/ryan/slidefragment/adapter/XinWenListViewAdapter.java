package com.ryan.slidefragment.adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.R;


/***
 * 首页新闻列表
 * @author de
 *
 */
public class XinWenListViewAdapter extends BaseAdapter {
	Context context;
	private List<Map<String, Object>> list1;
	public XinWenListViewAdapter(Activity mActivity, List<Map<String, Object>> list) {
		this.context=mActivity;
		this.list1=list;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list1.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list1.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_home_xinwen, null);
			holder=new ViewHolder();
			holder.imageView=(ImageView) convertView.findViewById(R.id.iv_xinwen_icon);
			holder.textView=(TextView) convertView.findViewById(R.id.tv_xinwen_name);
			convertView.setTag(holder);
		}{
		holder=(ViewHolder) convertView.getTag();
		}
		holder.textView.setText(list1.get(position).get("title").toString());
		return convertView;
	}

	class ViewHolder{
		ImageView imageView;
		TextView textView;
	}

	

}
