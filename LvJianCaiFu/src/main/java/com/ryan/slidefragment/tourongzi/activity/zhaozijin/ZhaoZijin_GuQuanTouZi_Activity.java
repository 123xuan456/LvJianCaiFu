package com.ryan.slidefragment.tourongzi.activity.zhaozijin;

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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.tourongzi.adapter.LiuYanAdapter;

public class ZhaoZijin_GuQuanTouZi_Activity extends Activity{
	private ImageView imageView1;//ͷ��
	private TextView mingzi,//����
	xingbie,//�Ա�
	provinceid,//ʡprovinceid
	cityid,//��cityid
	title,//����title
	textView5,//�����
	createtime,//ʱ��createtime
	zhutitypeid,textView2,//Ͷ������
	summoney,//Ͷ�ʽ��
	qixian,//����
	costtypeid,//ǰ�ڷ���
	stagetypeid,//Ͷ�ʽ׶�
	address,//Ͷ�ʵ���
	infotypeid,//Ͷ������
	hangyetypeid,//������ҵ
	bili,//����
	bili1,//����
	description,//��Ŀ����

	imageButton2,//Լ̸��Ŀ
	imageButton1;
	private String id;
	private ListView listView1;
	public List<Map<String, Object>> list ;
	private TextView sick_title_mid_tv, sick_title_right_tv,
			sick_title_left_tv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gu_quan);
		Intent i=getIntent();
		id = i.getStringExtra("id");
		System.out.println("id="+id);
		setView();
		getData(id);


	}
	private void setView() {
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
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
		imageButton1=(TextView)findViewById(R.id.imageButton1);	
		listView1 = (ListView) findViewById(R.id.listView1);//�����б�
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
				JSonData(result);

			}
			@Override
			public void onFailure(HttpException error, String msg) {
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
				System.out.println("sdhjkasdhas"+list);
				adapter = new LiuYanAdapter(getApplicationContext(), list);
				listView1.setAdapter(adapter);
				fixListViewHeight(listView1);
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

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
}
