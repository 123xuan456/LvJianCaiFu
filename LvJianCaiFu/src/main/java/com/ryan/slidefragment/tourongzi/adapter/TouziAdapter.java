package com.ryan.slidefragment.tourongzi.adapter;

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

public class TouziAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> list;
	private ImageWorker mImageLoader;
	public TouziAdapter(Context context,
			List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		System.out.println("list="+list);
		mImageLoader = new ImageFetcher(context);
		mImageLoader.setImageCache(ImageCache.getInstance(context));
		
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
			convertView=LayoutInflater.from(context).inflate(R.layout.item_guquantouzi, null);
			holder.infotypeid=(TextView) convertView.findViewById(R.id.infotypeid);
			holder.address=(TextView) convertView.findViewById(R.id.address);
			holder.stagetypeid=(TextView) convertView.findViewById(R.id.stagetypeid);
			holder.zhutitypeid=(TextView) convertView.findViewById(R.id.zhutitypeid);
			holder.summoney=(TextView) convertView.findViewById(R.id.summoney);
			holder.fangshitypeid=(TextView) convertView.findViewById(R.id.fangshitypeid);
			holder.hangyetypeid=(TextView) convertView.findViewById(R.id.hangyetypeid);
			holder.toudi=(TextView) convertView.findViewById(R.id.toudi);
			holder.title=(TextView) convertView.findViewById(R.id.title);
			holder.tiaoshu=(TextView) convertView.findViewById(R.id.tiaoshu);
			holder.createtime=(TextView) convertView.findViewById(R.id.createtime);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.address.setText(list.get(position).get("address").toString());
		holder.infotypeid.setText(list.get(position).get("infotypeid").toString());
		holder.stagetypeid.setText(list.get(position).get("stagetypeid").toString());
		holder.zhutitypeid.setText(list.get(position).get("zhutitypeid").toString());
		holder.summoney.setText(list.get(position).get("summoney").toString());
		holder.fangshitypeid.setText(list.get(position).get("fangshitypeid").toString());
		holder.hangyetypeid.setText(list.get(position).get("hangyetypeid").toString());
		holder.title.setText(list.get(position).get("title").toString());
		holder.tiaoshu.setText(list.get(position).get("tiaoshu").toString());
		holder.createtime.setText(list.get(position).get("createtime").toString());
		return convertView;
	}
	class ViewHolder{
		TextView title,//标题
				infotypeid,//投资类型
				address,//投资地区
				stagetypeid,//投资阶段
				zhutitypeid,//资金类型
				summoney,//投资资金
				fangshitypeid,//投资方式
				hangyetypeid,//投资行业
				tiaoshu,//留言数
				createtime,//日期
				toudi;//立即投递
	}
}
