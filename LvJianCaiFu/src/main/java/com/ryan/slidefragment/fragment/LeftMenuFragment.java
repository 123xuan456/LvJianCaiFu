package com.ryan.slidefragment.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.ryan.slidefragment.TechnologyFragment;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.ErWeiMaBean;
import com.ryan.slidefragment.generaldemo.AboutActivity;
import com.ryan.slidefragment.generaldemo.ActivityZhaoBiao;
import com.ryan.slidefragment.generaldemo.MainActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeftMenuFragment extends Fragment implements OnClickListener {
	private static final String TAG = "LeftMenuFragment";
	FragmentManager fragmentManager = getFragmentManager();
	LinearLayout xiaoxi, lll;
	private SlidingMenu mSlidingMenu;
	private TextView nicheng, textView1, textView2,
	sy,//首页字体
	zbt,//招标投
	zc;//政策
	
	//收藏
	private LinearLayout shoucang;
	private ImageView imageView1;
	// 招标按钮
	private List<Integer> faceIds = new ArrayList<Integer>();
	private List<String> faceMsg = new ArrayList<String>();
	private ImageView left_menu_avatar;
	// 首页按钮
	RelativeLayout left_menu_blog;
	// 招标按钮
	RelativeLayout left_menu_mailto;
	// 论坛按钮
	RelativeLayout left_menu_luntan;
	//产品展示
	RelativeLayout left_menu_canpinzhanshi;
	private ErWeiMaBean ewmb;
	private int denglu = 0;
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.slide_menu_fragment, container,
				false);
		getAndSetViews(view);
		xiaoxi = (LinearLayout) view.findViewById(R.id.xiaoxi);
		xiaoxi.setOnClickListener(this);
		lll = (LinearLayout) view.findViewById(R.id.lll);
		lll.setOnClickListener(this);
		textView1 = (TextView) view.findViewById(R.id.textView1);
		textView2 = (TextView) view.findViewById(R.id.textView2);
		
		sy = (TextView) view.findViewById(R.id.sy);
		zbt = (TextView) view.findViewById(R.id.zbt);
		zc = (TextView) view.findViewById(R.id.zc);
		sy.setTextColor(Color.RED);
		//imageView1 = (ImageView) view.findViewById(R.id.imageView1);
	//	imageView1.setOnClickListener(this);
		// 首页按钮
		left_menu_blog = (RelativeLayout) view
				.findViewById(R.id.left_menu_blog);
		left_menu_blog.setOnClickListener(this);
		// 招标按钮
		left_menu_mailto = (RelativeLayout) view
				.findViewById(R.id.left_menu_mailto);
		left_menu_mailto.setOnClickListener(this);
		// 论坛按钮
		left_menu_luntan = (RelativeLayout) view
				.findViewById(R.id.left_menu_luntan);
		left_menu_luntan.setOnClickListener(this);
		// 头像
		left_menu_avatar = (ImageView) view.findViewById(R.id.left_menu_avatar);
		left_menu_avatar.setOnClickListener(this);
		
		//产品展示
//		left_menu_canpinzhanshi = (RelativeLayout) view.findViewById(R.id.left_menu_canpinzhanshi);
//		left_menu_canpinzhanshi.setOnClickListener(this);
		
		shoucang = (LinearLayout) view.findViewById(R.id.shoucang);
		shoucang.setOnClickListener(this);
		
		return view;
	}

	// 切换背景颜色
	private void changeBackground(int num) {
		switch (num) {
		case 0:
			left_menu_mailto.setBackgroundColor(Color.TRANSPARENT);
			left_menu_luntan.setBackgroundColor(Color.TRANSPARENT);
			break;
		case 1:
			left_menu_blog.setBackgroundColor(Color.TRANSPARENT);
			left_menu_luntan.setBackgroundColor(Color.TRANSPARENT);

			break;
		case 2:
			left_menu_blog.setBackgroundColor(Color.TRANSPARENT);
			left_menu_mailto.setBackgroundColor(Color.TRANSPARENT);
			break;
		}
	}



	private void getAndSetViews(View view) {
		view.findViewById(R.id.left_menu_get_source).setOnClickListener(this);
		view.findViewById(R.id.left_menu_blog).setOnClickListener(this);
	}



	// 点击事件
//	@OnClick(R.id.bt_ok)
	public void onClick(View v) {
		switch (v.getId()) {
		// 关于技术创新
		case R.id.left_menu_get_source:
//			changeBackground(0);
			Intent intent1 = new Intent(this.getActivity(), AboutActivity.class);
			startActivity(intent1);
			break;
		// 招标
		case R.id.left_menu_mailto:
//			changeBackground(1);
			zbt.setTextColor(Color.RED);
			sy.setTextColor(Color.WHITE);
			zc.setTextColor(Color.WHITE);
			Intent intent2 = new Intent(this.getActivity(),
					ActivityZhaoBiao.class);
			startActivity(intent2);
//			 finish();
			

			break;
		// 首页
		case R.id.left_menu_blog:
			sy.setTextColor(Color.RED);
			zbt.setTextColor(Color.WHITE);
			zc.setTextColor(Color.WHITE);
			Intent intent3 = new Intent(this.getActivity(), MainActivity.class);
			startActivity(intent3);
			getActivity().finish();
			break;

		case R.id.left_menu_luntan:
//			changeBackground(2);
			zc.setTextColor(Color.RED);
			zbt.setTextColor(Color.WHITE);
			sy.setTextColor(Color.WHITE);
			Intent intent7 = new Intent(this.getActivity(),TechnologyFragment.class);
			startActivity(intent7);
			break;
			
		}
	}

	private void Erweima(){
		
		final HashMap<String, String> params = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {
			
			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.ERWEIMATUPIAN, params);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code", "200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
							setData(resultFService);
						}
					});
				}else {
					ThreadUtils.post(new Runnable() {
						public void run() {
							Toast.makeText(getActivity(), "服务出错", Toast.LENGTH_LONG).show();
							
						}
					});
				}
				
			}
		});
	}
	// 拿到值
	
		private void setData(String resultFService) {
			ewmb = JsonUtils.parser(resultFService,
					ErWeiMaBean.class);
//			System.out.println(ewmb);
			CacheUtils.putString("rows", ewmb.getImgurl());
			BaseApplication.i_erweima_img = ewmb.getImgurl() + "";
			CacheUtils.putString("rows", ewmb.getName());
			BaseApplication.i_erweima_txt = ewmb.getName() + "";
//			 getActivity().finish();
		}

}
