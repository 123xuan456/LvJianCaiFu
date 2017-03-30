package com.ryan.slidefragment.generaldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ryan.slidefragment.adapter.MyAdapter_quanbuchanpin;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QuanBuChanPinActivity extends Activity implements OnClickListener {
	private PullToRefreshListView pullToRefresh;
	private MyAdapter_quanbuchanpin adapter;
	// listView中item的数据 数据根据布局而定
	// private List<PullBean> data = new ArrayList<PullBean>();
	public static int pon = 1;
	//private ProgressDialog dialog;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// 封装参数集合
	HashMap<String, String> upParamsMap = new HashMap<String, String>();
	List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	HashMap<String, Integer> params = new HashMap<String, Integer>();
	private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv;
	private ImageView sick_title_left_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quan_bu_chan_pin);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(this);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("产品信息");
		sick_title_right_tv.setVisibility(View.GONE);
		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
		params.put("pno", pon);

		//dialog = new ProgressDialog(this);
		//dialog.setTitle("提示");
		//dialog.setMessage("正在加载...");
		new MyTask().execute(getUrl(params));

		// pullToRefresh.setMode(Mode.BOTH);
		// init();
		pullToRefresh.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				// 上拉刷新
				// adapter.notifyDataSetChanged();
				list.clear();
				// params.clear();
				if (pon < 1) {
					pon = 1;
				} else {
					pon--;
				}
				params.put("pno", pon);
				new MyTask().execute(getUrl(params));
				System.out.print(getUrl(params));
				adapter.notifyDataSetChanged();

			}

			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

				// 加载更多
				// adapter.notifyDataSetChanged();
				// params.clear();
				list.clear();
				pon++;
				params.put("pno", pon);
				new MyTask().execute(getUrl(params));
				System.out.print(getUrl(params));
				adapter.notifyDataSetChanged();
			}
		});

		/**
		 * item的点击事件
		 */
		pullToRefresh.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(
				// TongZhiGongGaoActivity.this,
				// "您点击了",
				// Toast.LENGTH_SHORT).show();
				list.get(position - 1).get("id");
				Person mPerson = new Person();
				mPerson.setName(list.get(position - 1).get("id"));
				Intent mIntent = new Intent(QuanBuChanPinActivity.this,
						BabyActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});
	}

	/**
	 * 拿到拼接后的url
	 */
	private String getUrl(HashMap<String, Integer> params) {
		// private static String PATH =
		// "http://192.168.0.110:8080/lvjiancfAPP/newsselectxinwenquanbu";
		String PATH = Constants.QUANBUCHANPIN;
		// String PATH1 = Constants.JISHUZHANSHIXIANGQING;
		// String PATH = Constants.URL_CS_DATA;
		// 添加url参数
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			StringBuffer sb = null;
			while (it.hasNext()) {
				String key = it.next();
				Integer value = params.get(key);
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
		}
		return PATH;
	}

	public void addListener() {
		// TODO Auto-generated method stub

	}

	public void initView() {

	}

	public void initData() {
	}

	/**
	 * MyTask继承线程池AsyncTask用来网络数据请求、json解析、数据更新等操作。
	 */
	class MyTask extends AsyncTask<String, Void, String> {
		/**
		 * 数据请求前显示dialog。
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//dialog.show();
		}

		/**
		 * 在doInBackground方法中，做一些诸如网络请求等耗时操作。
		 */
		@Override
		protected String doInBackground(String... params) {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {

			}
			return RequestData();
		}

		/**
		 * 在该方法中，主要进行一些数据的处理，更新。
		 */
		@Override
		protected void onPostExecute(String result) {

			if (result != null) {
				// 如果获取的result数据不为空，那么对其进行JSON解析。并显示在手机屏幕上。
				list = JSONAnalysis(result);
				adapter = new MyAdapter_quanbuchanpin(
						QuanBuChanPinActivity.this, list);
				pullToRefresh.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				//dialog.dismiss();
			} else if (result == null) {
				Toast.makeText(QuanBuChanPinActivity.this, "请求数据失败...",
						Toast.LENGTH_LONG).show();
			}
			//dialog.dismiss();
			pullToRefresh.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

	/**
	 * 网络数据请求
	 * 
	 * @return
	 */
	public String RequestData() {
		HttpPost post = new HttpPost(getUrl(params));
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = null;
		try {
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == 200) {
				InputStream inputStream = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				builder = new StringBuilder();
				String s = null;
				for (s = reader.readLine(); s != null; s = reader.readLine()) {
					builder.append(s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	 * JSON解析
	 * 
	 * @param result
	 * @return
	 */
	public List<Map<String, Object>> JSONAnalysis(String result) {
		JSONObject array = null;
		try {
			array = new JSONObject(result);
			JSONObject jj1_xinwen = array.getJSONObject("date");
			String aab = array.getString("di");
			JSONArray a1_xinwen = jj1_xinwen.getJSONArray("quanbuchanpin");

			for (int i = 0; i < a1_xinwen.length(); i++) {
				JSONObject objectOne = a1_xinwen.optJSONObject(i);
				String id = objectOne.optString("id");
				String content = objectOne.optString("content");
				String title = objectOne.optString("name");
				String imgurl = objectOne.optString("imgurl");
				String address = objectOne.optString("address");
				System.out.println(title);
				System.out.println(content);
				// String hdf_yy_dizhi = objectOne.optString("hdf_yy_dizhi");
				// String picname_hospital_s = objectOne
				// .optString("picname_hospital_s");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("content", content);
				map.put("name", title);
				map.put("imgurl", imgurl);
				map.put("address", address);
				// map.put("hdf_yy_dizhi", hdf_yy_dizhi);
				// map.put("picname_hospital_s", picname_hospital_s);
				list.add(map);
			}
			if (aab.equals("a")) {
				pullToRefresh.setMode(Mode.BOTH);

			} else {
				Toast.makeText(QuanBuChanPinActivity.this, "没有更多数据了",
						Toast.LENGTH_SHORT).show();
				// pullToRefresh.setMode(Mode.BOTH);
				// pullToRefresh.setMode(Mode.PULL_FROM_START);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

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
