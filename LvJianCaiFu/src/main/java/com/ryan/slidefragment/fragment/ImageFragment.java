package com.ryan.slidefragment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;

public class ImageFragment extends Fragment {
	private ImageWorker mImageLoader;
//	private static ImageLoader imageLoader = ImageLoader.getInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ImageView img = (ImageView) inflater.inflate(R.layout.img_layout,
				container, false);
		mImageLoader = new ImageFetcher(getActivity());
		mImageLoader.setImageCache(ImageCache.getInstance(getActivity()));
//		img.setImageResource(getArguments().getInt("img"));
//		for(String s:getArguments().getStringArrayList("img")){
//			imageLoader.displayImage(s, img);
//		}
		if(getArguments()!=null){
//			imageLoader.displayImage(getArguments().getString("img"), img);
//			 mImageLoader.loadImage(aa.get(position).get("image"), iv, R.drawable.cy_jianzhucailiao_1);
			mImageLoader.loadImage(getArguments().getString("img"), img, R.drawable.bag_lunbotu);
		}
		
		return img;
	}
}
