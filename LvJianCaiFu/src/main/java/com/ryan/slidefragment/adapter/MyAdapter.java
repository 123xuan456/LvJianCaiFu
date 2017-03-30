package com.ryan.slidefragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> list;

	public MyAdapter(Context context, List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		System.out.println("MyAdapter="+list);
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
					R.layout.chanpin_item, null);
			holder = new ViewHolder();

			// holder.picname_hospital_s = (ImageView)
			// convertView.findViewById(R.id.picname_hospital_s);
			// holder.id = (TextView) convertView.findViewById(R.id.id);
			holder.t_jishuzhanshi_xinwenbiaoti = (TextView) convertView
					.findViewById(R.id.t_jishuzhanshi_xinwenbiaoti);
			holder.hangyezixun_time = (TextView) convertView
					.findViewById(R.id.hangyezixun_time);

			holder.t_jishuzhanshi_xinwenneirong = (TextView) convertView
					.findViewById(R.id.t_jishuzhanshi_xinwenneirong);

			// holder.hdf_yy_tese = (TextView)
			// convertView.findViewById(R.id.hdf_yy_tese);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// holder.picname_hospital_s.setImageResource(R.drawable.ic_launcher);
		// holder.id.setText(list.get(position).get("id").toString());
		Map<String, Object> li = list.get(position);
		holder.t_jishuzhanshi_xinwenbiaoti.setText(li
				.get("title").toString());
		holder.t_jishuzhanshi_xinwenneirong.setText(list.get(position)
				.get("content").toString());
		holder.hangyezixun_time.setText(list.get(position)
				.get("time").toString());
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
		TextView id, t_jishuzhanshi_xinwenbiaoti, t_jishuzhanshi_xinwenneirong,
				hangyezixun_time;

	}
}
