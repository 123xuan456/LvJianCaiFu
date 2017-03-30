package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.ChengYuanGongSiXiangQingBean;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class GongSiActivity extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,
			t_gongsijiejian_juti, t_chakangengduo, t_gengduochanpin,
			gongsi_title, gongsi_chengyuanjibie, gongsi_guimo, gongsi_xingzhi,
			gongsi_dianhua,sick_title_left_tv;
	// new_register_btn;
	private ImageView sick_title_left_img, i_chanpin1, i_chanpin2, i_chanpin3,
			gongsilog_img;
	private ChengYuanGongSiXiangQingBean syxwxq;
	public String ww, ee, aa, bb, cc, dd, rr, ff;
	private String id;
	private static ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	public void findView() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gongsi);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(this);
		// a57 = (ImageView) findViewById(R.id.a57);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		t_gongsijiejian_juti = (TextView) findViewById(R.id.t_gongsijiejian_juti);
//		t_chakangengduo = (TextView) findViewById(R.id.t_chakangengduo);
		// t_gengduochanpin=(TextView)findViewById(R.id.t_gengduochanpin);
		// t_gengduochanpin.setOnClickListener(this);
		// i_chanpin1=(ImageView)findViewById(R.id.i_chanpin1);
		// i_chanpin2=(ImageView)findViewById(R.id.i_chanpin2);
		// i_chanpin3=(ImageView)findViewById(R.id.i_chanpin3);
		// i_chanpin1.setOnClickListener(this);
		// i_chanpin2.setOnClickListener(this);
		// i_chanpin3.setOnClickListener(this);
		gongsi_title = (TextView) findViewById(R.id.gongsi_title);
		gongsi_chengyuanjibie = (TextView) findViewById(R.id.gongsi_chengyuanjibie);
		gongsi_title.setOnClickListener(this);
		gongsi_chengyuanjibie.setOnClickListener(this);
		gongsi_guimo = (TextView) findViewById(R.id.gongsi_guimo);
		gongsi_guimo.setOnClickListener(this);
		gongsi_xingzhi = (TextView) findViewById(R.id.gongsi_xingzhi);
		gongsi_xingzhi.setOnClickListener(this);
		gongsi_dianhua = (TextView) findViewById(R.id.gongsi_dianhua);
		gongsi_dianhua.setOnClickListener(this);
		gongsilog_img = (ImageView) findViewById(R.id.gongsilog_img);
		gongsilog_img.setOnClickListener(this);

		sick_title_left_img.setOnClickListener(this);
//		Person mPerson = (Person) getIntent().getSerializableExtra("pr");
//		ids = (String) mPerson.getName();
		Bundle mBundle = new Bundle();
		mBundle = this.getIntent().getExtras();
		id = (mBundle.getInt("id"))+"";
		System.out.println("wweerr" + id);


		sick_title_mid_tv.setText("正文");
		sick_title_right_tv.setVisibility(View.GONE);
		shouyexinwenxiangqing();
//		BaseApplication.myId = null;
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
			// case R.id.a57:
			// Intent i = new Intent(GongSiActivity.this, ActivityGongsi.class);
			// startActivity(i);
			// break;
			case R.id.sick_title_left_img:
				finish();
				break;
			case R.id.sick_title_left_tv:
				finish();
				break;
//		case R.id.t_chakangengduo:
//			// 隐藏多条内容，点击展示
//			if (t_gongsijiejian_juti.getLineCount() == 10) {
//				t_gongsijiejian_juti.setMaxLines(150);
//				t_chakangengduo.setText("收回");
//			} else {
//				t_gongsijiejian_juti.setLines(10);
//				t_chakangengduo.setText("查看更多");
//			}
//			break;
			// case R.id.t_gengduochanpin:
			// Intent i=new Intent(GongSiActivity.this,
			// Gongsi_Chanpin_ListActivity.class);
			// startActivity(i);
			// break;
			// case R.id.i_chanpin1:
			// Intent i1=new Intent(GongSiActivity.this, BabyActivity.class);
			// startActivity(i1);
			// break;
			// case R.id.i_chanpin2:
			// Intent i2=new Intent(GongSiActivity.this, BabyActivity.class);
			// startActivity(i2);
			// break;
			// case R.id.i_chanpin3:
			// Intent i3=new Intent(GongSiActivity.this, BabyActivity.class);
			// startActivity(i3);
			// break;
			default:
				break;
		}
	}

	/** 接口 */
	public void shouyexinwenxiangqing() {

		final HashMap<String, String> ha = new HashMap<String, String>();
		// 给服务器传值
		ha.put("id", id);
//		System.out.println("-------------" + s);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(
								Constants.CEHNGYUANGONGSIXIANGQING, ha);
				System.out.println("qqqqqqqqqq"+resultFService);
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
							Toast.makeText(GongSiActivity.this, "网络异常", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			}
		});
	}

	private void setData(String resultFService) {
		syxwxq = JsonUtils.parser(resultFService,
				ChengYuanGongSiXiangQingBean.class);
		CacheUtils.putString("rows", syxwxq.getContent());
		ww = syxwxq.getTitle() + "";
		ee = syxwxq.getContent() + "";
		aa = syxwxq.getDianhua() + "";
		bb = syxwxq.getJibie() + "";
		dd = syxwxq.getRenshu() + "";
		ff = syxwxq.getXingzhi() + "";
		rr = syxwxq.getImgurl() + "";
		gongsi_title.setText(ww);
		t_gongsijiejian_juti.setText(ee);
		gongsi_dianhua.setText(aa);
		gongsi_chengyuanjibie.setText(bb);
		gongsi_guimo.setText(dd);
		gongsi_xingzhi.setText(ff);
		// gongsilog_img.setImageResource(rr);
		imageLoader.displayImage(rr, gongsilog_img);

		// BaseApplication.xinwen_biaoti = syxwxq.getTitle() + "";
		// String ww = syxwxq.getTitle() + "";
		// app.settit(ww);
		// BaseApplication.xinwen_neirong = syxwxq.getContent() + "";
		// getActivity().finish();
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		// a57.setOnClickListener(this);
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("公司详情");
		sick_title_right_tv.setVisibility(View.GONE);
		t_gongsijiejian_juti.setOnClickListener(this);
//		t_chakangengduo.setOnClickListener(this);
		// t_gengduochanpin.setOnClickListener(this);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}


}
