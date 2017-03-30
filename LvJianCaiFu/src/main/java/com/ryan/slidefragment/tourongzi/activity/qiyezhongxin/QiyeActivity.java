package com.ryan.slidefragment.tourongzi.activity.qiyezhongxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.R;

public class QiyeActivity extends Activity implements OnClickListener{

	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout,linearlayout1,linearlayout2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_qiye);
		initView();
	}

	private void initView() {
		linearlayout1 = (LinearLayout) findViewById(R.id.linearlayout1);
		linearlayout1.setOnClickListener(this);
		linearlayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
		linearlayout2.setOnClickListener(this);
		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
		title.setText("企业");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.linearlayout1:
				Intent mintent = new  Intent(this,ZiliaoxinxiActivity.class);
				startActivity(mintent);

				break;
			case R.id.linearlayout2:
				Intent qintent = new Intent(this,FachutoudiActivity.class);
				startActivity(qintent);
				break;
			default:
				break;
		}
	}

}
