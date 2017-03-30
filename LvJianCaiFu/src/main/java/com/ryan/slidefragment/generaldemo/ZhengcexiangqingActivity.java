package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.ShouYeXinWenXiangQingBean;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class ZhengcexiangqingActivity extends Activity implements
		OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv, xinwen_biaoti,
			xinwen_neirong, xinwen_liulanliang,sick_title_left_tv;
	private ImageView sick_title_left_img;
	private ShouYeXinWenXiangQingBean syxwxq;
	public String ww, ee, qq;
	String ids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_limengxinwengengduozhengwen);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(this);
		xinwen_liulanliang = (TextView) findViewById(R.id.xinwen_liulanliang);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		xinwen_biaoti = (TextView) findViewById(R.id.xinwen_biaoti);
		xinwen_neirong = (TextView) findViewById(R.id.xinwen_neirong);
		xinwen_biaoti.setOnClickListener(this);
		xinwen_neirong.setOnClickListener(this);
		sick_title_left_img.setOnClickListener(this);
		Person mPerson = (Person) getIntent().getSerializableExtra("pr");
		ids = (String) mPerson.getName();
		System.out.println(mPerson.getName() + "woooooooooooooooooo");
		sick_title_mid_tv.setText("正文");
		sick_title_right_tv.setVisibility(View.GONE);
		shouyexinwenxiangqing(BaseApplication.myId);
		BaseApplication.myId = null;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sick_title_left_img:
			finish();
			break;
		case R.id.sick_title_left_tv:
			finish();
			break;
		default:
			break;
		}
	}

	/** 接口 */
	public void shouyexinwenxiangqing(String s) {

		final HashMap<String, String> ha = new HashMap<String, String>();
		// 给服务器传值
		ha.put("id", ids);
		System.out.println("-------------" + s);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.ZHENGCEXIANGQING, ha);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code",
						"200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
							setData(resultFService);
						}
					});

				} else {
					ThreadUtils.post(new Runnable() {

						public void run() {
							Toast.makeText(ZhengcexiangqingActivity.this,
									"网络异常", Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
	}

	private void setData(String resultFService) {
		syxwxq = JsonUtils.parser(resultFService,
				ShouYeXinWenXiangQingBean.class);
		CacheUtils.putString("rows", syxwxq.getContent());
		ww = syxwxq.getTitle() + "";
		ee = syxwxq.getContent() + "";
		qq = syxwxq.getClick() + "";
		xinwen_biaoti.setText(ww);
		xinwen_neirong.setText(ee);
		xinwen_liulanliang.setText(qq);
		// BaseApplication.xinwen_biaoti = syxwxq.getTitle() + "";
		// String ww = syxwxq.getTitle() + "";
		// app.settit(ww);
		// BaseApplication.xinwen_neirong = syxwxq.getContent() + "";
		// getActivity().finish();
	}
}
