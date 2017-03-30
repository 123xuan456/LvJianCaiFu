package com.ryan.slidefragment.tourongzi;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.domain.ChengYuan_CommonalityBean;
import com.ryan.slidefragment.tourongzi.activity.jurenmai.JurenmaiActivity;
import com.ryan.slidefragment.generaldemo.PingtaiActivity;
import com.ryan.slidefragment.tourongzi.activity.qiyezhongxin.QiyeActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.tourongzi.activity.chenggonganli.CengGongAnLiActivity_Tou;
import com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.ZhaoxiangmuActivity;
import com.ryan.slidefragment.tourongzi.activity.zhaozijin.ZhaozijinActivity;
import com.ryan.slidefragment.tourongzi.ben.Dialog_ben;
import com.ryan.slidefragment.utils.JsonUtils;

@SuppressLint("InflateParams")
public class TouRongZiActivity extends Activity implements OnClickListener{

	private TextView title,sick_title_right_tv;//标题
	private LinearLayout touRongZi_ll,jurenmai1,pingtaifuwu1,sick_titel_left_layout;
	private Button zhaozijin,zhaoxiangmu,chenggonganli,qiyezhongxin,button4,button3;
	private Dialog dialog;
	private String id;//将id传递给服务器
	private TextView textView1;
	private TextView dialog_text;
	private Dialog_ben diaben;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tou_rong_zi);
		initView();
		id = BaseApplication.userID;
		getData();
//		dialog();



	}
	RelativeLayout layout;
	private void dialog() {
		LayoutInflater inflaterDl = LayoutInflater.from(this);

		layout = (RelativeLayout)inflaterDl.inflate(R.layout.dialog_1, null);
		dialog = new AlertDialog.Builder(TouRongZiActivity.this).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);// 设置点击屏幕Dialog不消失
		dialog.setCancelable(false);//设置点击返回键不取消
		dialog.setCancelable(false);

	//	getData();
		dialog_text = (TextView) layout.findViewById(R.id.dialog_text);

		textView1 = (TextView) layout.findViewById(R.id.textView1);
		textView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("wang", diaben.getWang());
				Intent i=new Intent(getApplicationContext(),WebActivity.class);
				i.putExtras(mBundle);
				startActivity(i);
			}
		});
		ImageButton btnClose = (ImageButton) layout.findViewById(R.id.dialog_close);
		btnClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

	}



	private void getData() {
		String url = com.ryan.slidefragment.options.Constants.TOURONGZIDIALOG+id;
		RequestParams params=new RequestParams();
		params.addQueryStringParameter("id",id);
		HttpUtils http=new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST ,
				url,
				params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result.toString() ;
						try {
							JSONObject obj=new JSONObject(result);
							diaben= JsonUtils.parser(result,
									Dialog_ben.class);
						//	String biao = obj.getString("biao");
						//	String wang = obj.getString("wang");
							String wanshan = obj.getString("wanshan");

							if(wanshan.equals("yes")){
								return;
							}else if(wanshan.equals("no")){
								dialog();
								dialog_text.setText(diaben.getBiao());
								textView1.setText(diaben.getWang());
							}


						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});

	}

	private void initView() {
		title = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
		title.setText("投融资");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		touRongZi_ll=(LinearLayout) findViewById(R.id.ll);
		touRongZi_ll.getBackground().setAlpha(50);//设置透明度

		pingtaifuwu1=(LinearLayout) findViewById(R.id.p1);//平台服务
		jurenmai1=(LinearLayout) findViewById(R.id.j1);//聚人脉
		button4=(Button) findViewById(R.id.pingtaifuwu);//平台服务
		button3=(Button) findViewById(R.id.jurenmai);//聚人脉
		zhaozijin=(Button) findViewById(R.id.zhaozijin);//找资金
		zhaoxiangmu=(Button) findViewById(R.id.zhaoxiangmu);//找项目
		chenggonganli=(Button) findViewById(R.id.chenggonganli);//成功案例
		qiyezhongxin=(Button) findViewById(R.id.qiyezhongxin);//企业中心
		sick_titel_left_layout.setOnClickListener(this);
//		pingtaifuwu.setOnClickListener(this);
//		jurenmai.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		zhaozijin.setOnClickListener(this);
		zhaoxiangmu.setOnClickListener(this);
		chenggonganli.setOnClickListener(this);
		qiyezhongxin.setOnClickListener(this);


	}


	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.zhaozijin:
				Intent i=new Intent(TouRongZiActivity.this,ZhaozijinActivity.class);
				startActivity(i);
				break;
			case R.id.pingtaifuwu:
				Toast.makeText(getApplicationContext(),"暂未开放",Toast.LENGTH_LONG).show();
				//Intent i1=new Intent(TouRongZiActivity.this,PingtaiActivity.class);
				//startActivity(i1);
				break;
			case R.id.jurenmai:
				Intent i2=new Intent(TouRongZiActivity.this,JurenmaiActivity.class);
				startActivity(i2);
				break;
			case R.id.zhaoxiangmu:
				Intent i3=new Intent(TouRongZiActivity.this,ZhaoxiangmuActivity.class);
				startActivity(i3);
				break;
			case R.id.chenggonganli:
				Intent i4=new Intent(TouRongZiActivity.this, CengGongAnLiActivity_Tou.class);
				startActivity(i4);
				break;
			case R.id.qiyezhongxin:
				Intent i5=new Intent(TouRongZiActivity.this,QiyeActivity.class);
				startActivity(i5);
				break;
			case R.id.sick_titel_left_layout:
				finish();
				break;

			default:
				break;
		}
	}

}
