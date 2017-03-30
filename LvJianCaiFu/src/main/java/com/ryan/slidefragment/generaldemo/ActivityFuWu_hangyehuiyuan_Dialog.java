package com.ryan.slidefragment.generaldemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ryan.slidefragment.base.BaseApplication;

public class ActivityFuWu_hangyehuiyuan_Dialog extends Activity implements
		OnClickListener {
	private Dialog dialog;
	private ImageView i;
	private TextView txt_neirong1;
	private Button b_xiayibu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fuwu_zhanlanhui_dialog);
		showdialog1();
	}

	private void showdialog1() {
		LayoutInflater inflaterbb = LayoutInflater.from(this);
		RelativeLayout layout1 = (RelativeLayout) inflaterbb.inflate(
				R.layout.fuwu_hangyehuiyuan_dialog_l, null);
		// i = (ImageView) findViewById(R.id.i);
		dialog = new Dialog(ActivityFuWu_hangyehuiyuan_Dialog.this,
				R.style.service_dialog);
		dialog.setContentView(layout1);
		dialog.setCanceledOnTouchOutside(true);
		dialog.findViewById(R.id.i).setOnClickListener(this);
		b_xiayibu = (Button) dialog.findViewById(R.id.b_xiayibu);
		b_xiayibu.setOnClickListener(this);
		txt_neirong1 = (TextView) dialog.findViewById(R.id.txt_neirong1);
		// 将app里的text_fuwu赋值在txt_neirong1上
		txt_neirong1.setText(BaseApplication.txt_neirong1);
		BaseApplication.txt_neirong1=null;
		dialog.show();

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.b_xiayibu:
			Intent i = new Intent(ActivityFuWu_hangyehuiyuan_Dialog.this, JiaRuLianMengActivity.class);
			startActivity(i);
			break;
		case R.id.i:
			dialog.dismiss();
			break;
		default:
			break;
		}
	}

}
