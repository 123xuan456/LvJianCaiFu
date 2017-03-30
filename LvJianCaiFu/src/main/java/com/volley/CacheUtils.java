package com.volley;

import java.io.File;
import java.text.DecimalFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.TextView;

import com.ryan.slidefragment.base.BaseApplication;

public class CacheUtils {
	
	public static final String CACHE_FILE_NAME = "config_cache";
	private static SharedPreferences mSharedPreferences;
	
	public static void putBoolean(String key, boolean value) {
		if(mSharedPreferences == null) {
			mSharedPreferences =  BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}
	
	
	public static boolean getBoolean(String key, boolean defValue) {
		if(mSharedPreferences == null) {
			mSharedPreferences =  BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getBoolean(key, defValue);
	}

	public static void putString(String key, String value) {
		if(mSharedPreferences == null) {
			mSharedPreferences =  BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		if(mSharedPreferences == null) {
			mSharedPreferences =  BaseApplication.getApplication().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getString(key, defValue);
	}
	/**
	 * 缓冲相关
	 */
	
	public void setFileTrueSize(TextView cache) {
		File sdcardDir = Environment.getExternalStorageDirectory();
		String path = sdcardDir.getPath() + "/Android/data/com.haha.lvjiancaifu.lyz";
		File path1 = new File(path);
		try {
			Long sumSizeLong=getFolderSize(path1);
			String sumSizeString=setFileSize(sumSizeLong);
			cache.setText(sumSizeString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取文件大小
	 * @param size
	 * @return
	 */
	public static String setFileSize(long size) {
		DecimalFormat df = new DecimalFormat("###.##");
		float f = ((float) size / (float) (1024 * 1024));

		if (f < 1.0) {
			float f2 = ((float) size / (float) (1024));

			return df.format(new Float(f2).doubleValue()) + " KB";

		} else {
			return df.format(new Float(f).doubleValue()) + " MB";
		}

	}
	
	public static long getFolderSize(File file)throws Exception{
		long size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++)
        {
            if (fileList[i].isDirectory())
            {
                size = size + getFolderSize(fileList[i]);
            } else
            {
                size = size + fileList[i].length();
            }
        }
        return size;
	}
	 public void delAllFile(final String path) {
	    	
	    	new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					File file = new File(path);
		            if (!file.exists()) {
		                    return;
		            }
		            if (!file.isDirectory()) {
		           return;
		            }
		            String[] tempList = file.list();
		            File temp = null;
		            for (int i = 0; i < tempList.length; i++) {
		                    if (path.endsWith(File.separator)) {
		                            temp = new File(path + tempList[i]);
		                    }
		                    else {
		                            temp = new File(path + File.separator + tempList[i]);
		                    }
		                    if (temp.isFile()) {
		                            temp.delete();
		                    }
		                    if (temp.isDirectory()) {
		                            delAllFile(path+"/"+ tempList[i]);//先删除文件夹里面的文件
//		                            delFolder(path+"/"+ tempList[i]);//再删除空文件夹
		                    }
		            }
				}
			}).start();
	    }
}
