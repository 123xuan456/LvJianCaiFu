package com.ryan.slidefragment.tourongzi.activity.zhaozijin;

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
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.R.id;
import com.ryan.slidefragment.generaldemo.R.layout;
import com.ryan.slidefragment.tourongzi.adapter.LiuYanAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhaoZijinXiangqingActivity extends Activity implements View.OnClickListener{

	private ImageView imageView1;//头像
	private TextView mingzi,//名字
	xingbie,//性别
	provinceid,//省provinceid
	cityid,//市cityid
	title,//标题title
	textView5,//浏览量
	createtime,//时间createtime
	zhutitypeid,textView2,//投资主体
	summoney,//投资金额
	qixian,//期限
	costtypeid,//前期费用
	stagetypeid,//投资阶段
	address,//投资地区
	infotypeid,//投资类型
	hangyetypeid,//所属行业
	bili,//比例
	bili1,//年限
	description,//项目概述

	imageButton2,//约谈项目
	imageButton1;//投递名片
	private String id;
	private ListView listView1;
	public List<Map<String, Object>> list ;

	private TextView sick_title_mid_tv, sick_title_right_tv,
			sick_title_left_tv;
	private ImageView sick_title_left_img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gu_quan);

		Intent i=getIntent();
		id = i.getStringExtra("id");
		System.out.println("id=" + id);
		setView();
		getData(id);
		adapter = new LiuYanAdapter(getApplicationContext(), list);
		listView1.setAdapter(adapter);
		fixListViewHeight(listView1);

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
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_mid_tv.setText("详情");
		sick_title_right_tv.setVisibility(View.GONE);


		mingzi=(TextView)findViewById(R.id.mingzi);	
		xingbie=(TextView)findViewById(R.id.xingbie);	
		provinceid=(TextView)findViewById(R.id.provinceid);	
		cityid=(TextView)findViewById(R.id.cityid);	
		title=(TextView)findViewById(R.id.title);	
		textView5=(TextView)findViewById(R.id.textView5);	
		createtime=(TextView)findViewById(R.id.createtime);	
		zhutitypeid=(TextView)findViewById(R.id.zhutitypeid);	
		textView2=(TextView)findViewById(R.id.textView2);	
		summoney=(TextView)findViewById(R.id.summoney);	
		qixian=(TextView)findViewById(R.id.qixian);	
		costtypeid=(TextView)findViewById(R.id.costtypeid);	
		stagetypeid=(TextView)findViewById(R.id.stagetypeid);	
		address=(TextView)findViewById(R.id.address);	
		infotypeid=(TextView)findViewById(R.id.infotypeid);	
		hangyetypeid=(TextView)findViewById(R.id.hangyetypeid);	
		bili=(TextView)findViewById(R.id.bili);	
		bili1=(TextView)findViewById(R.id.bili1);	
		description=(TextView)findViewById(R.id.description);	
		imageButton2=(TextView)findViewById(R.id.imageButton2);	
		imageButton1=(TextView)findViewById(R.id.imageButton1);	//投递名片
		listView1 = (ListView) findViewById(R.id.listView1);//留言列表
	}


	private void getData(String id2) {
		String url = com.ryan.slidefragment.options.Constants.GUQUANTOUZI_LISTVIEW_XiangQing+id2;
		HttpUtils http=new HttpUtils();
		http.send(HttpRequest.HttpMethod.GET,
				url,
				new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result.toString() ;
				System.out.println("参数成功?"+result);
				JSonData(result);

			}
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("传递失败");
			}
		});



	}

	private LiuYanAdapter adapter;

	protected void JSonData(String result) {
		list=new ArrayList<Map<String, Object>>();
		JSONObject obj;
		try {
			obj = new JSONObject(result);
			String address1=obj.getString("address");
			String areaid1=obj.getString("areaid");
			String bili_1=obj.getString("bili");
			String cases1=obj.getString("cases");
			String cityid1=obj.getString("cityid");
			String costtypeid1=obj.getString("costtypeid");
			String datumtypeid1=obj.getString("datumtypeid");
			String description1=obj.getString("description");
			String hangyetypeid1=obj.getString("hangyetypeid");
			String image1=obj.getString("image");
			String infotypeid1=obj.getString("infotypeid");
			String intenttypeid1=obj.getString("intenttypeid");
			String provinceid1=obj.getString("provinceid");
			String qixian1=obj.getString("qixian");
			String remarks1=obj.getString("remarks");
			String stagetypeid1=obj.getString("stagetypeid");
			String summoney1=obj.getString("summoney");
			String title1=obj.getString("title");
			String mingzi1=obj.getString("mingzi");
			String xingbie1=obj.getString("xingbie");
			String zhutitypeid1=obj.getString("zhutitypeid");
			JSONArray liuyan = obj.getJSONArray("lsitzhaozijinliuyan");
			for (int i = 0; i < liuyan.length(); i++) {
				JSONObject ob=liuyan.getJSONObject(i);
				String capid=ob.getString("capid");  	
				String content=ob.getString("content");   
				String id=ob.getString("id"); 
				String usersid=ob.getString("usersid");   
				String bie=ob.getString("bie");   
				String ming=ob.getString("ming");   
				String site=ob.getString("site");   
				String url=ob.getString("url");   
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("capid", capid);
				map.put("content", content);
				map.put("bie", bie);
				map.put("ming", ming);
				map.put("usersid", usersid);
				map.put("site", site);
				map.put("url", url);

				list.add(map);
				System.out.println("sdhjkasdhas" + list);
			}

			System.out.println("sdhjkasdhass"+list);
			address.setText(address1);
			mingzi.setText(mingzi1);	
			xingbie.setText(xingbie1);
			provinceid.setText(provinceid1);
			cityid.setText(cityid1);
			title.setText(title1);
			zhutitypeid.setText(zhutitypeid1);	
			textView2.setText(zhutitypeid1);
			summoney.setText(summoney1);
			qixian.setText(qixian1);
			costtypeid.setText(costtypeid1);
			stagetypeid.setText(stagetypeid1);	
			address.setText(address1);
			infotypeid.setText(infotypeid1);
			hangyetypeid.setText(hangyetypeid1);
			bili.setText(bili_1);
			description.setText(description1);	
			//			map.put("id", id);
			//			map.put("address", address);
			//			map.put("bili", bili);
			//			map.put("cases", cases);
			//			map.put("cityid", cityid);
			//			map.put("costtypeid", costtypeid);
			//			map.put("areaid", areaid);
			//			map.put("createtime", createtime);
			//			map.put("datumtypeid", datumtypeid);
			//			map.put("description", description);
			//			map.put("fangshitypeid", fangshitypeid);
			//			map.put("hangyetypeid", hangyetypeid);
			//			map.put("image", image);
			//			map.put("infotypeid", infotypeid);
			//			map.put("intenttypeid", intenttypeid);
			//			map.put("provinceid", provinceid);
			//			map.put("stagetypeid", stagetypeid);
			//			map.put("qixian", qixian);
			//			map.put("remarks", remarks);
			//			map.put("summoney", summoney);
			//			map.put("title", title);
			//			map.put("zhutitypeid", zhutitypeid);
			//			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public void fixListViewHeight(ListView listView) {   
		// 如果没有设置数据适配器，则ListView没有子项，返回。  
		//   ListAdapter listAdapter = listView.getAdapter();  
		int totalHeight = 0;   
		if (adapter == null) {   
			return;   
		}   
		for (int i = 0, len = adapter.getCount(); i < len; i++) {     
			View listViewItem = adapter.getView(i , null, listView);  
			// 计算子项View 的宽高   
			listViewItem.measure(0, 0);    
			// 计算所有子项的高度和
			totalHeight += listViewItem.getMeasuredHeight();    
		}   

		ViewGroup.LayoutParams params = listView.getLayoutParams();   
		// listView.getDividerHeight()获取子项间分隔符的高度   
		// params.height设置ListView完全显示需要的高度    
		params.height = totalHeight+ (listView.getDividerHeight() * (adapter.getCount() - 1));   
		listView.setLayoutParams(params);   
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.imageButton1:
				Toast.makeText(getApplicationContext(),"暂未开发",Toast.LENGTH_LONG).show();
			break;

		}
		


	}
}
