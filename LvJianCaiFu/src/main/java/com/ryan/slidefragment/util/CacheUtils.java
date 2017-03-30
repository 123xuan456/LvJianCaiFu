package com.ryan.slidefragment.util;

import java.io.File;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;

/**
 * 缓存工具类.
 *@Title:
 *@Description:
 *@Author:Justlcw
 *@Since:2014-3-6
 *@Version:
 */
public final class CacheUtils
{
    /**
     * 获得bitmap的字节大小.
     * @Description:
     * @Author Justlcw
     * @Date 2014-3-6
     */
    public static int getBitmapSize(Bitmap bitmap)
    {
        return bitmap.getRowBytes() * bitmap.getHeight();
        //在低版本中用一行的字节*高度，来得到bitmap的字节的大小
    }

    /**
     * 外部存储是否可读写.
     * @Description:
     * @Author Justlcw
     * @Date 2014-3-5
     */
    public static boolean isExternalStorageRWable()
    {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获得可使用的<缓存主目录>(先外部,后外部)
     * @Description:
     * @Author Justlcw
     * @Date 2014-3-6
     */
    public static File getEnabledCacheDir(Context context, String cacheName)
    {
        String cachePath;
        if(isExternalStorageRWable())
        {
            cachePath = Environment.getExternalStorageDirectory().getPath();
        }
        else
        {
            cachePath = context.getCacheDir().getPath();
        }
        File cacheFile = new File(cachePath + CacheConfig.DISK_CACHE_NAME + cacheName);
        //如果缓存目录不存在,创建缓存目录.
        if (!cacheFile.exists())
        {
            cacheFile.mkdirs();
        }
        return cacheFile;
    }
    
    /**
     * 这个文件路径下,有多少空间可用.
     * @param path 文件路径.
     * @return 可用空间.
     */
    public static long getUsableSpace(File path)
    {
        final StatFs stats = new StatFs(path.getPath());
        return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    }

    /**
     * Return the approximate per-application memory class of the current device. 
     * This gives you an idea of how hard a memory limit you should impose on your 
     * application to let the overall system work best. The returned value is in megabytes; 
     * the baseline Android memory class is 16 (which happens to be the Java heap limit of those devices); 
     * some device with more memory may return 24 or even higher numbers.
     * @Description:
     * @Author Justlcw
     * @Date 2014-3-6
     */
    private static int getMemoryClass(Context context)
    {
        return ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
    }
    
    /**
     * 
     * @param context context
     * @param shrinkFactor 内存总大小的缩小倍数(如:如果传入8,将得到内存1/8的大小)
     * @Description:
     * @Author Justlcw
     * @Date 2014-3-7
     */
    public static int getMemorySize(Context context, int shrinkFactor)
    {
        int totalSize = getMemoryClass(context)*1024*1024;
        
        return totalSize / shrinkFactor;
    }

    public static void putString(String cheng, String cheng1) {

    }
}