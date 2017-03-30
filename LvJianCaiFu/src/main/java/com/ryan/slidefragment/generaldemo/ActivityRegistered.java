package com.ryan.slidefragment.generaldemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.XieYiBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class ActivityRegistered extends BaseActivity implements OnClickListener {

	private TextView sick_title_mid_tv, sick_title_right_tv, new_register_btn;
	private ImageView sick_title_left_img;
	private TextView tv_text, xieyi,sick_title_left_tv;
	private EditText phone_edit, register_yzm, new_password_edit,
			new_password_edit1;
	private XieYiBean xieyibean;
	String myphone;
	String datayanzhengma;
	
	
	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zhuce);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);

		phone_edit = (EditText) findViewById(R.id.phone_edit);
		// 倒数时间控件
		tv_text = (TextView) findViewById(R.id.tv_text);
		tv_text.setOnClickListener(this);
		
		sick_title_left_tv =(TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(this);
		// 验证码
		register_yzm = (EditText) findViewById(R.id.register_yzm);
		// 绿色财富协议
		xieyi = (TextView) findViewById(R.id.xieyi);
		// 密码
		new_password_edit = (EditText) findViewById(R.id.new_password_edit);
		// 二次密码
		new_password_edit1 = (EditText) findViewById(R.id.new_password_edit1);

		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);

		new_register_btn = (TextView) findViewById(R.id.new_register_btn);

	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		sick_title_mid_tv.setText("注册");
		sick_title_right_tv.setVisibility(View.GONE);
		sick_title_mid_tv.setText("注册");
		sick_title_right_tv.setVisibility(View.GONE);
		xieyi.setOnClickListener(this);
		sick_title_left_img.setOnClickListener(this);
		new_register_btn.setOnClickListener(this);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	
	

	public void onClick(View v) {
		MyCount myCount = new MyCount(60000, 1000);
		switch (v.getId()) {
		case R.id.tv_text:
			if (checkPhone(phone_edit.getText().toString())) {
				myCount.start();
				yanzhengma();
			}

			break;
		case R.id.sick_title_left_img:
			finish();
			break;
		case R.id.sick_title_left_tv:
			finish();
			break;
		case R.id.xieyi:
//			agreement();
			Intent i = new Intent(this, Lvsecaifuxieyi.class);
			startActivity(i);
			// finish();
			break;

		// finish();
		case R.id.new_register_btn:
			// if (phone_edit==null) {
			// Toast.makeText(ActivityRegistered.this, "手机号不能为空", 0) 
			// .show();
			// }
			// if (register_yzm==null) {
			// Toast.makeText(ActivityRegistered.this, "验证码不能为空", 0)
			// .show();
			// }
			// if (new_password_edit==null) {
			// Toast.makeText(ActivityRegistered.this, "密码不能为空", 0)
			// .show();
			// }
			// if (new_password_edit1==null) {
			// Toast.makeText(ActivityRegistered.this, "新密码不能为空", 0)
			// .show();
			// }
			// 注册接口
			zhuceyonghu(); 

			break;
		default:
			break;
		}
	}

	// 设置电话为11位
	private boolean checkPhone(String phone) {
		if (phone.length() != 11) {
			return false;
		}

		String[] checkStr = new String[] { "13", "14", "15", "16", "17", "18",
				"19" };
		for (String str : checkStr) {
			if (phone.startsWith(str)) {
				return true;
			}
		}
		return false;
	}

	public class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			tv_text.setText("点击获取");
			tv_text.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			tv_text.setClickable(false);
			Date date = new Date(millisUntilFinished);
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			String str = sdf.format(date);
			tv_text.setText(millisUntilFinished / 1000 + "秒后重发");

		}

	}



//	private void agreement() {
//		final HashMap<String, String> aa = new HashMap<String, String>();
//		ThreadUtils.newCachedThreadPool().execute(new Runnable() {
//
//			public void run() {
//				final String agreement = HttpClientDao.getListHttpClientPost(
//						Constants.GETAGREEMENT_URL, aa);
//				boolean judger = JsonJudger
//						.JsonJudger(agreement, "code", "200");
//				System.out.println(agreement);
//				if (judger) {
//					ThreadUtils.post(new Runnable() {
//
//						public void run() {
//							setData(agreement);
//
//						}
//					});
//				}
//			}
//		});
//	}

//	private void setData(String agreement) {
//		boolean judger = JsonJudger.JsonJudger(agreement, "code", "200");
//		if (judger) {
//			xieyibean = JsonUtils.parser(agreement, XieYiBean.class);
//			CacheUtils.putString("rows", xieyibean.getRows());
//			BaseApplication.text = xieyibean.getRows() + "";
//		}
//	}

	// 获取验证码
	private void yanzhengma() {
		myphone = phone_edit.getText().toString().trim();
		final HashMap<String, String> bb = new HashMap<String, String>();
		bb.put("myphone", myphone);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String qwe = HttpClientDao.getListHttpClientPost(
						Constants.YANZHENMA, bb);
				boolean judger = JsonJudger.JsonJudger(qwe, "code", "200");
				System.out.println(qwe);
				if (judger) {
					ThreadUtils.post(new Runnable() {

						public void run() {

							gatData(qwe);
							Toast.makeText(ActivityRegistered.this,
									"成功发送验证码，请注意查收短信！", Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
	}

	private void gatData(String qwe) {
		xieyibean = JsonUtils.parser(qwe, XieYiBean.class);
		CacheUtils.putString("yanzhengma", xieyibean.getYanzhengma());
		// BaseApplication.text = xieyibean.getRows()+"";
		datayanzhengma = xieyibean.getYanzhengma() + "";

	}

	// 注册用户
	private void zhuceyonghu() {
		final HashMap<String, String> cc = new HashMap<String, String>();
		// final String myphone = phone_edit.getText().toString().trim();
		if (TextUtils.isEmpty(myphone)) {
			Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
			return;
		} else {
			myphone = phone_edit.getText().toString().trim();
			cc.put("myphone", myphone);
			// Toast.makeText(this, myphone, Toast.LENGTH_SHORT).show();
		}
		final String yanzhenma = register_yzm.getText().toString().trim();

		if (yanzhenma.equals(datayanzhengma)) {
			cc.put("yanzhenma", yanzhenma);

		} else {
			Toast.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
			return;
		}

		final String pwd = new_password_edit.getText().toString().trim();

		final String pwd2 = new_password_edit1.getText().toString().trim();

		if (pwd.equals(pwd2)) {
			cc.put("pwd", pwd);
		} else {
			Toast.makeText(ActivityRegistered.this, "两次密码不一致，请重新输入！", Toast.LENGTH_LONG).show();
			new_password_edit.setText("");
			new_password_edit1.setText("");
		}

		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String agreement = HttpClientDao.getListHttpClientPost(
						Constants.ZHUCEYONGHU, cc);
				boolean judger = JsonJudger
						.JsonJudger(agreement, "code", "200");
				System.out.println(agreement);
				if (judger) {
					ThreadUtils.post(new Runnable() {

						public void run() {
							Toast.makeText(
									ActivityRegistered.this,
									"注册成功！请在网站上完善更多信息:http://www.greendeep.cn/", Toast.LENGTH_LONG).show();
							finish();
						}
					});
				}
			}
		});
	}

}
