package com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpUtilsDao;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.adapter.LiuYanAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhaoXiangMu_xiangqing extends Activity implements View.OnClickListener
{

	TextView textView5,textView7,title,zhuti,rongzijine,xiangmuleixing,yongtu,rongzijine1,
			fangshi,provinceid,hangye,textView8,description,mingzi,xingbie,textView2,cityid
			;


	ImageView iv_baby_collection_1,iv_baby_collection;
	ListView listView1;
	private List<Map<String, Object>> list;
	private LiuYanAdapter adapter;

	private boolean a;
	private TextView sick_title_mid_tv, sick_title_right_tv,
			sick_title_left_tv;
	private ImageView sick_title_left_img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zhao_xiang_mu_xiangqing);
		Intent i=getIntent();
		String ids = i.getStringExtra("id");
		System.out.println("id=" + ids);
		setView();
		getData(ids);
		s= BaseApplication.name;
		da = new Data();
	}
	private void setView() {
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_mid_tv.setText("详情");


		textView5=(TextView) findViewById(R.id.textView5);
		textView7=(TextView) findViewById(R.id.textView7);
		title=(TextView) findViewById(R.id.title);
		zhuti=(TextView) findViewById(R.id.zhuti);
		xiangmuleixing=(TextView) findViewById(R.id.xiangmuleixing);
		yongtu=(TextView) findViewById(R.id.yongtu);
		rongzijine1=(TextView) findViewById(R.id.rongzijine1);
		fangshi=(TextView) findViewById(R.id.fangshi);
		provinceid=(TextView) findViewById(R.id.provinceid);
		hangye=(TextView) findViewById(R.id.hangye);
		textView8=(TextView) findViewById(R.id.textView8);
		mingzi=(TextView) findViewById(R.id.mingzi);
		xingbie=(TextView) findViewById(R.id.xingbie);
		cityid=(TextView) findViewById(R.id.cityid);
		textView2=(TextView) findViewById(R.id.textView2);

		description=(TextView) findViewById(R.id.description);
		listView1=(ListView) findViewById(R.id.listView1);

		iv_baby_collection_1= (ImageView) findViewById(R.id.iv_baby_collection_1);
		iv_baby_collection= (ImageView) findViewById(R.id.iv_baby_collection);
		iv_baby_collection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (s != null) {
					iv_baby_collection.setVisibility(View.GONE);
					iv_baby_collection_1.setVisibility(View.VISIBLE);
					//向服务器发请求
					sendServerParameter();
					Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
				}
			}
		});
		iv_baby_collection_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				cancelCollection();
			}
		});
	}
	String url;
	private void getData(String id2) {

		url = com.ryan.slidefragment.options.Constants.ZHAOXIANGMUXiangQing+id2;
		HttpUtils http=new HttpUtils();
		http.send(HttpRequest.HttpMethod.GET,
				url,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result.toString() ;
						JSonData(result);

					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});


	}
	private void JSonData(String result) {
		System.out.println("result="+result);
		list=new ArrayList<Map<String, Object>>();

		JSONObject obj;
		try {
			obj=new JSONObject(result);

			JSONArray liuyan=obj.getJSONArray("liuyan");
			for (int i = 0; i < liuyan.length(); i++) {
				JSONObject o=liuyan.getJSONObject(i);
				String bie=o.getString("bie");
				String content=o.getString("content");
				String id=o.getString("id");
				String itemId=o.getString("itemId");
				String ming=o.getString("ming");
				String site=o.getString("site");
				String url=o.getString("url");
				String usersid=o.getString("usersid");

				Map<String, Object> map= new HashMap<String, Object>();
				map.put("id", id);
				map.put("itemId", itemId);
				map.put("content", content);
				map.put("bie", bie);
				map.put("ming", ming);
				map.put("usersid", usersid);
				map.put("site", site);
				map.put("url", url);
				list.add(map);
			}
			System.out.println("sdhjkasdhas" + list);
			adapter = new LiuYanAdapter(getApplicationContext(), list);
			listView1.setAdapter(adapter);
			fixListViewHeight(listView1);

			mingzi.setText(obj.getString("mingzi"));
			rongzijine1.setText(obj.getString("rongzijine"));
			zhuti.setText(obj.getString("zhuti"));
			provinceid.setText(obj.getString("provinceid"));
			String remarks=obj.getString("remarks");
			System.out.println("remark=" + remarks);
			String xingbie1=obj.getString("xingbie");
			xingbie.setText(xingbie1);
			id=obj.getString("id");
			System.out.println("id=" + id);
			yongtu.setText(obj.getString("yongtu"));
			cityid.setText(obj.getString("cityid"));
			hangye.setText(obj.getString("hangye"));
			ww=obj.getString("title");
			title.setText(ww);


			a = obj.getBoolean("yes");
			System.out.println("收藏返回boolean=" + a);
			if(a==true){
				iv_baby_collection.setVisibility(View.GONE);
				iv_baby_collection_1.setVisibility(View.VISIBLE);
			}else if(a==false){
				iv_baby_collection.setVisibility(View.VISIBLE);
				iv_baby_collection_1.setVisibility(View.GONE);
			}

			String superiority=obj.getString("superiority");
			description.setText(obj.getString("description"));
			xiangmuleixing.setText(obj.getString("xiangmuleixing"));
			fangshi.setText(obj.getString("fangshi"));


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void fixListViewHeight(ListView listView) {
		//   ListAdapter listAdapter = listView.getAdapter();
		int totalHeight = 0;
		if (adapter == null) {
			return;
		}
		for (int i = 0, len = adapter.getCount(); i < len; i++) {
			View listViewItem = adapter.getView(i , null, listView);
			listViewItem.measure(0, 0);
			totalHeight += listViewItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight+ (listView.getDividerHeight() * (adapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	private String s;
	private HttpUtilsDao dao;
	String ww;
	@Override
	public void onClick(View view) {
		switch (view.getId()){
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
		}
	}


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
	String id;
	private void sendCancelServerParameter() {
		String url1=Constants.SHOUCHANG_QUXIAO;
		RequestParams params = new RequestParams();
		String id1=da.getId();
		System.out.println("参数id2有没有" + id1);

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
	Data da;

	private void  sendServerParameter() {

		String userID = BaseApplication.userID;
		//用来判断是否有次用户
		System.out.println("用户id传递"+userID);

		String Url= Constants.SHOUCHANG_CHANGPINZHANSHI;
		RequestParams params = new RequestParams();
		System.out.println("用户id传递url"+url);
		System.out.println("用户id传递title"+ww);
		System.out.println("用户id传递usersid"+userID);

		params.addQueryStringParameter("title",ww);
		params.addQueryStringParameter("url", url);
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
							String id2=new String();
							id2 = array.getString("id");

							da.setId(id2);
							System.out.println("参数id2=" +id2);
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
}
class Data{
	public String  id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
