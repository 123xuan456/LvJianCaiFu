package com.ryan.slidefragment.tourongzi.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ryan.slidefragment.adapter.Adapter_ListView_cart.HolderView;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LiuYanAdapter extends BaseAdapter {
	private Context context;
	public List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private ImageWorker mImageLoader;
	public LiuYanAdapter(Context context,List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		System.out.println("lisdsasdast="+list);
	//	mImageLoader = new ImageFetcher(context);
	//	mImageLoader.setImageCache(ImageCache.getInstance(context));
		
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.item_liuyan, null);
			holder.content=(TextView) convertView.findViewById(R.id.content);
			holder.bie=(TextView) convertView.findViewById(R.id.bie);
			holder.site=(TextView) convertView.findViewById(R.id.site);
			holder.ming=(TextView) convertView.findViewById(R.id.ming);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
			holder.content.setText(list.get(position).get("content").toString());
			holder.bie.setText(list.get(position).get("bie").toString());
			holder.site.setText(list.get(position).get("site").toString());
			holder.ming.setText(list.get(position).get("ming").toString());
		}
		
		return convertView;
	}
	class ViewHolder{
		TextView content,//内容
				id,
				bie,//性别
				site,//地区
				ming,//姓
				url,//图片路径
				capid;
		
		
	}
}
