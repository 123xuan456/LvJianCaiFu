package com.ryan.slidefragment.generaldemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.utils.SharedPreferencesUtils;

public class ActivityHuanYingYe extends BaseActivity {
	@Override
	public void findView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.huanyingye);
		init();
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@SuppressLint("HandlerLeak")
	private void init() {
		new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (SharedPreferencesUtils.getBoolean(ActivityHuanYingYe.this, "is_first", false)) {
					startActivity(new Intent(ActivityHuanYingYe.this,MainActivity.class));
					finish();
				} else {
					SharedPreferencesUtils.saveBoolean(ActivityHuanYingYe.this, "is_first", true);
					startActivity(new Intent(ActivityHuanYingYe.this,ActivityYinDaoYe.class));
					finish();
				}
			};
		}.sendEmptyMessageDelayed(0, 4000);

	}

}
