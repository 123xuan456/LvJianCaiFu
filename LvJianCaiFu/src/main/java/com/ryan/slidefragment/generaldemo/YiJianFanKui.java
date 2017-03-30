package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.UserInfoBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.JsonJudger;

public class YiJianFanKui extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv;
	private ImageView sick_title_left_img;
	private EditText eet;
	private Button button;
	private UserInfoBean userInfo;


	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//返回键
		case R.id.sick_title_left_img:
			finish();
			
			break;
		case R.id.button:

			yijianfankui();
			break;
		default:
			break;
		}
	}

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yijianfankui);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		eet = (EditText) findViewById(R.id.eet);
		button = (Button) findViewById(R.id.button);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("意见反馈");
		sick_title_right_tv.setVisibility(View.GONE);
		button.setOnClickListener(this);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	
	private void yijianfankui(){
		final String content = eet.getText().toString().trim();
		if (TextUtils.isEmpty(content)) {
			Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		final HashMap<String, String> params = new HashMap<String, String>();
		params.put("content", content);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {
			
			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.GETYIJIANFANKUI, params);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code", "200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
//							setDate(resultFService);
							finish();
						}
					});
				}else {
					ThreadUtils.post(new Runnable() {
						public void run() {
							Toast.makeText(YiJianFanKui.this, "错误", Toast.LENGTH_LONG).show();
							eet.setText("");
						}
					});
				}
				
			}
		});
	}
	
//	private void setDate(String resultFService) {
//		userInfo = JsonUtils.parser(resultFService, UserInfoBean.class);
//		this.finish();
//		
//	}
	
}
