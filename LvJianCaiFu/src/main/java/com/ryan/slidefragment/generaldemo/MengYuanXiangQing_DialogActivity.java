package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.MengYuanXiangQingBean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class MengYuanXiangQing_DialogActivity extends Activity implements
		OnClickListener {
	private Dialog dialog;
	private ImageView i;
	private TextView txt_mengyuanjibei_neirong;
	private TextView txt_neirong;
	private MengYuanXiangQingBean myxq_bean;
	String text_mengyuanxiangqing;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_meng_yuan_xiang_qing__dialog);
//		showdialog1();
		txt_neirong = (TextView) findViewById(R.id.txt_neirong);
		Text(BaseApplication.fuwu_mengyuanjiebiexiangqing);
	}

//	private void showdialog1() {
//		LayoutInflater inflaterbb = LayoutInflater.from(this);
//		RelativeLayout layout1 = (RelativeLayout) inflaterbb.inflate(
//				R.layout.mengyuanxiangqing_dialog_l, null);
//		// i = (ImageView) findViewById(R.id.i);
//		dialog = new Dialog(MengYuanXiangQing_DialogActivity.this,
//				R.style.service_dialog);
//		dialog.setContentView(layout1);
//		dialog.setCanceledOnTouchOutside(true);
//		dialog.findViewById(R.id.i).setOnClickListener(this);
//		txt_mengyuanjibei_neirong = (TextView) dialog
//				.findViewById(R.id.txt_mengyuanjibei_neirong);
//		// 将app里的txt_mengyuanjibei_neirong赋值在txt_mengyuanjibei_neirong上
//		txt_mengyuanjibei_neirong.setText(BaseApplication.txt_mengyuanjibei_neirong);
//		BaseApplication.txt_mengyuanjibei_neirong = null;
//		dialog.show();
//
//	}
	// 盟员级别详情加载方法
		private void Text(String r) {
			final HashMap<String, String> ha = new HashMap<String, String>();
			// 给服务器传值
			ha.put("id", r);
			System.out.println("-------------" + r);
			ThreadUtils.newCachedThreadPool().execute(new Runnable() {

				public void run() {
					final String resultFService = HttpClientDao
							.getListHttpClientPost(
									Constants.MENGYUANJIBIEXIANGQING, ha);
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
								Toast.makeText(MengYuanXiangQing_DialogActivity.this, "网络异常",
										Toast.LENGTH_LONG).show();
							}
						});
					}
				}
			});
		}

		// 拿到值
		private void setData(String resultFService) {
			myxq_bean = JsonUtils.parser(resultFService,
					MengYuanXiangQingBean.class);
			CacheUtils.putString("rows", myxq_bean.getRows());
			text_mengyuanxiangqing = myxq_bean.getRows() + "";
			txt_neirong.setText(text_mengyuanxiangqing);
			BaseApplication.fuwu_mengyuanjiebiexiangqing = null;
		}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.i:
			dialog.dismiss();
			break;
		default:
			break;
		}
	}
}
