package com.ryan.slidefragment.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.MainActivity;
import com.ryan.slidefragment.generaldemo.R;

public class yindaoyeAdapter extends PagerAdapter {
	// 界面列表
	private ArrayList<View> pageViews; 
			private Activity activity;

			private static final String SHAREDPREFERENCES_NAME = "first_pref";

			public yindaoyeAdapter(ArrayList<View> pageViews, Activity activity) {
				this.pageViews = pageViews;
				this.activity = activity;
			}

			// 销毁arg1位置的界面
			@Override
			public void destroyItem(View arg0, int arg1, Object arg2) {
				((ViewPager) arg0).removeView(pageViews.get(arg1));
			}

			@Override
			public void finishUpdate(View arg0) {
			}

			// 获得当前界面数
			@Override
			public int getCount() {
				if (pageViews != null) {
					return pageViews.size();
				}
				return 0;
			}

			// 初始化arg1位置的界面
			@Override
			public Object instantiateItem(View arg0, int arg1) {
				((ViewPager) arg0).addView(pageViews.get(arg1), 0);
				if (arg1 == pageViews.size() - 1) {
					TextView mStartWeiboImageButton = (TextView) arg0
							.findViewById(R.id.iv_start);
					mStartWeiboImageButton.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							// 设置已经引导
							setGuided();
							goHome();

						}

					});
				}
				return pageViews.get(arg1);
			}

			private void goHome() {
				// 跳转
				Intent intent = new Intent(activity, MainActivity.class);
				activity.startActivity(intent);
				activity.finish();
			}

			/**
			 * 
			 * method desc：设置已经引导过了，下次启动不用再次引导
			 */
			private void setGuided() {
				SharedPreferences preferences = activity.getSharedPreferences(
						SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
				Editor editor = preferences.edit();
				// 存入数据
				editor.putBoolean("isFirstIn", false);
				// 提交修改
				editor.commit();
			}

			// 判断是否由对象生成界面
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return (arg0 == arg1);
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {
			}

			@Override
			public Parcelable saveState() {
				return null;
			}

			@Override
			public void startUpdate(View arg0) {
			}
}
