package com.ryan.slidefragment.generaldemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.view.BabyPopWindow;
import com.ryan.slidefragment.view.BabyPopWindow.OnItemClickListener;

/**
 * 点击我的收藏列表，进入详情页面
 */
public class BabyActivity_1 extends FragmentActivity implements OnClickListener,
OnItemClickListener {
	private ImageWorker mImageLoader;
	private ImageView sick_title_left_img, ii;
	HashMap<String, String> map;
	ImageView imageView;
	private String id1;
	private ImageView iv_baby_collection,iv_baby_collection_1;
	private ImageView iv_baby_collectio,iv_baby_collection_;//两个不同的布局显示的收藏按钮不一样
	/** 用于设置背景暗淡 */
	String ids;
	private String url1;
	private String id;
	private String aab;
	private String aab1;
	private String img;
	private String click;
	private TextView title_chanpin,xinwen_biaoti,xinwen_neirong,xinwen_liulanliang;
	private TextView textView,sick_title_mid_tv,sick_title_right_tv;
	private boolean a;
	private LinearLayout scroll_1;
	private LinearLayout scroll_2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mImageLoader = new ImageFetcher(this);
		mImageLoader.setImageCache(ImageCache.getInstance(this));
		setContentView(R.layout.babydetail_b);
		Intent i=getIntent();
		url1=i.getStringExtra("url1");
		System.out.println("url1="+url1);
		//向服务器拿数据
		getData(url1);
		initView();
		addlistener();
		
		
	}

	private void getData(String url12) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000  * 10); //设置超时时间   10s 
		http.send(HttpRequest.HttpMethod.POST ,
				url12,
				new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result.toString();
				setData(result);
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("传递失败");
			}
		});
	}

	protected void setData(String result) {
		System.out.println("我的收藏读取数据="+result);
		JSONObject array;
		try {
		array = new JSONObject(result);
		aab = array.getString("title");//标题
		aab1 = array.getString("content");//内容
		System.out.println("内容="+aab1);
		img = array.getString("img");//图片url
		System.out.println("图片img="+img);
		
		id = array.getString("id");//用户点击收藏生成的id
		System.out.println("用户点击收藏生成的id="+id);
		a = array.getBoolean("yes");//用户点击收藏生成的，通过true，false来判断按钮的显示
		System.out.println("收藏返回boolean="+a);
		//click=array.getString("click");
	//	System.out.println("点击次数="+click);
		if(img.equals("null")){//当没有图片使用不同布局
			scroll_1.setVisibility(View.GONE);
			scroll_2.setVisibility(View.VISIBLE);
			xinwen_biaoti.setText(aab);
			xinwen_neirong.setText(aab1);
		//	xinwen_liulanliang.setText(click);
			
		}else{//有图片
			scroll_2.setVisibility(View.GONE);
			scroll_1.setVisibility(View.VISIBLE);
			title_chanpin.setText(aab);
			textView.setText(aab1);
			mImageLoader.loadImage(img, ii, R.drawable.chanpin_log);
		}
//		id = array.getString("id");//用户点击收藏生成的id
//		System.out.println("用户点击收藏生成的id="+id);
//		a = array.getBoolean("yes");//用户点击收藏生成的，通过true，false来判断按钮的显示
//		System.out.println("收藏返回boolean="+a);
//		if(a==true){
//			iv_baby_collection.setVisibility(View.GONE);
//			iv_baby_collection_1.setVisibility(View.VISIBLE);
//			iv_baby_collectio.setVisibility(View.GONE);
//			iv_baby_collection_.setVisibility(View.VISIBLE);
//		}else {
//			iv_baby_collection.setVisibility(View.VISIBLE);
//			iv_baby_collection_1.setVisibility(View.GONE);
//			iv_baby_collectio.setVisibility(View.VISIBLE);
//			iv_baby_collection_.setVisibility(View.GONE);
//		}
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// fandView
	private void initView() {
		sick_title_right_tv=(TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv=(TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_mid_tv.setText("收藏列表");
		sick_title_right_tv.setText("");
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_left_img.setOnClickListener(this);
		ii = (ImageView) findViewById(R.id.ii);
//		iv_baby_collection = (ImageView)
//				findViewById(R.id.iv_baby_collection);
//		iv_baby_collection_1 = (ImageView)
//				findViewById(R.id.iv_baby_collection_1);
//		iv_baby_collectio = (ImageView)
//				findViewById(R.id.iv_baby_collectio);
//		iv_baby_collection_ = (ImageView)
//				findViewById(R.id.iv_baby_collection_);
		textView = (TextView) findViewById(R.id.textView);
		title_chanpin=(TextView) findViewById(R.id.title_chanpin);
		xinwen_biaoti=(TextView) findViewById(R.id.xinwen_biaoti);
		xinwen_neirong = (TextView) findViewById(R.id.xinwen_neirong);
		//xinwen_liulanliang = (TextView) findViewById(R.id.xinwen_liulanliang);
		scroll_1=(LinearLayout)findViewById(R.id.scroll_1);
		scroll_2=(LinearLayout)findViewById(R.id.scroll_2);
	}
	// 点击事件
	private void addlistener() {
//		iv_baby_collection.setOnClickListener(this);
//		iv_baby_collection_1.setOnClickListener(this);
//		iv_baby_collectio.setOnClickListener(this);
//		iv_baby_collection_.setOnClickListener(this);
	}


	public void onClick(View v) {
		switch (v.getId()) {
		//点击收藏
//		case R.id.iv_baby_collection:
//			iv_baby_collection.setVisibility(View.GONE);
//			iv_baby_collection_1.setVisibility(View.VISIBLE);
//			//向服务器发请求
//			sendServerParameter();
//			Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
//			break;
//			//点击取消收藏
//		case R.id.iv_baby_collection_1:
//			// 提示是否取消收藏
//			cancelCollection();
//			break;
//		case R.id.iv_baby_collectio:
//			iv_baby_collection.setVisibility(View.GONE);
//			iv_baby_collection_1.setVisibility(View.VISIBLE);
//			//向服务器发请求
//			sendServerParameter();
//			Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
//			break;
//			//点击取消收藏
//		case R.id.iv_baby_collection_:
//			// 提示是否取消收藏
//			cancelCollection();
//			break;
		case R.id.sick_title_left_img:
			finish();
			break;
		default:
			break;
		}

	}

	// 取消收藏
	private void cancelCollection() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("是否取消收藏");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// 如果取消收藏，则显示取消收藏的结果
				iv_baby_collection.setVisibility(View.VISIBLE);
				 iv_baby_collection_1.setVisibility(View.GONE);
				 iv_baby_collectio.setVisibility(View.VISIBLE);
				 iv_baby_collection_.setVisibility(View.GONE);
				//发送给服务器
				sendCancelServerParameter();
				//点击取消之后返回到列表页面,并要跟新列表
//				CollectionActivity aa = new CollectionActivity();
//				aa.finish();
//				Intent i=new Intent(BabyActivity_1.this,CollectionActivity.class);
//				startActivity(i);
				finish();
			}
		});
		dialog.setNegativeButton("取消", null);
		dialog.create().show();
	}
	//取消收藏时发送服务器
	protected void sendCancelServerParameter() {
		String url1=Constants.SHOUCHANG_QUXIAO;
		RequestParams params = new RequestParams();
		System.out.println("参数id2有没有"+id1);
		if (id1==null) {
			params.addQueryStringParameter("id", id);
		} else if (id1==id) {
			params.addQueryStringParameter("id", id1);
		} else {
			params.addQueryStringParameter("id", id1);
		}
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间   10s 
		http.send(HttpRequest.HttpMethod.POST,
				url1,
				params,
				new RequestCallBack<String>() {
			@Override
			public void onStart() {
				
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result.toString() ;
				System.out.println("取消收藏传递参数成功?"+result);
				
				
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("传递失败");
			}
		});
		
		
	}

	/*收藏之后，向服务器发送请求*/
	private void sendServerParameter() {
		String userID = BaseApplication.userID;
		//用来判断是否有次用户
		System.out.println("用户id传递"+userID);
		
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST ,
				url1,
				new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result.toString();
				System.out.println("点击收藏"+result);
				JSONObject array;
				try {
					array = new JSONObject(result);
					id1 = array.getString("id");
					System.out.println("参数id1="+id1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				Log.i(msg, error.toString());
				System.out.println("传递失败了，宝贝");
			}
		});
	}

	public void onClickOKPop() {

	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
