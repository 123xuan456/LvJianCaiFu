package com.ryan.slidefragment.generaldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ShenQingActivity extends Activity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv;
	private ImageView sick_title_left_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shen_qing);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("加入联盟");
		sick_title_right_tv.setText("完成");
		sick_title_right_tv.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sick_title_right_tv:
			Intent i = new Intent(ShenQingActivity.this,
					MengYuanJiBieActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.sick_title_left_img:
			finish();
			break;
		default:
			break;
		}
	}
}
