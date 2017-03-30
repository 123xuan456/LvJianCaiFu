package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class FuwuQianSanActivity extends Activity implements OnClickListener {
	private Dialog dialog;
	private ImageView i;
	private TextView txt_neirong;
	String text_fuwu;
	private Fuwu_Bean fuwu_Bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fuwu_qian_san);
		showdialog1();
	}

	private void showdialog1() {
		LayoutInflater inflaterbb = LayoutInflater.from(this);
		RelativeLayout layout1 = (RelativeLayout) inflaterbb.inflate(
				R.layout.fuwu_zhanlanhui_dialog_l3, null);
		// i = (ImageView) findViewById(R.id.i);
		dialog = new Dialog(FuwuQianSanActivity.this, R.style.service_dialog);
		dialog.setContentView(layout1);
		dialog.setCanceledOnTouchOutside(true);
		dialog.findViewById(R.id.i).setOnClickListener(this);
		txt_neirong = (TextView) dialog.findViewById(R.id.txt_neirong);
		// 将app里的text_fuwu赋值在txt_neirong上
		// txt_neirong.setText(BaseApplication.text_fuwu);
		// BaseApplication.text_fuwu=null;
		dialog.show();
		fuwu(BaseApplication.fuwu);

	}

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
							Toast.makeText(FuwuQianSanActivity.this, "网络异常", Toast.LENGTH_LONG)
									.show();
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
		BaseApplication.text_fuwu = null;
		// getActivity().finish();
		// xieyibean = JsonUtils.parser(agreement, XieYiBean.class);
		// CacheUtils.putString("rows", xieyibean.getRows());
		// // BaseApplication.text = xieyibean.getRows()+"";
		// neirong = xieyibean.getRows()+"";
		// textview.setText(neirong);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.i:
			dialog.dismiss();
			break;
		default:
			break;
		}

	}
}
