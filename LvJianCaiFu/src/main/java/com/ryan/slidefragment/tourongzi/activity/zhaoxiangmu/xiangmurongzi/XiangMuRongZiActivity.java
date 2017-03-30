package com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.xiangmurongzi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class XiangMuRongZiActivity extends Activity {
	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	private PullToRefreshListView pullToRefresh;
	private XiangMuRongZiAdapter adapter;
	public List<Map<String, Object>> list ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_xiang_mu_rong_zi);

		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setText("");//隐藏字体
		sick_title_right_tv.setBackgroundResource(R.drawable.xiangmurongzi);
		sick_title_right_tv.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i=new Intent(XiangMuRongZiActivity.this,XiangMuRongZiActivity_ShaiXuan.class);
				startActivity(i);
			}
		});
		title.setText("项目融资");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

		getData();
		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
		pullToRefresh.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				String  id=list.get(arg2-1).get("id").toString();
				System.out.println("id+="+id);
				Intent i=new Intent(XiangMuRongZiActivity.this,ZhaoXiangMu_xiangqing.class);
				i.putExtra("id", id);
				startActivity(i);

			}
		});


		//	 adapter=new XiangMuRongZiAdapter();
		//	pullToRefresh.setAdapter(adapter);

	}

	private void getData() {
		String url=Constants.Xiangmujianchang_1;
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

	protected void parseJson(String s) {
		try {
			list=new ArrayList<Map<String, Object>>();
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
				adapter = new XiangMuRongZiAdapter(getApplicationContext(), list);
				pullToRefresh.setAdapter(adapter);
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}








	}





}
