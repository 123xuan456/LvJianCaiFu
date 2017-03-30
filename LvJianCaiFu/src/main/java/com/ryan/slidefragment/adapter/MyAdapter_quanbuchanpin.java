package com.ryan.slidefragment.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;

public class MyAdapter_quanbuchanpin extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> list;
	private ImageWorker mImageLoader;
	public MyAdapter_quanbuchanpin(Context context,
			List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		// mImageLoader.setImageCache(ImageCache.getInstance(getActivity()));
		mImageLoader = new ImageFetcher(context);
		mImageLoader.setImageCache(ImageCache.getInstance(context));
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.quanbuchanpin_item, null);
			holder = new ViewHolder();
			holder.t_jishuzhanshi_xinwendizhi = (TextView) convertView
			.findViewById(R.id.t_jishuzhanshi_xinwendizhi);
			holder.t_jishuzhanshi_xinwenbiaoti = (TextView) convertView
					.findViewById(R.id.t_jishuzhanshi_xinwenbiaoti);
			holder.t_jishuzhanshi_xinwenneirong = (TextView) convertView
					.findViewById(R.id.t_jishuzhanshi_xinwenneirong);
			holder.iv_adapter_list_pic = (ImageView) convertView
					.findViewById(R.id.iv_adapter_list_pic);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.t_jishuzhanshi_xinwenbiaoti.setText(list.get(position)
				.get("name").toString());
		holder.t_jishuzhanshi_xinwenneirong.setText(list.get(position)
				.get("content").toString());
		holder.t_jishuzhanshi_xinwendizhi.setText(list.get(position)
				.get("address").toString());
//		imageLoader.displayImage((String) list.get(position).get("imgurl"),
//				holder.iv_adapter_list_pic);
		mImageLoader.loadImage((String) list.get(position).get("imgurl"),
				holder.iv_adapter_list_pic, R.drawable.detail_show_1);
		// holder.hdf_yy_tese.setText(list.get(position).get("hdf_yy_tese").toString());
		// 接口回调的方法，完成图片的读取;
		// DownImage downImage = new
		// DownImage(list.get(position).get("picname_hospital_s").toString());
		// downImage.loadImage(new ImageCallBack() {
		//
		// @Override
		// public void getDrawable(Drawable drawable) {
		// holder.picname_hospital_s.setImageDrawable(drawable);
		// }
		// });

		return convertView;
	}

	class ViewHolder {
		TextView id, t_jishuzhanshi_xinwenbiaoti, t_jishuzhanshi_xinwenneirong,t_jishuzhanshi_xinwendizhi;
		// ImageView picname_hospital_s;
		ImageView iv_adapter_list_pic;
	}
}
