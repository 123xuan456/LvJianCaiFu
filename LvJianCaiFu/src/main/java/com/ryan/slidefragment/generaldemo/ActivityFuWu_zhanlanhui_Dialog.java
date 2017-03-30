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
import com.ryan.slidefragment.domain.Fuwu_Bean;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class ActivityFuWu_zhanlanhui_Dialog extends Activity implements
		OnClickListener {
	private Dialog dialog;
	private ImageView i;
	private TextView txt_neirong;
	private Fuwu_Bean fuwu_Bean;
	String text_fuwu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fuwu_zhanlanhui_dialog_l);
		// showdialog1();
		txt_neirong = (TextView) findViewById(R.id.txt_neirong);
		// 将app里的text_fuwu赋值在txt_neirong上
//		txt_neirong.setText(BaseApplication.text_fuwu);
//		BaseApplication.text_fuwu = null;
		fuwu(BaseApplication.fuwu);
	}

	private void showdialog1() {
		// LayoutInflater inflaterbb = LayoutInflater.from(this);
		// RelativeLayout layout1 = (RelativeLayout) inflaterbb.inflate(
		// R.layout.fuwu_zhanlanhui_dialog_l, null);
		// i = (ImageView) findViewById(R.id.i);
		// dialog = new Dialog(ActivityFuWu_zhanlanhui_Dialog.this,
		// R.style.service_dialog);
		// dialog.setContentView(layout1);
		// dialog.setCanceledOnTouchOutside(true);
		// dialog.findViewById(R.id.i).setOnClickListener(this);
		// txt_neirong = (TextView)findViewById(R.id.txt_neirong);
		// //将app里的text_fuwu赋值在txt_neirong上
		// txt_neirong.setText(BaseApplication.text_fuwu);
		// BaseApplication.text_fuwu=null;
		// dialog.show();

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	// public void onClick(View arg0) {
	// // TODO Auto-generated method stub
	// switch (arg0.getId()) {
	//
	// case R.id.i:
	// dialog.dismiss();
	// break;
	// default:
	// break;
	// }
	// }
	// 判断是否已经登录，登录之后才能进入看介绍
//	public void IfDengLu12() {
//		String s = BaseApplication.name;
//		// Boolean b = Boolean.getBoolean(s);
//		// b = false;
//		if (s != null) {
//			fuwu("12");
//			Intent i8 = new Intent(getActivity(),
//					ActivityFuWu_zhanlanhui_Dialog.class);
//			startActivity(i8);
//		} else {
//			Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
//		}
//	}

	private void fuwu(String s) {

		final HashMap<String, String> ha = new HashMap<String, String>();
		// 给服务器传值
		ha.put("id", s);
		System.out.println("-------------" + s);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.FUWU_ZHANLANHUI, ha);
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
							Toast.makeText(ActivityFuWu_zhanlanhui_Dialog.this, "网络异常", Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
	}

	// 拿到值
	private void setData(String resultFService) {
		fuwu_Bean = JsonUtils.parser(resultFService, Fuwu_Bean.class);
		CacheUtils.putString("rows", fuwu_Bean.getRows());
		text_fuwu = fuwu_Bean.getRows() + "";
		txt_neirong.setText(text_fuwu);
		BaseApplication.fuwu = null;
	}

}
