package com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.canguhezuo;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.ZhaoXiangMu_xiangqing;
import com.ryan.slidefragment.tourongzi.adapter.XiangMuRongZiAdapter;
import com.ryan.slidefragment.tourongzi.adapter.ZiJinJiaoYiAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CanGuHeZuoActivity extends Activity implements OnClickListener{
	private PopupWindow popupwindow;
	private Button type,region,mode;
	private PullToRefreshListView pullToRefresh;
	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	private ZiJinJiaoYiAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zi_jin_jiao_yi);
		setView();
		getDate();

	}

	private void setView() {
		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
		title.setText("参股合作");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});


		type = (Button) findViewById(R.id.type);
		region = (Button) findViewById(R.id.region);
		mode = (Button) findViewById(R.id.mode);
		type.setOnClickListener(this);
		region.setOnClickListener(this);
		mode.setOnClickListener(this);


		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
		 pullToRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			 @Override
			 public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				 String  id=list.get(i-1).get("id").toString();
				 System.out.println("id+="+id);
				 Intent i1=new Intent(CanGuHeZuoActivity.this,ZhaoXiangMu_xiangqing.class);
				 i1.putExtra("id", id);
				 startActivity(i1);
			 }
		 });


	//	adapter = new ZiJinJiaoYiAdapter();
	//	pullToRefresh.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.type:
				if (popupwindow != null&&popupwindow.isShowing()) {
					popupwindow.dismiss();
					return;
				} else {
					initmPopupWindowView();
					popupwindow.showAsDropDown(v, 0, 5);
				}
				break;
			case R.id.mode:
				if (popupwindow != null&&popupwindow.isShowing()) {
					popupwindow.dismiss();
					return;
				} else {
					initmPopupWindowView();
					popupwindow.showAsDropDown(v, 0, 5);
				}
				break;
			case R.id.region:
				if (popupwindow != null&&popupwindow.isShowing()) {
					popupwindow.dismiss();
					return;
				} else {
					initmPopupWindowView();
					popupwindow.showAsDropDown(v, 0, 5);
				}
				break;
			default:
				break;
		}

	}

	private void initmPopupWindowView() {
		// // 获取自定义布局文件pop.xml的视图  
		View customView = getLayoutInflater().inflate(R.layout.popview_item,
				null, false);
		// 创建PopupWindow实例,200,150分别是宽度和高度  
		popupwindow = new PopupWindow(customView, 250, 280);
		// 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]  
		popupwindow.setAnimationStyle(R.style.animationFade);
		// 自定义view添加触摸事件  
		customView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupwindow != null && popupwindow.isShowing()) {
					popupwindow.dismiss();
					popupwindow = null;
				}

				return false;
			}
		});

		/** 在这里可以实现自定义视图的功能 */
		Button btton2 = (Button) customView.findViewById(R.id.button2);
		Button btton3 = (Button) customView.findViewById(R.id.button3);
		Button btton4 = (Button) customView.findViewById(R.id.button4);
		btton2.setOnClickListener(this);
		btton3.setOnClickListener(this);
		btton4.setOnClickListener(this);
	}

	public void getDate() {
		String url= Constants.CANGUHEZUO;
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpRequest.HttpMethod.GET,
				url, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String s=responseInfo.result.toString();
						System.out.println("sdasdsadda154498="+s);
						parseJson(s);
					}


					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("哎呀，失败了，没有数据");
					}
				});

	}
	ArrayList<Map<String, Object>> list;
	private void parseJson(String s) {

		try {

			list = new ArrayList<Map<String, Object>>();
			JSONObject obj=new JSONObject(s);
			//String di=obj.getString("di");
			JSONObject date=obj.getJSONObject("date");
			JSONArray guquan= date.getJSONArray("guquan");
			for (int i = 0; i < guquan.length(); i++) {
				JSONObject o=guquan.getJSONObject(i);
				String title=o.getString("title");
				String provinceid=o.getString("provinceid");
				String fangshitypeid=o.getString("fangshitypeid");
				String hangyetypeid=o.getString("hangyetypeid");
				String yongtu=o.getString("yongtu");
				String createtime=o.getString("createtime");
				String id=o.getString("id");
				String tiaoshu=o.getString("tiaoshu");
				String jingzichan=o.getString("jingzichan");
				String amount=o.getString("amount");
				String total=o.getString("total");
				String description=o.getString("description");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", title);
				map.put("provinceid", provinceid);
				map.put("fangshitypeid", fangshitypeid);
				map.put("hangyetypeid", hangyetypeid);
				map.put("yongtu", yongtu);
				map.put("id", id);
				map.put("createtime", createtime);
				map.put("total", total);
				map.put("tiaoshu", tiaoshu);
				map.put("jingzichan", jingzichan);
				map.put("description", description);
				map.put("amount", amount);
				list.add(map);
				System.out.println("sdhjkasdhas"+list);
				adapter = new ZiJinJiaoYiAdapter(getApplicationContext(), list);
				pullToRefresh.setAdapter(adapter);
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






	}

}
