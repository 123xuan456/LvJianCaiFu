package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.GuanYuGongSiBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

/**
 * 
 * @author lyz 2016-2-1
 * 关于公司内容（接口）
 */
public class GongsijianjieActivity extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv,neirong;
	private ImageView sick_title_left_img;
	private GuanYuGongSiBean gygsb;
	
	
	@SuppressLint("HandlerLeak") 
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle b = msg.getData();
			String mname = b.getString("aboutcompany");
			neirong.setText(mname);
		}
	};


	@Override
	public void findView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gongsijianjie);
		
	}
	@Override
	public void initView() {
		neirong = (TextView) findViewById(R.id.neirong);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_mid_tv.setText("关于公司");
		sick_title_right_tv.setVisibility(View.GONE);
		
	}
	@Override
	public void addListener() {
		sick_title_left_img.setOnClickListener(this);
		sick_title_left_tv.setOnClickListener(this);
	}
	@Override
	public void initData() {
		guanyugongsi();
		
	}
	
	public void onClick(View v) {
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
	
	/**
	 * 接口，获取公司信息
	 */
	private void guanyugongsi() {
		final HashMap<String, String> params = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.GUANYUGONGSI, params);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code",
						"200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
							gygsb = JsonUtils.parser(resultFService,
									GuanYuGongSiBean.class);
							CacheUtils.putString("aboutcompany", gygsb.getContent());

							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("aboutcompany", gygsb.getContent() + "");
							msg.setData(b);
							mHandler.sendMessage(msg);
						}
					});

				} else {
					ThreadUtils.post(new Runnable() {
						public void run() {
							Toast.makeText(GongsijianjieActivity.this, "网络连接错误", Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
	}
}
