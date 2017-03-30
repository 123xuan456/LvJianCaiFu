package com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.R.id;
import com.ryan.slidefragment.generaldemo.R.layout;
import com.ryan.slidefragment.generaldemo.R.menu;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.canguhezuo.CanGuHeZuoActivity;
import com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.qitahezuo.QiTaHeZuoActivity;
import com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.shougou.ShouGouActivity;
import com.ryan.slidefragment.tourongzi.activity.zhaoxiangmu.xiangmurongzi.XiangMuRongZiActivity;
import com.ryan.slidefragment.tourongzi.activity.zhaozijin.ZhaoZijin_GuQuanTouZi_Activity;
import com.ryan.slidefragment.tourongzi.adapter.LiuYanAdapter;
import com.ryan.slidefragment.tourongzi.adapter.ZhaoxiangmuAdapter;
import com.ryan.slidefragment.tourongzi.ben.Root;
import com.ryan.slidefragment.tourongzi.ben.Root.Date;
import com.ryan.slidefragment.tourongzi.ben.Root.Date.Guquan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ZhaoxiangmuActivity extends Activity implements OnClickListener{


	private PullToRefreshListView pullToRefresh;
	private TextView title,sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	private RelativeLayout Rln1,Rln2,Rln3,Rln4;
	private ZhaoxiangmuAdapter adapter;
	public List<Map<String, Object>> list ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zhaoxiangmu);
		initView();
		getData();
	}

	private void getData() {
		String url=Constants.ZHAOXIANGMU;
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpRequest.HttpMethod.GET,
				url, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String s=responseInfo.result.toString();
						System.out.println("sdasdsadda154498="+s);
						gson(s);

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("哎呀，失败了，没有数据");
					}
				});


	}

	protected void gson(String s) {
		try {
			list=new ArrayList<Map<String, Object>>();
			JSONObject obj=new JSONObject(s);
			//String di=obj.getString("di");
			JSONObject date=obj.getJSONObject("date");
			JSONArray guqua= date.getJSONArray("guquan");
			for (int i = 0; i < guqua.length(); i++) {
				JSONObject o=guqua.getJSONObject(i);
				String title=o.getString("title");
				String provinceid=o.getString("provinceid");
				String fangshitypeid=o.getString("fangshitypeid");
				String hangyetypeid=o.getString("hangyetypeid");
				String yongtu=o.getString("yongtu");
				String createtime=o.getString("createtime");
				String id=o.getString("id");
				String tiaoshu=o.getString("tiaoshu");
				String jingzichan=o.getString("jingzichan");
				String image=o.getString("image");

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", title);
				map.put("provinceid", provinceid);
				map.put("fangshitypeid", fangshitypeid);
				map.put("hangyetypeid", hangyetypeid);
				map.put("yongtu", yongtu);
				map.put("id", id);
				map.put("createtime", createtime);
				map.put("tiaoshu", tiaoshu);
				map.put("jingzichan", jingzichan);
				map.put("image", image);
				list.add(map);
				System.out.println("sdhjkasdhas" + list);
			}
			adapter = new ZhaoxiangmuAdapter(getApplicationContext(), list);
			pullToRefresh.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	private void initView() {
		title= (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
		title.setText("找项目");
		sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
		sick_titel_left_layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		Rln1=(RelativeLayout) findViewById(R.id.lin1);//项目融资
		Rln2=(RelativeLayout) findViewById(R.id.lin2);//资金交易
		Rln3=(RelativeLayout) findViewById(R.id.lin3);//政府招商
		Rln4=(RelativeLayout) findViewById(R.id.lin4);//投资理财
		Rln1.setOnClickListener(this);
		Rln2.setOnClickListener(this);
		Rln3.setOnClickListener(this);
		Rln4.setOnClickListener(this);

		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);

		/**
		 * item的点击事件
		 */
		pullToRefresh.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				String id1=list.get(arg2-1).get("id").toString();
				Intent mIntent = new Intent(getApplicationContext(),
						ZhaoXiangMu_xiangqing.class);
				mIntent.putExtra("id", id1);
				startActivity(mIntent);
			}
		});


	}


	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.lin1:
				Intent i1=new Intent(ZhaoxiangmuActivity.this,XiangMuRongZiActivity.class);//项目建厂
				startActivity(i1);
				break;
			case R.id.lin2:
				Toast.makeText(getApplicationContext(),"正在开发中。。。",Toast.LENGTH_LONG).show();
//				Intent i2=new Intent(ZhaoxiangmuActivity.this,CanGuHeZuoActivity.class);//参股合作
//				startActivity(i2);
				break;
			case R.id.lin3:
				Toast.makeText(getApplicationContext(),"正在开发中。。。",Toast.LENGTH_LONG).show();
//				Intent i3=new Intent(ZhaoxiangmuActivity.this,ShouGouActivity.class);//收购并购
//				startActivity(i3);
				break;
			case R.id.lin4:
				Toast.makeText(getApplicationContext(),"正在开发中。。。",Toast.LENGTH_LONG).show();
//				Intent i4=new Intent(ZhaoxiangmuActivity.this,QiTaHeZuoActivity.class);//其他合作
//				startActivity(i4);
				break;

			default:
				break;
		}
	}

}
