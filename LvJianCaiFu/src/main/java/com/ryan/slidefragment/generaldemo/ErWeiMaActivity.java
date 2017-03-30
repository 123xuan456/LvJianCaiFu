package com.ryan.slidefragment.generaldemo;

import java.util.HashMap;

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
import com.ryan.slidefragment.domain.ErWeiMaBean;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

public class ErWeiMaActivity extends BaseActivity implements OnClickListener {
	private ImageWorker mImageLoader;
	private ImageView imageView1;
	private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv;
	private ImageView sick_title_left_img;
	private TextView textView1;
	private ErWeiMaBean erweima;
	private String imgurl2, name;
//	BitmapUtils bitmapUtils = new BitmapUtils(this);
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle b = msg.getData();
			String img = b.getString("imgur");
//			bitmapUtils.display(imageView1, img);
			mImageLoader.loadImage(img, imageView1, R.drawable.chanpinzhenjiazai);
			String mname = b.getString("name");
			textView1.setText(mname);
		}
	};

	@Override
	public void findView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_erweima);
		mImageLoader = new ImageFetcher(this);
		mImageLoader.setImageCache(ImageCache.getInstance(this));
		textView1 = (TextView) findViewById(R.id.textView1);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_img.setOnClickListener(this);
		sick_title_left_tv.setOnClickListener(this);
		sick_title_mid_tv.setText("二维码");
		sick_title_right_tv.setVisibility(View.GONE);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		wakaka();
		// textView1.setText(name);
		// BitmapUtils bitmapUtils = new BitmapUtils(this);
		// bitmapUtils.display(imageView1, imgurl);

	}

	private void wakaka() {
		final HashMap<String, String> params = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {

			public void run() {
				final String resultFService = HttpClientDao
						.getListHttpClientPost(Constants.ERWEIMATUPIAN, params);
				System.out.println(resultFService);
				boolean judger = JsonJudger.JsonJudger(resultFService, "code",
						"200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
							// setData(resultFService);
							erweima = JsonUtils.parser(resultFService,
									ErWeiMaBean.class);
							CacheUtils.putString("imgurl", erweima.getImgurl());
							CacheUtils.putString("name", erweima.getName());
							String imgurl2 = erweima.getImgurl() + "";
							System.out.print(imgurl2);

							String textname = erweima.getName() + "";
							// String name2 = erweima.getImgurl()+"";
							Message msg = new Message();
							Bundle b = new Bundle();
							b.putString("imgur", imgurl2);
							b.putString("name", textname);
							msg.setData(b);
							// msg.obj = b;
							mHandler.sendMessage(msg);
							// mHandler.sendEmptyMessage(0);
						}
					});

				} else {
					ThreadUtils.post(new Runnable() {

						public void run() {
							Toast.makeText(ErWeiMaActivity.this, "网络连接错误",
									Toast.LENGTH_LONG)
									.show();
						}
					});
				}
			}
		});
	}

//	private void setData(String resultFService) {
//		erweima = JsonUtils.parser(resultFService, ErWeiMaBean.class);
//		CacheUtils.putString("imgurl", erweima.getImgurl());
//		CacheUtils.putString("name", erweima.getName());
//		// imgurl = erweima.getImgurl()+"";
//		name = erweima.getImgurl() + "";
//
//	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

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
}