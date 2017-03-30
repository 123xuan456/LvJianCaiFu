package com.ryan.slidefragment.generaldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.domain.JiaRuLianMengXieYiBean;

public class MengYuanJiBieActivity extends Activity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv, t_xiangqing0,
			t_xiangqing1, t_xiangqing2, t_xiangqing3;
	private ImageView sick_title_left_img;
	private RadioButton fulishizhang, changwulishi, lishi, mengyuan;
	private RadioGroup rGroup;
	
	private JiaRuLianMengXieYiBean jrxy_bean;
	private Button b_xieyi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_meng_yuan_ji_bie);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("盟员级别");
		sick_title_right_tv.setVisibility(View.GONE);

		b_xieyi=(Button)findViewById(R.id.b_xieyi);
		b_xieyi.setOnClickListener(this);
		t_xiangqing0 = (TextView) findViewById(R.id.t_xiangqing0);
		t_xiangqing0.setOnClickListener(this);
		t_xiangqing1 = (TextView) findViewById(R.id.t_xiangqing1);
		t_xiangqing1.setOnClickListener(this);
		t_xiangqing2 = (TextView) findViewById(R.id.t_xiangqing2);
		t_xiangqing2.setOnClickListener(this);
		t_xiangqing3 = (TextView) findViewById(R.id.t_xiangqing3);
		t_xiangqing3.setOnClickListener(this);
		rGroup = (RadioGroup) findViewById(R.id.rGroup);

		rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == R.id.fulishizhang) {
					rGroup.check(R.id.fulishizhang);
				}
			}
		});

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sick_title_left_img:
			finish();
			break;
		case R.id.t_xiangqing0:
//			Text("0");
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "";
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "3";
			Intent i0 = new Intent(MengYuanJiBieActivity.this,
					MengYuanXiangQing_DialogActivity.class);
			startActivity(i0);
			break;
		case R.id.t_xiangqing1:
//			Text("1");
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "";
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "5";
			Intent i1 = new Intent(MengYuanJiBieActivity.this,
					MengYuanXiangQing_DialogActivity.class);
			startActivity(i1);
			break;
		case R.id.t_xiangqing2:
//			Text("2");
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "";
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "1";
			Intent i2 = new Intent(MengYuanJiBieActivity.this,
					MengYuanXiangQing_DialogActivity.class);
			startActivity(i2);
			break;
		case R.id.t_xiangqing3:
//			Text("3");
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "";
			BaseApplication.fuwu_mengyuanjiebiexiangqing = "2";
			Intent i3 = new Intent(MengYuanJiBieActivity.this,
					MengYuanXiangQing_DialogActivity.class);
			startActivity(i3);
			break;
		case R.id.b_xieyi:
//			Jiarulianmengxieyi();
			Intent i4=new Intent(MengYuanJiBieActivity.this, JiangRuLiangMengXieYiActivity.class);
			startActivity(i4);
		default:
			break;
		}
	}

	

}
