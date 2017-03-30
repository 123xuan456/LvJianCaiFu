package com.ryan.slidefragment.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lesogo.cu.custom.ScaleView.HackyViewPager;
import com.ryan.slidefragment.generaldemo.R;

/**
 * 显示大图界面
 * @author lyz
 *
 */
public class ShowBigPictrue extends FragmentActivity {

	private HackyViewPager viewPager;
	private int[] resId = { R.drawable.a2, R.drawable.a1,
			R.drawable.a3, R.drawable.a4,
			R.drawable.a5, R.drawable.a6 };
	/**得到上一个界面点击图片的位置*/
	private int position=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_big_pictrue_a);
		Intent intent=getIntent();
		position=intent.getIntExtra("position", 0);
		initViewPager();
	}
	
private void initViewPager(){
		
		viewPager = (HackyViewPager) findViewById(R.id.viewPager_show_bigPic);
		ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		//跳转到第几个界面
		viewPager.setCurrentItem(position);
		
	}
	
	private class ViewPagerAdapter extends FragmentStatePagerAdapter{

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			int show_resId=resId[position];
			return new PictrueFragment(show_resId);
		}

		@Override
		public int getCount() {
			return resId.length;
		}

		
	}

}
