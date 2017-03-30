package com.ryan.slidefragment.tourongzi.activity.jurenmai;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.activity.zhaozijin.CustomViewPager;
import com.ryan.slidefragment.tourongzi.adapter.JuRenMaiAdapter;
import com.ryan.slidefragment.tourongzi.ben.JuRenMai_ben;
import com.ryan.slidefragment.tourongzi.fragment.GuQuanTouZi;
import com.ryan.slidefragment.tourongzi.fragment.XiangMuFragment;
import com.ryan.slidefragment.tourongzi.fragment.ZhaiQuanTouZi;
import com.ryan.slidefragment.tourongzi.fragment.ZhiJinFragment;
import com.ryan.slidefragment.utils.JsonUtils;

import java.util.List;

public class JurenmaiActivity extends FragmentActivity implements OnClickListener{

	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	PullToRefreshListView pullToRefresh;
	private  List<JuRenMai_ben.Jurenmai> juren;
	private ZhiJinFragment zijin;
	private XiangMuFragment xiangmu;
	private Button btn1a,btn2a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_jurenmai);
		initView();
	//	getData();//
		setViewPager();
	}

	private CustomViewPager vPager;
	private void setViewPager() {
		vPager=(CustomViewPager) findViewById(R.id.vp01);

		FragmentManager a = getSupportFragmentManager();
		FrgAdapter adapter= new FrgAdapter(a);

		vPager.setAdapter(adapter);

	}

	private void getData() {
		String url= Constants.TOURONGZI_JURENMAI;
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpRequest.HttpMethod.GET,
				url, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String s = responseInfo.result.toString();
						System.out.println("sdasdsadda154498=" + s);
						JSONAnalysis(s);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("哎呀，失败了，没有数据");
					}
				});
	}

	private void JSONAnalysis(String s) {
//		JuRenMai_ben ju= JsonUtils.parser(s, JuRenMai_ben.class);
//		JuRenMai_ben.Date d = ju.getDate();
//		juren = d.getJurenmai();
//
//		JuRenMaiAdapter adapter=new JuRenMaiAdapter(this,juren);
//		pullToRefresh.setAdapter(adapter);
	}

	private void initView() {
		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
		title.setText("聚人脉");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});

		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
		btn1a=(Button)findViewById(R.id.btn1);
		btn2a=(Button)findViewById(R.id.btn2);
		btn1a.setOnClickListener(this);
		btn2a.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zhaozijin, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn1:
				btn1a.setBackgroundResource(R.drawable.bai_xiang);
				btn2a.setBackgroundResource(R.drawable.zijin_hui);
				changeView(0);
				break;
			case R.id.btn2:
				btn1a.setBackgroundResource(R.drawable.hui_xiang);
				btn2a.setBackgroundResource(R.drawable.zijin_bai);
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
	class FrgAdapter extends FragmentPagerAdapter {
		public FrgAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			if(pos==0){
				xiangmu=new XiangMuFragment();
				return xiangmu;
			}else if(pos==1){
				zijin=new ZhiJinFragment();
				return zijin;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}

	}
}
