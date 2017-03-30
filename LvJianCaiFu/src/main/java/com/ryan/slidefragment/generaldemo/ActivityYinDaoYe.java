package com.ryan.slidefragment.generaldemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class ActivityYinDaoYe extends Activity implements View.OnClickListener {

	private ImageView iv_experience;
	private ViewPager view_pager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guide);
		iv_experience = (ImageView) findViewById(R.id.iv_experience);
		view_pager = (ViewPager) findViewById(R.id.view_pager);

		view_pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position==3) {
					iv_experience.setVisibility(View.VISIBLE);
				} else {
					iv_experience.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});


		List<View> lists = new ArrayList<View>();

		ImageView imageview1 = new ImageView(this);
		ImageView imageview2 = new ImageView(this);
		ImageView imageview3 = new ImageView(this);
		ImageView imageview4 = new ImageView(this);

		imageview1.setBackgroundResource(R.drawable.welcom_1);
		imageview2.setBackgroundResource(R.drawable.welcom_2);
		imageview3.setBackgroundResource(R.drawable.welcom_3);
		imageview4.setBackgroundResource(R.drawable.welcom_4);

		lists.add(imageview1);
		lists.add(imageview2);
		lists.add(imageview3);
		lists.add(imageview4);

		ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(lists);
		view_pager.setAdapter(pagerAdapter);

		iv_experience.setOnClickListener(this);
	}

	private class ViewPagerAdapter extends PagerAdapter {
		private List<View> pages;
		public ViewPagerAdapter(List<View> lists){
			this.pages = lists;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(pages.get(position));
			return pages.get(position);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(pages.get(position));
		}
	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(ActivityYinDaoYe.this, MainActivity.class));
		finish();

	}

}
