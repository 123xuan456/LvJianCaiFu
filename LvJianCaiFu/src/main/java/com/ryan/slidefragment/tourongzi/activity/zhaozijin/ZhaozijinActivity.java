package com.ryan.slidefragment.tourongzi.activity.zhaozijin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.R.drawable;
import com.ryan.slidefragment.generaldemo.R.id;
import com.ryan.slidefragment.generaldemo.R.layout;
import com.ryan.slidefragment.tourongzi.fragment.GuQuanTouZi;
import com.ryan.slidefragment.tourongzi.fragment.ZhaiQuanTouZi;

public class ZhaozijinActivity extends FragmentActivity implements OnClickListener{

	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	private Button btn1a,btn2a;
	private GuQuanTouZi guquan;
	private ZhaiQuanTouZi zhaiquan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zhaozijin);
		initView();
		setViewPager();
	}

	private CustomViewPager vPager;
	private void setViewPager() {
		vPager=(CustomViewPager) findViewById(R.id.vp01);

		FragmentManager a = getSupportFragmentManager();
		FrgAdapter adapter= new FrgAdapter(a);

		vPager.setAdapter(adapter);

	}
	class FrgAdapter extends FragmentPagerAdapter{
		public FrgAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int pos) {
			if(pos==0){
				guquan=new GuQuanTouZi();
				return guquan;
			}else if(pos==1){
				zhaiquan=new ZhaiQuanTouZi();
				return zhaiquan;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}

	}
	private void initView() {
		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
		title.setText("找资金");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

		btn1a=(Button)findViewById(R.id.btn1);
		btn2a=(Button)findViewById(R.id.btn2);
		btn1a.setOnClickListener(this);
		btn2a.setOnClickListener(this);

	}


	@SuppressLint("ResourceAsColor")
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn1:
				btn1a.setBackgroundResource(R.drawable.huise);
				btn2a.setBackgroundResource(R.drawable.baise);
				changeView(0);
				break;
			case R.id.btn2:
				btn2a.setBackgroundResource(R.drawable.huise);
				btn1a.setBackgroundResource(R.drawable.baise);
				changeView(1);
				break;
			default:
				break;
		}

	}
	//手动设置ViewPager要显示的视图
	private void changeView(int desTab)
	{
		vPager.setCurrentItem(desTab, true);
	}


}
