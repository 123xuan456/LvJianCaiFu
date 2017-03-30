package com.ryan.slidefragment.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.RequestQueue;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.ryan.slidefragment.generaldemo.R;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class BaseApplication extends Application {

	// 图片用的
//	public static RequestQueue queue;
	public static int a;
	public static String name;
	public static String text;
	public static String text_fuwu;
	public static String txt_neirong1;
	public static String txt_mengyuanjibei_neirong;
	public static String t_jiarulianmengxieyis;
	public static String i_erweima_img;
	public static String i_erweima_txt;
	// 主线程的上下文
	private static BaseApplication mMainContext;
	// 获取到主线程的handler
	private static Handler mMainThreadHandler;
	// 获取到主线程的looper
	private static Looper mMainThreadLooper;
	// 获取到主线程的id
	private static int mMainThreadId;
	// 获取到程序的主线程
	private static Thread mMainThead;
	public static String latitude_me;
	public static String longitude_me;
	public static String mCityName;
	public static String userID; 
	public static String xinwen_biaoti;
	public static String xinwen_neirong;
	public static String macAddress;
	public static String myId;
	public static String fuwu;
	public static String fuwu_mengyuanjiebiexiangqing;
	public static int serverVersion;

//	public String gettit(){
//		return xinwen_biaoti;
//	}
//	
//	public void settit(String s){
//		  this.xinwen_biaoti = s;
//	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		settit("aa");
		// queue = Volley.newRequestQueue(getApplicationContext());

		// ImageLoaderConfiguration configuration = ImageLoaderConfiguration
		// .createDefault(this);
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		// ImageLoader.getInstance().init(configuration);
		// initImageLoader(getApplicationContext());
		this.mMainContext = this;
		this.mMainThreadHandler = new Handler();
		this.mMainThreadLooper = getMainLooper();
		this.mMainThreadId = android.os.Process.myTid();
		this.mMainThead = Thread.currentThread();
		// this.queue = queue;
	}

	public ImageLoader imageLoader = ImageLoader.getInstance();

	// public static RequestQueue getHttpRequestQueue(){
	// return queue;
	//
	// }
	/**
	 * 初始化ImageLoader
	 */
	public static void initImageLoader(Context context) {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.chanpinzhenjiazai) // 请求url为空时的默认图片
				.showImageOnFail(R.drawable.chanpinzhenjiazai) // 请求失败时的图片
				.showStubImage(R.drawable.chanpinzhenjiazai).cacheOnDisk(true) // 硬缓存
				.cacheInMemory(true).cacheOnDisk(true).build(); // 软缓存
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(defaultOptions)
				.diskCacheSize(25 * 1024 * 1024).discCacheFileCount(100)// 缓存一百张图片
				.writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}

	public static BaseApplication getApplication() {
		return mMainContext;
	}

	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	public static Looper getMainThreadLooper() {
		return mMainThreadLooper;
	}

	public static int getMainThreadId() {
		return mMainThreadId;
	}

	public static Thread getMainThread() {
		return mMainThead;
	}
}
