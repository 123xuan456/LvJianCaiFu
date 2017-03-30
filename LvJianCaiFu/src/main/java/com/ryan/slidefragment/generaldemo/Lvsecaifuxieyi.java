package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.XieYiBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class Lvsecaifuxieyi extends BaseActivity implements OnClickListener {
	LinearLayout ll;
	TextView sick_title_mid_tv, sick_title_right_tv,textview;
	ImageView sick_title_left_img;
	XieYiBean xieyibean;
	String neirong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		if (savedInstanceState == null) {
//			ll.getViewTreeObserver().addOnPreDrawListener(
//					new OnPreDrawListener() {
//
//						public boolean onPreDraw() {
//							// TODO Auto-generated method stub
//							ll.getViewTreeObserver().removeOnPreDrawListener(
//									this);
//							startRootAnimation();
//							return true;
//						}
//					});
//		}
		
	}

//	@SuppressLint("NewApi")
//	protected void startRootAnimation() {
//		ll.setScaleX(0.1f);
//		ll.setPivotY(ll.getY() + ll.getHeight() / 2);
//		ll.animate().scaleX(1).setDuration(1000)
//				.setInterpolator(new AccelerateInterpolator()).start();
//	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sick_title_left_img:
//			Intent i = new Intent(this, ActivityRegistered.class);
//			startActivity(i);
			finish();
			break;

		default:
			break;
		}
	}

	// 点击系统返回按键，返回到上一界面
	public void onBackPressed() {
		Intent i = new Intent(this, ActivityRegistered.class);
		startActivity(i);
		finish();
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lvsecaifuxieyi);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		ll = (LinearLayout) findViewById(R.id.ll);
		textview = (TextView) findViewById(R.id.textview);
		
	}

	
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		sick_title_mid_tv.setText("绿建中心协议");
		sick_title_right_tv.setText(null);
		sick_title_left_img.setOnClickListener(this);
//		textview.setText(BaseApplication.text);
		agreement();
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
	

	}

	// 处理数据
	@Override
	public void initData() {
	}
	
	private void agreement() {
		final HashMap<String, String> aa = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {
			
			public void run() {
				final String agreement = HttpClientDao.getListHttpClientPost(Constants.GETAGREEMENT_URL,aa);
				boolean judger = JsonJudger.JsonJudger(agreement, "code", "200");
				System.out.println(agreement);
				if (judger) {
					ThreadUtils.post(new Runnable() {
						
						public void run() {
							setData(agreement);

						}
					});
				}
			}
		});
	}
	private void setData(String agreement) {
		boolean judger = JsonJudger.JsonJudger(agreement, "code", "200");
		if (judger) {
			xieyibean = JsonUtils.parser(agreement, XieYiBean.class);
			CacheUtils.putString("rows", xieyibean.getRows());
//			BaseApplication.text = xieyibean.getRows()+"";
			neirong = xieyibean.getRows()+"";
			textview.setText(neirong);
		}
	}

	
}
