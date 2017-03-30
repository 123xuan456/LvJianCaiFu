package com.ryan.slidefragment.options;

//import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
//import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
//import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.LoadedFrom;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
//import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
//import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
//import com.nostra13.universalimageloader.core.imageaware.ImageAware;


public class ImageLoaderOptions {
//	public  DisplayImageOptions options;
//	public  DisplayImageOptions options_bg;
//	public ImageLoaderConfiguration imgconfig_brand;
//	public   DisplayImageOptions options_head;
//	public   LayoutParams layoutParams;
//	public ImageLoaderOptions(Context context) {
//		options = new DisplayImageOptions.Builder()  
//	.displayer(new FadeInBitmapDisplayer(1000))	
//	  .considerExifParams(true) 
//	   .delayBeforeLoading(500)
//	     .showImageOnLoading(R.drawable.loadingimg) //设置图片在下载期间显示的图片  
//	     .showImageForEmptyUri(R.drawable.loadingimg)//设置图片Uri为空或是错误的时候显示的图片  
//	    .showImageOnFail(R.drawable.loadingimg)  //设置图片加载/解码过程中错误时候显示的图片
//      .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中  
//      .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中  
//      .build();
//		options_bg = new DisplayImageOptions.Builder()  
//		.displayer(new FadeInBitmapDisplayer(1000))	
//		.considerExifParams(true) 
//		.delayBeforeLoading(500)
//		.cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中  
//		.cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中  
//		.build();
//		imgconfig_brand = new ImageLoaderConfiguration.Builder(context)  
//		    .memoryCacheExtraOptions(480, 800)          // default = device screen dimensions  
//		    .discCacheExtraOptions(480, 800, CompressFormat.PNG, 60, null)  
//				// default
//				.memoryCache(new LruMemoryCache(4 * 1024 * 1024))
//				.threadPoolSize(15)
//				// You can pass your own memory cache
//				// implementation/你可以通过自己的内存缓存实现
//				.memoryCacheSize(4 * 1024 * 1024)
//				.discCacheSize(50* 1024 * 1024)
//				   .discCacheFileCount(500) //缓存的文件数量  
//				    .discCache(new UnlimitedDiscCache(Environment.getExternalStorageDirectory()))//自定义缓存路径  
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5
//				.tasksProcessingOrder(QueueProcessingType.FIFO)  
//		    .imageDownloader(new BaseImageDownloader(context)) // default  
//		    .writeDebugLogs()  
//		    .build(); 
//		options_head = new DisplayImageOptions.Builder()  
//      .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中  
//	    .showImageOnFail(R.drawable.loadingimg)  //设置图片加载/解码过程中错误时候显示的图片head
//      .displayer(new BitmapDisplayer() {
//			
//			
//			public void display(Bitmap bitmap, ImageAware imageAware,
//					LoadedFrom loadedFrom) {
//				Bitmap roundBitmap = PictureUtils.toRoundBitmap(bitmap);
//				imageAware.setImageBitmap(roundBitmap);
//			}
//		})
//      .build();
//	}
}

