package com.ryan.slidefragment.generaldemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.slidefragment.base.BaseActivity;

public class ActivityForget extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,
			forget_password_activity_btn_check,sick_title_left_tv;
	private ImageView sick_title_left_img;
	private EditText phone_edit;

	public void onClick(View v) {
		MyCount myCount = new MyCount(60000, 1000);
		switch (v.getId()) {
		case R.id.sick_title_left_img:
			finish();

			break;
			
		case R.id.sick_title_left_tv:
			finish();
			break;
		case R.id.forget_password_activity_btn_check:
			if (checkPhone(phone_edit.getText().toString())) {
				myCount.start();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forget);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		// 倒数时间控件
		forget_password_activity_btn_check = (TextView) findViewById(R.id.forget_password_activity_btn_check);
		forget_password_activity_btn_check.setOnClickListener(this);
		phone_edit = (EditText) findViewById(R.id.phone_edit);
		
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		sick_title_mid_tv.setText("忘记密码");
		sick_title_right_tv.setVisibility(View.GONE);
		sick_title_left_img.setOnClickListener(this);
		sick_title_left_tv.setOnClickListener(this);
	}

	// 设置电话为11位
	private boolean checkPhone(String phone) {
		if (phone.length() != 11) {
			return false;
		}

		String[] checkStr = new String[] { "13", "14", "15", "16", "17", "18",
				"19" };
		for (String str : checkStr) {
			if (phone.startsWith(str)) {
				return true;
			}
		}
		return false;
	}

	public class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			forget_password_activity_btn_check.setText("点击获取");
			forget_password_activity_btn_check.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			forget_password_activity_btn_check.setClickable(false);
			Date date = new Date(millisUntilFinished);
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			String str = sdf.format(date);
			forget_password_activity_btn_check.setText(millisUntilFinished
					/ 1000 + "秒后重发");

		}

	}
}
