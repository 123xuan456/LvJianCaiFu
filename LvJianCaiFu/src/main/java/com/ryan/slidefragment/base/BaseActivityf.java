package com.ryan.slidefragment.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ryan.slidefragment.options.ImageLoaderOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseActivityf extends FragmentActivity {
//	public static String[] tabNames;
//	public static String[] tab_infomations;
//	public static ImageLoader instance;
	public static ImageLoaderOptions imageLoaderOptions;
	public static String PATH;
//	public static ArrayList<HomeList> newsList;//用于存储新问列表
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		tabNames = getResources().getStringArray(R.array.app_tab);
//		tab_infomations = getResources().getStringArray(R.array.app_tab_infomation);
//		File sdcardDir = Environment.getExternalStorageDirectory();
//		PATH = sdcardDir.getPath() + "/Android/data/com.lvjiancaifu";
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initImageLoader();
	}
	protected void initImageLoader() {
		System.out.print("羊羊羊恒源祥");
//		instance = ImageLoader.getInstance();
//		imageLoaderOptions = new ImageLoaderOptions(getApplicationContext());//getApplicationContext
		System.out.print("恒源祥，羊羊羊");
	}
}
