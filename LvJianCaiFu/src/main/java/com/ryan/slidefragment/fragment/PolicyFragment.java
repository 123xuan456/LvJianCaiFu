package com.ryan.slidefragment.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.ryan.slidefragment.base.BaseFragment;
import com.ryan.slidefragment.generaldemo.R;
import com.viewpagerindicator.TabPageIndicator;

import java.lang.reflect.Field;

public class PolicyFragment extends BaseFragment {

	private static final String[] TITLE = { "理事长", "副理事长", "常务理事长", "理事", "盟员" };

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_search, null);
		ViewUtils.inject(this, view);

		// ViewPager的adapter
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(
				getChildFragmentManager());
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		// 实例化TabPageIndicator然后设置ViewPager与之关联
		TabPageIndicator indicator = (TabPageIndicator) view
				.findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		// 如果我们要对ViewPager设置监听，用indicator设置就行了
		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int arg0) {
				// Toast.makeText(getActivity().getApplicationContext(),
				// TITLE[arg0], Toast.LENGTH_SHORT).show();

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});

		return view;
	}

	class TabPageIndicatorAdapter extends FragmentPagerAdapter {

		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// 新建一个Fragment来展示ViewPager item的内容，并传递参数

			Fragment f = null;
			switch (position) {
			case 0:
				f = new ChengYuanGongSi_Listview();
				break;
			case 1:
				f = new ChengYuanGongSi_Listview_2();
				break;
			case 2:
				f = new ChengYuanGongSi_Listview_3();
				break;
			case 3:
				f = new ChengYuanGongSi_Listview_4();
				break;
			case 4:
				f = new ChengYuanGongSi_Listview_5();
				break;

			}
			return f;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLE[position % TITLE.length];
		}

		@Override
		public int getCount() {
			return TITLE.length;
		}

	}
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}
}
