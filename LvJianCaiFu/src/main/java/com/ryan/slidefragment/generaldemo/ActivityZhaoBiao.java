package com.ryan.slidefragment.generaldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.ryan.slidefragment.fragment.zhaobiao_gonggao;
import com.ryan.slidefragment.fragment.zhaobiao_jieguo;
import com.ryan.slidefragment.fragment.zhaobiao_liucheng;

public class ActivityZhaoBiao extends FragmentActivity {
	RadioGroup rg;
	ViewPager vPager;
	int layout[] = { R.layout.zhaobiao_gonggao, R.layout.zhaobiao_liucheng,
			R.layout.zhaobiao_jieguo };

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zhaobiao);
		vPager = (ViewPager) findViewById(R.id.vp);
		vPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					rg.check(R.id.zhaobiao_gonggao);
					break;
				case 1:
					rg.check(R.id.zhaobiao_liucheng);
					break;
				case 2:
					rg.check(R.id.zhaobiao_jieguo);
					break;
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		rg = (RadioGroup) findViewById(R.id.rg);
		// 点击按键滑动页面
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.zhaobiao_gonggao:
					vPager.setCurrentItem(0);
					break;
				case R.id.zhaobiao_liucheng:
					vPager.setCurrentItem(1);
					break;
				case R.id.zhaobiao_jieguo:
					vPager.setCurrentItem(2);
				}
			}
		});
		vPager.setAdapter(new My(getSupportFragmentManager()));
	}

	class My extends FragmentPagerAdapter {

		public My(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return layout.length;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment f = null;
			switch (arg0) {
			case 0:
				f = new zhaobiao_gonggao();
				break;
			case 1:
				f = new zhaobiao_liucheng();
				break;
			case 2:
				f = new zhaobiao_jieguo();
				break;
			}
			return f;
		}

	}

}
