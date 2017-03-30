package com.ryan.slidefragment.generaldemo;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.base.BaseApplication;

public class Jiarulianemeng extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,textview;
	private ImageView sick_title_left_img;

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sick_title_left_img:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.jiarulianmeng);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		textview = (TextView) findViewById(R.id.textview);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("加入联盟");
		sick_title_right_tv.setVisibility(View.GONE);
		textview.setText(BaseApplication.text);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
}
