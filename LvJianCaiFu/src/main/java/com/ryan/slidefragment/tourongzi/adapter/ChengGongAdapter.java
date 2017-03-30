package com.ryan.slidefragment.tourongzi.adapter;

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

import java.util.List;
import java.util.Map;

/**
 * Created by de on 2016/8/5.
 */
public class ChengGongAdapter extends BaseAdapter{
    private Context context;
    private List<Map<String, Object>> list;

    public ChengGongAdapter(Context context,
                                  List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
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
            holder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.tourongzi_item_chenggonganli, null);
            holder.content= (TextView) convertView.findViewById(R.id.content);
            holder.title= (TextView) convertView.findViewById(R.id.title);
            holder.yongtu= (TextView) convertView.findViewById(R.id.yongtu);
            holder.total= (TextView) convertView.findViewById(R.id.total);
            holder.textView5= (TextView) convertView.findViewById(R.id.textView5);
            holder.textView7= (TextView) convertView.findViewById(R.id.textView7);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.content.setText(list.get(position).get("content").toString());
            holder.title.setText(list.get(position).get("name").toString());
            holder.yongtu.setText(list.get(position).get("yongtu").toString());
            holder.total.setText(list.get(position).get("province").toString());
            holder.textView5.setText(list.get(position).get("clicks").toString());
            holder.textView7.setText(list.get(position).get("createtime").toString());
        return convertView;
    }

    class ViewHolder {
        TextView title,//标题
                yongtu,
                total,//地区
                content,//内容
                textView5,//浏览量
                textView7;//时间

    }
}
