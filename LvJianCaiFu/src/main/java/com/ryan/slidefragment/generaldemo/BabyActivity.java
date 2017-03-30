package com.ryan.slidefragment.generaldemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.domain.UserInfoBean;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.view.BabyPopWindow;
import com.ryan.slidefragment.view.BabyPopWindow.OnItemClickListener;

/**
 * 单个商品详情界面
 * 
 * @author lyz
 * 
 */
public class BabyActivity extends FragmentActivity implements OnClickListener,
OnItemClickListener {
	private ImageWorker mImageLoader;
	// private List<String> lunbotu = new ArrayList<String>();
	private TextView chanpinzhanshi, title_chanpin;
	private ImageView sick_title_left_img, headcar, ii;
	private List<HashMap<String, String>> aa = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> map;
	private PullToRefreshListView pullToRefresh;
	private TextView textView, textView1;
	ImageView imageView;
	private String id1;
	private ImageView iv_baby_collection,iv_baby_collection_1;
	private ArrayList<View> allListView;
	String ids;
	HashMap<String, String> params1 = new HashMap<String, String>();
	private String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);


		mImageLoader = new ImageFetcher(this);
		mImageLoader.setImageCache(ImageCache.getInstance(this));
		setContentView(R.layout.babydetail_a);
		Person mPerson = (Person) getIntent().getSerializableExtra("pr");
		ids = (String) mPerson.getName();
		// 得到保存的收藏信息
		//getSaveCollection();
		initView();
		addlistener();
		params1.put("id", ids + "");
		// String url1 = Constants.QUANBUCHANPINXIANGQING;
		Gridview gWeather = new Gridview();
		gWeather.execute(getUrl(params1));
		s = BaseApplication.name;
	}

	// fandView
	private void initView() {
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		chanpinzhanshi = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_left_img.setOnClickListener(this);
		title_chanpin = (TextView) findViewById(R.id.title_chanpin);
		ii = (ImageView) findViewById(R.id.ii);
		textView = (TextView) findViewById(R.id.textView);
		iv_baby_collection = (ImageView)
				findViewById(R.id.iv_baby_collection);
		iv_baby_collection_1 = (ImageView)
				findViewById(R.id.iv_baby_collection_1);
		// headcar = (ImageView) findViewById(R.id.headcar);
		// gongsi = (ImageView) findViewById(R.id.gongsi);
		// buy_now = (ImageView) findViewById(R.id.buy_now);
		// put_in = (ImageView) findViewById(R.id.put_in);
		// textView1 = (TextView) findViewById(R.id.textView1);
		// headcar = (ImageView) findViewById(R.id.headcar);
		// initViewPager();
//		if (isCollection) {
//			// 如果已经收藏，则显示收藏后的效果
//			iv_baby_collection.setImageResource(R.drawable.second_2_collection);
//		}

	}

	// 点击事件
	private void addlistener() {
		iv_baby_collection.setOnClickListener(this);
		iv_baby_collection_1.setOnClickListener(this);
		//textView1.setOnClickListener(this);
		//headcar.setOnClickListener(this);
		//put_in.setOnClickListener(this);
		//buy_now.setOnClickListener(this);
		//gongsi.setOnClickListener(this);

	}

	// /** 得到保存的是否添加收藏标记 */
//	private void getSaveCollection() {
//		SharedPreferences sp = getSharedPreferences("SAVECOLLECTION",
//				Context.MODE_PRIVATE);
//		isCollection = sp.getBoolean("isCollection", false);
//
//	}

	private void initViewPager() {/*
		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < aa.size(); i++) {
			View view = LayoutInflater.from(this).inflate(R.layout.pic_item,
					null);
			imageView = (ImageView) view.findViewById(R.id.pic_item);
			//	  imageLoader.displayImage(aab2, ii);
			//	
			//	 imageLoader.displayImage(getString("img"), imageView);
			imageView.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// 查看大图片界面
					Intent intent = new Intent(BabyActivity.this,
							ShowBigPictrue.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}
			});
			allListView.add(view);
		}
		 iv_baby = (HackyViewPager) findViewById(R.id.iv_baby);
	 *//** 加载器 *//*
			 ViewPagerAdapter adapter = new ViewPagerAdapter();
			 iv_baby.setOnPageChangeListener(new OnPageChangeListener() {

			 public void onPageSelected(int arg0) {
			 position = arg0;
			 }

			 public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

			 }


		 public void onPageScrollStateChanged(int arg0) {
			 // TODO Auto-generated method stub

			 }
			 });
			 iv_baby.setAdapter(adapter);
	  */

		class ViewPagerAdapter extends PagerAdapter {

			// @Override
			public int getCount() {
				// TODO Auto-generated method stub
				return allListView.size();
			} 
			// @Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == (View) arg1;
			}

			// @Override
			public void destroyItem(ViewGroup container, int position, Object object)
			{
				container.removeView((View) object);
			}

			// @Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = allListView.get(position);
				container.addView(view);
				return view;
			}

		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
			// case R.id.headcar:
			// 跳不过去Activity跳转fragment
			// Intent intent = new Intent(this,GouWuCheFragment.class);
			// startActivity(intent);
			// break;
		//点击收藏
		case R.id.iv_baby_collection:
			if (s != null) {
			iv_baby_collection.setVisibility(View.GONE);
			iv_baby_collection_1.setVisibility(View.VISIBLE);
			//向服务器发请求
			sendServerParameter();
			}else{
				Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
			}
			break;
			//点击取消收藏
		case R.id.iv_baby_collection_1:
			// 提示是否取消收藏
			cancelCollection();
			break;
		case R.id.sick_title_left_img:
			finish();
			break;
			// case R.id.put_in:
			// // 加入购物车
			// isClickBuy = false;
			// setBackgroundBlack(all_choice_layout, 0);
			// popWindow.showAsDropDown(v);
			// break;
			// case R.id.buy_now:
			// // 立即购买
			// isClickBuy = true;
			// setBackgroundBlack(all_choice_layout, 0);
			// popWindow.showAsDropDown(v);
			//
			// break;
			// case R.id.gongsi:
			// // 所属公司
			// Intent intent1 = new Intent(this, GongSiActivity.class);
			// startActivity(intent1);
			// break;
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
				//发送给服务器
				sendCancelServerParameter();
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
		http.send(HttpRequest.HttpMethod.POST ,
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
		
		String Url=Constants.SHOUCHANG_CHANGPINZHANSHI;
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("title",aab);
		params.addQueryStringParameter("url", PATH);
		params.addQueryStringParameter("usersid",userID);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST ,
				Url,
				params,
				new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result.toString();
				System.out.println("点击收藏传递参数成功?"+result);
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


	private String aab2;
	private String aab1;
	private String aab;
	private String PATH;
	private String id;
	private boolean a;
	class Gridview extends AsyncTask<String, Void, String> {


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuffer sb1 = new StringBuffer();
			try {
				URL url = new URL(params[0]);
				HttpURLConnection huc = (HttpURLConnection) url
						.openConnection();
				InputStream is = huc.getInputStream();
				byte[] b = new byte[1024];
				int len = is.read(b);
				while (len > 0) {
					sb1.append(new String(b, 0, len));
					len = is.read(b);
				}
				is.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sb1.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			JSONObject array = null;

			super.onPostExecute(result);
			try {
				System.out.println("收藏返回数据="+result);
				array = new JSONObject(result);
				aab = array.getString("title");
				aab1 = array.getString("content");
				System.out.println(aab1+"..............................");
				aab2 = array.getString("img");
				System.out.println("图片aab2="+aab2);
				title_chanpin.setText(aab);
				textView.setText(aab1);
				mImageLoader.loadImage(aab2, ii, R.drawable.chanpin_huanbao);
				id = array.getString("id");
				System.out.println("收藏成功拿到的信息id="+id);
				 a = array.getBoolean("yes");
				 System.out.println("收藏返回boolean="+a);
				 if(a==true){
					 iv_baby_collection.setVisibility(View.GONE);
					 iv_baby_collection_1.setVisibility(View.VISIBLE);
				 }else {
					 iv_baby_collection.setVisibility(View.VISIBLE);
					 iv_baby_collection_1.setVisibility(View.GONE);
				 }
					 
				
				//				imageLoader.displayImage(aab2, ii);
				
				// imageView.setImageResource(d[i]);
				// JSONArray a1 = array.getJSONArray("img");
				// if (aa.size() != 0) {
				// aa.clear();
				// }
				// for (int i = 0; i < a1.length(); i++) {
				// JSONObject js1 = a1.optJSONObject(i);
				// String s1 = js1.getString("img");
				// System.out.println(s1+"djidjdjdjodjo");
				// map = new HashMap<String, String>();
				// map.put("imgurl", s1);
				// aa.add(map);
				// System.out.println(aa+"djidjdjdjodjo");
				// // map.add(s1);
				// }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// adapter.notifyDataSetChanged();

		}

	}

	/**
	 * 拿到拼接后的url
	 */
	private String getUrl(HashMap<String, String> params) {
		// private static String PATH =
		// "http://192.168.0.110:8080/lvjiancfAPP/newsselectxinwenquanbu";
		PATH= Constants.QUANBUCHANPINXIANGQING;
		// String PATH1 = Constants.JISHUZHANSHIXIANGQING;
		// String PATH = Constants.URL_CS_DATA;
		// 添加url参数
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			StringBuffer sb = null;
			while (it.hasNext()) {
				String key = it.next();
				String value = params.get(key);
				if (sb == null) {
					sb = new StringBuffer();
					sb.append("?");
				} else {
					sb.append("&");
				}
				sb.append(key);
				sb.append("=");
				sb.append(value);
			}
			PATH += sb.toString();
			System.out.println("djasiodj+="+PATH);
		}
		return PATH;
	}

	// 点击弹窗确认按钮的响应
	// public void onClickOKPop() {
	// setBackgroundBlack(all_choice_layout, 1);
	//
	// if (isClickBuy) {
	// // 如果之前是点击的立即购买，就跳转到立即购买的页面
	// Intent intent = new Intent(BabyActivity.this, AboutActivity.class);
	// startActivity(intent);
	// } else {
	// Toast.makeText(this, "添加到购物车成功", Toast.LENGTH_SHORT).show();
	// }
	// }

	public void onClickOKPop() {
		// TODO Auto-generated method stub

	}

	/** 控制背景变暗 0变暗1变量 */
	// public void setBackgroundBlack(View view, int what) {
	// switch (what) {
	// case 0:
	// // view.setVisibility(View.VISIBLE);
	// break;
	// case 1:
	// // view.setVisibility(View.GONE);
	// break;
	// }
	// }

}
