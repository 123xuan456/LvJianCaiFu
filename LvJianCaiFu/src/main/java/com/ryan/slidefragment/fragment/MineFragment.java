package com.ryan.slidefragment.fragment;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.base.BaseFragment;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.XieYiBean;
import com.ryan.slidefragment.generaldemo.ActivityLogin;
import com.ryan.slidefragment.generaldemo.CollectionActivity;
import com.ryan.slidefragment.generaldemo.JiaRuLianMengActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.YiJianFanKui;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

/**
 * 我的模块
 * 
 * @author Administrator
 * 
 */
public class MineFragment extends BaseFragment implements OnClickListener {

	private XieYiBean xieyibean;
	private static final String PATH = null;
	private View view;
	// 登录
	@ViewInject(R.id.lv_login)
	private RelativeLayout lv_login;
	@ViewInject(R.id.lv_login_lv)
	private RelativeLayout lv_login_lv;
	// 退出登录
	@ViewInject(R.id.btn_unlogin)
	private Button btn_unlogin;
	// 我的收藏,加入联盟
	@ViewInject(R.id.iv_mycollect)
	private ImageView iv_mycollect;
	@ViewInject(R.id.iv_connection)
	private ImageView iv_connection;
	// 清除缓存
	@ViewInject(R.id.btn_clear)
	private Button btn_clear;
	@ViewInject(R.id.tv_my_feedback)
	private Button tv_my_feedback;
	// @ViewInject(R.id.tv_size)
	// private TextView tv_size;
	// @ViewInject(R.id.iv_head_logoin)
	// private ImageView iv_head_logoin;
	@ViewInject(R.id.tv_username)
	private TextView tv_username;

	private String userId;
	private HttpClientDao httpClientDao;

	@Override
	public void initData() {
		lv_login.setOnClickListener(this);
		iv_mycollect.setOnClickListener(this);
		// tv_size.setOnClickListener(this);
		// 加入联盟
		iv_connection.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_unlogin.setOnClickListener(this);
		// 意见反馈
		tv_my_feedback.setOnClickListener(this);

	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.mine_fragment, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onResume() {
		// userId = CacheUtils.getString("username", "0");
		// BaseApplication.userID = userId;
		// httpClientDao = new HttpClientDao();
		if (BaseApplication.a == 1) {
			// iv_head_logoin.setVisibility(View.INVISIBLE);
			// iv_login.setVisibility(View.GONE);
			lv_login.setVisibility(View.GONE);
			lv_login_lv.setVisibility(View.VISIBLE);
			btn_unlogin.setVisibility(View.VISIBLE);
			tv_username.setText(BaseApplication.name);
		}
		super.onResume();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lv_login:
			Intent intent = new Intent(this.getActivity(), ActivityLogin.class);
			startActivity(intent);
			break;
			//我的收藏
		case R.id.iv_mycollect:

			IfDengLu();
			break;
		// 加入联盟
		case R.id.iv_connection:
			IfDengLu2();

			break;
		case R.id.btn_unlogin:
			lv_login.setVisibility(View.VISIBLE);
			lv_login_lv.setVisibility(View.GONE);
			btn_unlogin.setVisibility(View.GONE);
			tv_username.setText("登录以后浏览更多精彩内容");
			BaseApplication.a = 0;
			break;
		case R.id.btn_clear:
			// delAllFile(MineFragment.PATH);
			// 1创建Builder
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			// 2内容填充
			builder.setTitle("友情提醒");
			builder.setMessage("确定要清除缓存？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// do something
//							Toast.makeText(getActivity(), "确定按钮被点击了",
//									Toast.LENGTH_LONG).show();
						}
					});
			builder.setNegativeButton("取消", null);
			// 3创建和显示
			builder.create();
			builder.show();
			break;
		case R.id.tv_my_feedback:
			Intent intent4 = new Intent(this.getActivity(), YiJianFanKui.class);
			startActivity(intent4);
			break;
		default:
			break;
		}

	}

	// 判断是否已经登录，登录之后才能进入看介绍
	public void IfDengLu() {
		String s = BaseApplication.name;
		// Boolean b = Boolean.getBoolean(s);
		// b = false;
		if (s != null) {
			Intent intent2 = new Intent(this.getActivity(),
					CollectionActivity.class);
			startActivity(intent2);
		} else {
			Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
		}
	}

	// 判断是否已经登录，登录之后才能进入看介绍
	public void IfDengLu2() {
		String s = BaseApplication.name;
		// Boolean b = Boolean.getBoolean(s);
		// b = false;
		if (s != null) {
			joinunion();

			Intent intent3 = new Intent(this.getActivity(),
					JiaRuLianMengActivity.class);
			startActivity(intent3);
		} else {
			Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
		}
	}

	// 加入接口
	private void joinunion() {
		final HashMap<String, String> bb = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				final String joinunion = HttpClientDao.getListHttpClientPost(
						Constants.GETJOINUNION_URL, bb);
				System.out.print(joinunion);
				ThreadUtils.post(new Runnable() {

					public void run() {
						setData(joinunion);
					}
				});
			}
		});
	}

	private void setData(String joinunion) {
		boolean judger = JsonJudger.JsonJudger(joinunion, "code", "200");
		if (judger) {
			xieyibean = JsonUtils.parser(joinunion, XieYiBean.class);
			CacheUtils.putString("flow", xieyibean.getRows());
			BaseApplication.text = xieyibean.getFlow() + "";
		}
	}

	public void delAllFile(final String path) {

		new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				File file = new File("/sdcard/t1.jpg");
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
					} else {
						temp = new File(path + File.separator + tempList[i]);
					}
					if (temp.isFile()) {
						temp.delete();
					}
					if (temp.isDirectory()) {
						delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
						// delFolder(path+"/"+ tempList[i]);//再删除空文件夹
					}
				}
			}
		}).start();
	}

	// public void setFileTrueSize() {
	// File path1 = new File(MineFragment.PATH);
	// try {
	// Long sumSizeLong = getFolderSize(path1);
	// String sumSizeString = setFileSize(sumSizeLong);
	// tv_size.setText(sumSizeString);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// delAllFile(MineFragment.PATH);
	// tv_size.setText("0 KB");
	//
	// }

	public static long getFolderSize(java.io.File file) throws Exception {
		long size = 0;
		java.io.File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				size = size + getFolderSize(fileList[i]);
			} else {
				size = size + fileList[i].length();
			}
		}
		return size;
	}

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

}
