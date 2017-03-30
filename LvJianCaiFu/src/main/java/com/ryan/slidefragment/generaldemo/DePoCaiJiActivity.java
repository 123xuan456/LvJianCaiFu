package com.ryan.slidefragment.generaldemo;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.ryan.slidefragment.base.BaseActivity;

public class DePoCaiJiActivity extends BaseActivity implements OnClickListener {
	ImageView caiji_back_img;

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.caiji_back_img:
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
		setContentView(R.layout.activity_de_po_cai_ji);
		caiji_back_img = (ImageView) findViewById(R.id.caiji_back_img);

	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		caiji_back_img.setOnClickListener(this);
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
