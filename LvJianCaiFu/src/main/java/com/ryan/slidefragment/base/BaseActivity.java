package com.ryan.slidefragment.base;


//import com.nostra13.universalimageloader.core.ImageLoader;
import android.app.Activity;
import android.os.Bundle;

import com.ryan.slidefragment.options.ImageLoaderOptions;

public abstract class BaseActivity extends Activity {
	
//	public static ImageLoader instance;
	public static ImageLoaderOptions imageLoaderOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		findView();
		initView();
		initData();
		addListener();
	}

	public abstract void findView();

	public abstract void addListener();

	public abstract void initView();

	public abstract void initData();

}
