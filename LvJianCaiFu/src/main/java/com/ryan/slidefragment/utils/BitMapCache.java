package com.ryan.slidefragment.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitMapCache implements ImageCache{

	//注意图片缓存原来LruCache 类实现的
		public LruCache<String, Bitmap> cache;
		//超过10兆，自动回收
		public int max = 10*1024*1024;
		@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
		public BitMapCache(){
			cache = new LruCache<String, Bitmap>(max){
				
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes()*value.getHeight();
				}
			};
		}
		public Bitmap getBitmap(String url) {
			// TODO Auto-generated method stub
			return null;
		}
		public void putBitmap(String url, Bitmap bitmap) {
			// TODO Auto-generated method stub
			
		}
		
	
		

	}