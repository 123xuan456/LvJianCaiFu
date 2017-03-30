package com.ryan.slidefragment.tourongzi.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.tourongzi.adapter.ZhaoxiangmuAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XiangMuRongZiAdapter extends BaseAdapter{
	private List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	private Context context;

	public XiangMuRongZiAdapter(Context context,
			List<Map<String, Object>> list) {
		this.context=context;
		this.list=list;
		System.out.println("list12="+list);
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
		convertView=LayoutInflater.from(context).inflate(R.layout.item_xiangmurongzi, null);
		holder.title=(TextView) convertView.findViewById(R.id.title);
		holder.provinceid=(TextView) convertView.findViewById(R.id.provinceid);
		holder.fangshitypeid=(TextView) convertView.findViewById(R.id.fangshitypeid);
		holder.hangyetypeid=(TextView) convertView.findViewById(R.id.hangyetypeid);
		holder.createtime=(TextView) convertView.findViewById(R.id.createtime);
		holder.yongtu=(TextView) convertView.findViewById(R.id.yongtu);
		holder.description=(TextView) convertView.findViewById(R.id.description);
		holder.amount=(TextView) convertView.findViewById(R.id.amount);
		holder.total=(TextView) convertView.findViewById(R.id.total);
	//	holder.jingzichan=(TextView) convertView.findViewById(R.id.jingzichan);
	//	holder.tiaoshu1=(TextView) convertView.findViewById(R.id.tiaoshu1);
	//	holder.jingzichan=(TextView) convertView.findViewById(R.id.jingzichan);

		convertView.setTag(holder);
	}else {
		holder=(ViewHolder) convertView.getTag();
	}
		String t = list.get(position).get("createtime").toString();
		System.out.println("t="+t);
		holder.createtime.setText(t);
		String t1 =list.get(position).get("title").toString();
		System.out.println("t1="+t1);
		holder.title.setText(t1);
		String t2 =list.get(position).get("provinceid").toString();
		holder.provinceid.setText(t2);
		System.out.println("t2="+t2);
		holder.fangshitypeid.setText(list.get(position).get("fangshitypeid").toString());
		holder.hangyetypeid.setText(list.get(position).get("hangyetypeid").toString());
		holder.yongtu.setText(list.get(position).get("yongtu").toString());
	//	holder.jingzichan.setText(list.get(position).get("jingzichan").toString());
		holder.description.setText(list.get(position).get("description").toString());
		holder.amount.setText(list.get(position).get("amount").toString());
		holder.total.setText(list.get(position).get("total").toString());
	
	return convertView;
	
}
class ViewHolder{
	TextView title,
			provinceid,//所在地区
			fangshitypeid,//方式
			hangyetypeid,//行业
			yongtu,//用途
			description,
			createtime,//时间
			amount,
			total,
			jingzichan;//融资资金
	ImageView image;


}
}
