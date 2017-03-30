package com.ryan.slidefragment.generaldemo;

import java.io.File;

import android.app.Activity;
import android.os.Environment;

public class ClearCacheCallBack extends Activity {
	/**
	 * 清除app的缓存，由于权限问题，没有办法清除app在系统中的所有缓存，只能清除cache目录和app在外部存储存放临时文件的目录(
	 * 或许是自己定义的目录)
	 */
	public void clearAPPCache(ClearCacheCallBack callback) {
		try {
			// 1、清除APP cache目录下的内容
			File appCacheDir = this.getCacheDir();
			delFolder(appCacheDir.getAbsolutePath());
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())
					|| !Environment.isExternalStorageRemovable()) {
				// 2、清除APP外部cache目录下的内容
				File appExCacheDir = this.getExternalCacheDir();
				if (appExCacheDir != null) {
					delFolder(appExCacheDir.getAbsolutePath());
				}
				// 3、清除APP在外部自定义的缓存目录
				File appExDir = new File("/sdcard/t1.jpg");
				if (appExDir != null) {
					delFolder(appExDir.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
//			callback.clearResult(0);
		}
//		callback.clearResult(1);
	}

//	public void clearResult(int resultCode) {
//	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}
