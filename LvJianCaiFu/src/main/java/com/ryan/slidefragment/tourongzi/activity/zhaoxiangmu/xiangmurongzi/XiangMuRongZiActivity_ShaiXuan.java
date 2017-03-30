package com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.xiangmurongzi;

import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.R.id;
import com.ryan.slidefragment.generaldemo.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XiangMuRongZiActivity_ShaiXuan extends Activity {
	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_xiang_mu_rong_zi_activity_1);
		
		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setText("提交");
		
		title.setText("项目融资");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
		
	}

}
