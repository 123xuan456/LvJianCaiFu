package com.ryan.slidefragment.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.Handler;

import com.ryan.slidefragment.base.BaseApplication;



public class ThreadUtils {
    /**
     * newFixedThreadPool就是一个固定大小的ThreadPool
     * @param nThreads
     * @return
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {  
        return new ThreadPoolExecutor(nThreads, nThreads,  
                                      0L, TimeUnit.MILLISECONDS,  
                                      new LinkedBlockingQueue<Runnable>());  
    }  
    /**
     * newCachedThreadPool比较适合没有固定大小并且比较快速就能完成的小任务，没必要维持一个Pool，
     * 这比直接new Thread来处理的好处是能在60s内重新用已创建的线程
     * @return
     */
    public static ExecutorService newCachedThreadPool() {  
        return new ThreadPoolExecutor(2,4,  
                                      60L, TimeUnit.SECONDS,  
                                      new LinkedBlockingQueue<Runnable>());  
    }  
	/** 获取主线程的handler */
	public static Handler getHandler() {
		return BaseApplication.getMainThreadHandler();
	}

	/** 延时在主线程执行runnable */
	public static boolean postDelayed(Runnable runnable, long delayMillis) {
		return getHandler().postDelayed(runnable, delayMillis);
	}

	/** 在主线程执行runnable */
	public static boolean post(Runnable runnable) {
		
		return getHandler().post(runnable);
	}

	/** 从主线程looper里面移除runnable */
	public static void removeCallbacks(Runnable runnable) {
		getHandler().removeCallbacks(runnable);
	}
}
