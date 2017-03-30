package com.ryan.slidefragment.tourongzi.adapter;

import java.util.List;

import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.tourongzi.ben.Root.Date.Guquan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CanGuHeZuoAdapter extends BaseAdapter{
	private List<Guquan> list;
	private Context context;

	public CanGuHeZuoAdapter(Context context,
			List<Guquan> list_guquan) {
		this.context=context;
		list=list_guquan;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
			convertView=LayoutInflater.from(context).inflate(R.layout.item_canguhezuo,null);
			holder.title=(TextView) convertView.findViewById(R.id.title);
			holder.provinceid=(TextView) convertView.findViewById(R.id.provinceid);
			holder.fangshitypeid=(TextView) convertView.findViewById(R.id.fangshitypeid);
			holder.hangyetypeid=(TextView) convertView.findViewById(R.id.hangyetypeid);
			holder.createtime=(TextView) convertView.findViewById(R.id.createtime);
			holder.jingzichan=(TextView) convertView.findViewById(R.id.jingzichan);

			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
			holder.createtime.setText(list.get(position).getCreatetime());
			holder.title.setText(list.get(position).getTitle());
			holder.provinceid.setText(list.get(position).getProvinceid());
			holder.fangshitypeid.setText(list.get(position).getFangshitypeid());
			holder.hangyetypeid.setText(list.get(position).getHangyetypeid());
			holder.jingzichan.setText(list.get(position).getJingzichan());
		}
		
		
		
		
		return convertView;
		
	}
	class ViewHolder{
		TextView title,
		provinceid,//
		fangshitypeid,
		hangyetypeid,
		yongtu,
		createtime,
		tiaoshu1,
		jingzichan;


		ImageView image;

	}
}
