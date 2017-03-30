package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.DianHuaBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.ryan.slidefragment.view.UpdateManager;
import com.volley.JsonJudger;

/**
 * 
 * @author lyz 2016-2-1
 * 设置类包含 版本更新 客服电话 关于公司
 *
 */

public class AboutActivity extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv;
	private ImageView sick_title_left_img;
	private Button btn_submittion, btn_dianhua;
	private DianHuaBean info;
	private Button tv_my_feedback;


	@Override
	public void findView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);

	}
	
	public void initView() {
		// TODO Auto-generated method stub
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		btn_submittion = (Button) findViewById(R.id.btn_submittion);// 版本更新
		btn_dianhua = (Button) findViewById(R.id.btn_dianhuan);// 客服电话
		tv_my_feedback = (Button) findViewById(R.id.tv_my_feedback);//关于公司
		
		
	}

	public void initData() {
		sick_title_mid_tv.setText("设置");
		sick_title_right_tv.setVisibility(View.GONE);
		
		kefudianhua();//获取电话接口
	}

	

	@Override
	public void addListener() {
		btn_submittion.setOnClickListener(this);
		btn_dianhua.setOnClickListener(this);
		sick_title_left_img.setOnClickListener(this);
		tv_my_feedback.setOnClickListener(this);
		sick_title_left_tv.setOnClickListener(this);
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sick_title_left_img:
			finish();
			break;
		case R.id.sick_title_left_tv:
			finish();
			break;
		// 版本更新
		case R.id.btn_submittion:
			UpdateManager manager = new UpdateManager(AboutActivity.this);
			manager.checkUpdate();
			break;
	    //客服电话
		case R.id.btn_dianhuan: 
			Intent intent1 = new Intent(); // 意图: 描述一个动作, 并且携带一些参数.
			intent1.setAction(Intent.ACTION_CALL); // 指定当前意图的动作是打电话
			intent1.setData(Uri.parse("tel:" + haha)); // 设置拨号的信息
			startActivity(intent1); // 开启拨号应用
			break;
		//关于公司
		case R.id.tv_my_feedback:
			Intent ii=new Intent(AboutActivity.this,GongsijianjieActivity.class);
			startActivity(ii);
			break;
		}
	}
	
	/**
	 * 获取客服电话
	 */
	private void kefudianhua() {
		final HashMap<String, String> params = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.GETLIANXIKEFU, params);
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
							Toast.makeText(AboutActivity.this, "服务出错", 0)
									.show();

						}
					});
				}

			}
		});
	}

	private String haha = "";
	private void setData(String resultFService) {
		info = JsonUtils.parser(resultFService, DianHuaBean.class);
		haha = info.dianhua.get(0).name;
		// mMenus.clear();
		// for (Phone item :info.dianhua ) {
		// mMenus.add(item.name);
		// }
		//
		// Iterator i = mMenus.iterator();
		// while (i.hasNext()) {
		// Object next = i.next();
		// String sNext = (String)next;
		// haha = sNext;
		// }
	}

}
