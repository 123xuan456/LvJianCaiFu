package com.ryan.slidefragment.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;

/**
 * 
 * @author lyz
 * 
 */
public class UpdateManager {

	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	private ProgressBar mProgress;
	private Dialog mDownloadDialog;
	private Context mContext;
	/* 是否取消更新 */
	private boolean cancelUpdate = false;
	/* 下载保存路径 */
	private String mSavePath;
	/* 保存信息 */
	// HashMap<String, Integer> mHashMap;
	/* 记录进度条数量 */
	private int progress;
	// int serverVersion;
	String apkurl;
	String name;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
				mProgress.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				// 安装文件
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context) {
		this.mContext = context;
	}

	/**
	 * 检测软件更新
	 */
	public void checkUpdate() {
		if (isUpdate()) {
			// 显示提示对话框
			showNoticeDialog();
		} else {
			Toast.makeText(mContext, R.string.soft_update_no, Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * 获取软件版本号
	 */
	private int getVersionCode(Context context) {
		// 获取当前的版本
		int versionCode = 0;
		try {
			// 获取软件版本号，对应清单文件下的versionCode
			versionCode = context.getPackageManager().getPackageInfo(
					"com.ryan.slidefragment.generaldemo", 0).versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 检查软件是否有更新
	 */
	private boolean isUpdate() {
		int versionCode = getVersionCode(mContext);
		String url = Constants.GENGXIN;
		ServerVersion sv = new ServerVersion();
		sv.execute(url);

		System.out.println(versionCode + "bbb");
		System.out.println(BaseApplication.serverVersion + "ccc");
		if (BaseApplication.serverVersion != 0) {

			if (BaseApplication.serverVersion > versionCode) {
				return true;
			}

		} else {

			return true;

		}

		return false;

	}

	/**
	 * 获取服务器上的版本号
	 * 
	 * @author lyz
	 * 
	 */
	class ServerVersion extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuffer sb1 = new StringBuffer();
			try {
				URL url = new URL(params[0]);
				HttpURLConnection huc = (HttpURLConnection) url
						.openConnection();
				InputStream is = huc.getInputStream();
				byte[] b = new byte[1024];
				int len = is.read(b);
				while (len > 0) {
					sb1.append(new String(b, 0, len));
					len = is.read(b);

				}
				is.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sb1.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);

				BaseApplication.serverVersion = j.getInt("version");
				System.out.println(BaseApplication.serverVersion + "aaa");
				// mHashMap.put("serverVersion", serverVersion);
				apkurl = j.getString("apkurl");
				name = j.getString("name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog() {
		// 构造对话框
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(R.string.soft_update_title);// "软件更新"
		builder.setMessage(R.string.soft_update_info);// "检测到新版本，立即更新吗"
		// 更新
		builder.setPositiveButton(R.string.soft_update_updatebtn,
				new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载对话框
						showDownloadDialog();
					}
				});
		// 稍后更新
		builder.setNegativeButton(R.string.soft_update_later,
				new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		Dialog noticeDialog = builder.create();
		noticeDialog.show();
	}

	/**
	 * 显示软件下载对话框
	 */
	private void showDownloadDialog() {
		// 构建软件下载对话框
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(R.string.soft_updating);
		// 给下载对话框增加进度条
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.softupdate_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
		builder.setView(v);
		// 取消更新
		builder.setNegativeButton(R.string.soft_update_cancel,
				new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 设置取消状态
						cancelUpdate = true;
					}
				});
		mDownloadDialog = builder.create();
		mDownloadDialog.show();
		// 下载文件
		downloadApk();
	}

	/**
	 * 下载apk文件
	 */
	private void downloadApk() {
		// 启动新线程下载软件
		new downloadApkThread().start();
	}

	/**
	 * 下载文件线程
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {

			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory()
							+ "/";
					mSavePath = sdpath + "download";
					URL url = new URL(apkurl);
					// Toast.makeText(mContext, apkurl,
					// Toast.LENGTH_SHORT).show();
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath, name);
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[1024];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 取消下载对话框显示
			mDownloadDialog.dismiss();
		}
	}

	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, name);
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
