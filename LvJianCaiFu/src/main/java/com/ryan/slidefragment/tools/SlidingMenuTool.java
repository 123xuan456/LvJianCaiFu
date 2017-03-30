package com.ryan.slidefragment.tools;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.anim.CustomAnimation;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.ryan.slidefragment.fragment.LeftMenuFragment;
import com.ryan.slidefragment.generaldemo.R;

public class SlidingMenuTool {
	/**
	 * 
	 * @param fm
	 * @param sl
	 *            下面的activity应该继承此类
	 * @param mainactivity
	 *            ,要添加侧滑menu的activity
	 */
	public static void slidingMenuView(FragmentManager fm,
			SlidingFragmentActivity sl, Activity mainactivity) {

		SlidingMenu sm = sl.getSlidingMenu();
		// 背景
		sm.setBackgroundColor(Color.rgb(37, 37, 37));
		// 阴影
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.slide_menu_shadow);
		// 偏移
		DisplayMetrics metrics = new DisplayMetrics();
		mainactivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		if (metrics.widthPixels > 0) {
			// 资源配置，在不同分辨率，总会有出现别扭的机型，
			// 可以通过屏幕实际宽度，按比例配置偏移，比如：黄金比例
			sm.setBehindOffset((int) (metrics.widthPixels * 0.382));
		} else {
			// 通过资源配置偏移
			sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		}
		// 设置侧滑栏动画
		sm.setBehindCanvasTransformer((new CustomAnimation())
				.getCustomZoomAnimation());

		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setMode(SlidingMenu.LEFT);

		// 设置左侧滑栏内容
		LeftMenuFragment lmf = new LeftMenuFragment();
		sl.setBehindContentView(R.layout.slide_menu_frame);
		fm.beginTransaction().replace(R.id.menu_frame, lmf).commit();

	}

}
