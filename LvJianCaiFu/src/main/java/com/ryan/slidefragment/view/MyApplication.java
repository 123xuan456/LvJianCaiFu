package com.ryan.slidefragment.view;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application{
	public static RequestQueue queue;
	
	@Override
	public void onCreate() {
		super.onCreate();
		queue = Volley.newRequestQueue(getApplicationContext());
		
		// 创建默认的ImageLoader配置参数  
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration  
//                .createDefault(this);  
  
        // Initialize ImageLoader with configuration.  
//        ImageLoader.getInstance().init(configuration); 
        

	}

	public static RequestQueue getHttpRequestQueue() {

		return queue;
	}
}