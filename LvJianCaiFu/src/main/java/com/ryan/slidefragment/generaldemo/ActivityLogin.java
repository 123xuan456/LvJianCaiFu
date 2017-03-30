package com.ryan.slidefragment.generaldemo;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.UserInfoBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ActivityLogin extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv;
	private TextView  tv, tv1;
	private static EditText register_yzm;
	private EditText phone_edit;
	private ImageView sick_title_left_img;
	private UserInfoBean userInfo; // 用户Id
	private Button new_register_btn_login; // 用户Id
	
	// 登录按钮
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_register_btn_login:
			login();
			break;
		case R.id.sick_title_left_img:
			finish();
			break;
			
		case R.id.sick_title_left_tv:
			finish();
			break;
		case R.id.tv:
			Intent intent = new Intent(this, ActivityRegistered.class);
			startActivity(intent);
			break;
		case R.id.tv1:
			Intent intent1 = new Intent(this, ActivityForget.class);
			startActivity(intent1);
		}
	}

	private void login() {
		final String name = phone_edit.getText().toString().trim();
		final String newPwd = register_yzm.getText().toString().trim();
		String m=md5(newPwd);
		System.out.println(m+"00000000");
		// String deviceid = DeviceUtil.getMacAddress(this);
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(newPwd)) {
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		final HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("pwd", newPwd);
		params.put("m", m);
		// params.put("deviceid", deviceid);
		System.out.println("-------------" + name);
		System.out.println("-------------" + params.get("pwd"));
		// System .out.println("-------------" + params.get("deviceid"));
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.GETLOGINMESSAGE_URL,
								params);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code",
						"200");
				if (judger) {
					BaseApplication.name = name;
					ThreadUtils.post(new Runnable() {
						public void run() {
							setData(resultFService);
							BaseApplication.a = 1;
						}
					});

				} else {
					ThreadUtils.post(new Runnable() {

						public void run() {
							Toast.makeText(ActivityLogin.this, "用户和密码不正确", Toast.LENGTH_LONG)
									.show();
						}
					});
				}
			}
		});
	}

	private void setData(String resultFService) {
		userInfo = JsonUtils.parser(resultFService, UserInfoBean.class);
		CacheUtils.putString("uid", userInfo.getUserID());
		String userID = userInfo.getUserID() + "";
		System.out.println("用户id="+userID);
		BaseApplication.userID = userID;
		this.finish();	
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		phone_edit = (EditText) findViewById(R.id.phone_edit_login);
		register_yzm = (EditText) findViewById(R.id.register_yzm_login);
		// 注册
		tv = (TextView) findViewById(R.id.tv);

		// 返回键
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);

		new_register_btn_login = (Button) findViewById(R.id.new_register_btn_login);

		// 忘记密码
		tv1 = (TextView) findViewById(R.id.tv1);

	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		tv.setOnClickListener(this);
		sick_title_left_img.setOnClickListener(this);
		new_register_btn_login.setOnClickListener(this);
		sick_title_mid_tv.setText("用户登录");
		sick_title_right_tv.setVisibility(View.GONE);
		tv1.setOnClickListener(this);
		sick_title_left_tv.setOnClickListener(this);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	//md5密码加密方法
	public static String md5(String string) {
	    byte[] hash;
	    try {
	        hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Huh, MD5 should be supported?", e);
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
	    }

	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if ((b & 0xFF) < 0x10) hex.append("0");
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    
	    return hex.toString();
	}
}
