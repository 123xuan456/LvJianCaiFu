package com.ryan.slidefragment.tourongzi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.tourongzi.activity.jurenmai.JurenmaiActivity;
import com.ryan.slidefragment.tourongzi.ben.JuRenMai_ben;

import java.util.List;
import java.util.Map;

/**
 * Created by de on 2016/8/9.
 */
public class JuRenMaiAdapter extends BaseAdapter{
    private final List<Map<String, Object>> list;
    List<JuRenMai_ben.Jurenmai> juren;
    Context context;
    private ImageWorker mImageLoader;

    public JuRenMaiAdapter( Context context, List<Map<String, Object>> list) {

    this.context=context;
        this.list=list;
        mImageLoader = new ImageFetcher(context);
        mImageLoader.setImageCache(ImageCache.getInstance(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(
                    R.layout.item_jurenmai, null);
            holder.imageView7= (ImageView) view.findViewById(R.id.imageView7);
            holder.textView14= (TextView) view.findViewById(R.id.textView14);
            holder.textView11= (TextView) view.findViewById(R.id.textView11);
            holder.textView12= (TextView) view.findViewById(R.id.textView12);
            holder.xingbie= (TextView) view.findViewById(R.id.xingbie);
            view.setTag(holder);
        }else {
           holder= (ViewHolder) view.getTag();
        }
        Map<String,Object> s = list.get(i);
        holder.xingbie.setText(s.get("ming").toString()+s.get("bie").toString());
        holder.textView14.setText(s.get("content").toString());
        holder.textView11.setText(s.get("site").toString());
        holder.textView12.setText(s.get("title").toString());
            mImageLoader.loadImage(s.get("url").toString(),
                    holder.imageView7, R.drawable.rongzi);

        return view;
    }
    class ViewHolder{
        ImageView imageView7;
        TextView xingbie,textView11,//职位
                textView12,//内容
                textView14;//来自信息

    }

}
