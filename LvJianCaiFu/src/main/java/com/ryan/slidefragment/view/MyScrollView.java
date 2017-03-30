package com.ryan.slidefragment.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Bolg :http://blog.csdn.net/aaawqqq?viewmode=contents
 * 
 * @author baozi
 * 
 */

public class MyScrollView extends ScrollView {

	// 鎷栧姩鐨勮窛绂�size = 4 鐨勬剰鎬�鍙厑璁告嫋鍔ㄥ睆骞曠殑1/4
	private static final int size = 4;
	private View inner;
	private float y;
	private Rect normal = new Rect();;

	public MyScrollView(Context context) {
		super(context);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (inner == null) {
			return super.onTouchEvent(ev);
		} else {
			commOnTouchEvent(ev);
		}
		return super.onTouchEvent(ev);
	}

	public void commOnTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			y = ev.getY();
			break;
		case MotionEvent.ACTION_UP:
			if (isNeedAnimation()) {
				// Log.v("mlguitar", "will up and animation");
				animation();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			final float preY = y;
			float nowY = ev.getY();
			/**
			 * size=4 琛ㄧず 鎷栧姩鐨勮窛绂讳负灞忓箷鐨勯珮搴︾殑1/4
			 */
			int deltaY = (int) (preY - nowY) / size;
			// 婊氬姩
			// scrollBy(0, deltaY);

			y = nowY;
			// 褰撴粴鍔ㄥ埌鏈�笂鎴栬�鏈�笅鏃跺氨涓嶄細鍐嶆粴鍔紝杩欐椂绉诲姩甯冨眬
			if (isNeedMove()) {
				if (normal.isEmpty()) {
					// 淇濆瓨姝ｅ父鐨勫竷灞�綅缃�
					normal.set(inner.getLeft(), inner.getTop(),
							inner.getRight(), inner.getBottom());
					return;
				}
				int yy = inner.getTop() - deltaY;

				// 绉诲姩甯冨眬
				inner.layout(inner.getLeft(), yy, inner.getRight(),
						inner.getBottom() - deltaY);
			}
			break;
		default:
			break;
		}
	}

	// 寮�惎鍔ㄧ敾绉诲姩

	public void animation() {
		// 寮�惎绉诲姩鍔ㄧ敾
		TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
				normal.top);
		ta.setDuration(200);
		inner.startAnimation(ta);
		// 璁剧疆鍥炲埌姝ｅ父鐨勫竷灞�綅缃�
		inner.layout(normal.left, normal.top, normal.right, normal.bottom);
		normal.setEmpty();
	}

	// 鏄惁闇�寮�惎鍔ㄧ敾
	public boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	// 鏄惁闇�绉诲姩甯冨眬
	public boolean isNeedMove() {
		int offset = inner.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
		if (scrollY == 0 || scrollY == offset) {
			return true;
		}
		return false;
	}

}
// 鈹忊敁銆��銆�攺鈹�
// 鈹忊敍鈹烩攣鈹佲攣鈹涒敾鈹�
// 鈹冦�銆��銆��銆��鈹�銆�
// 鈹冦�銆��鈹併�銆��鈹�
// 鈹冦�鈹斥敍銆�敆鈹炽�鈹�
// 鈹冦�銆��銆��銆��鈹�
// 鈹冦�銆��鈹汇�銆��鈹�
// 鈹冦�銆��銆��銆��鈹�
// 鈹椻攣鈹撱�銆��鈹忊攣鈹�
// 鈹冦�銆��鈹�绁炲吔淇濅綉銆��銆��銆��銆��
// 鈹冦�銆��鈹�浠ｇ爜鏃燘UG锛�
// 鈹冦�銆��鈹椻攣鈹佲攣鈹�
// 鈹冦�銆��銆��銆��鈹ｂ敁
// 鈹冦�銆��銆��銆��鈹忊敍
// 鈹椻敁鈹撯攺鈹佲敵鈹撯攺鈹�
// 鈹冣敨鈹�鈹冣敨鈹�
// 鈹椻敾鈹涖�鈹椻敾鈹�
