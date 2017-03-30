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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.dao.HttpUtilsDao;
import com.ryan.slidefragment.domain.ShouYeXinWenXiangQingBean;
import com.ryan.slidefragment.generaldemo.BabyActivity.Gridview;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;

public class ChenggonganlixiangqingActivity extends Activity implements
OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv, xinwen_biaoti,
	xinwen_neirong,xinwen_liulanliang,sick_title_left_tv;
	private ImageView sick_title_left_img,iv_baby_collection_1,iv_baby_collection;
	private ShouYeXinWenXiangQingBean syxwxq;
	public String ww, ee,qq;
	String ids;
	private String aab2;
	private String aab1;
	private String aab;
	private String PATH;
	private String id;
	private boolean a;
	private String id1;
	private HttpUtilsDao dao;
	HashMap<String, String> params1 = new HashMap<String, String>();
	private Object s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_limengxinwengengduozhengwen);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(this);
		xinwen_liulanliang = (TextView) findViewById(R.id.xinwen_liulanliang);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		xinwen_biaoti = (TextView) findViewById(R.id.xinwen_biaoti);
		xinwen_neirong = (TextView) findViewById(R.id.xinwen_neirong);

		iv_baby_collection = (ImageView)
				findViewById(R.id.iv_baby_collection);
		iv_baby_collection_1 = (ImageView)
				findViewById(R.id.iv_baby_collection_1);
		iv_baby_collection.setOnClickListener(this);
		iv_baby_collection_1.setOnClickListener(this);

		xinwen_biaoti.setOnClickListener(this);
		xinwen_neirong.setOnClickListener(this);
		sick_title_left_img.setOnClickListener(this);
		Person mPerson = (Person) getIntent().getSerializableExtra("pr");
		ids = (String) mPerson.getName();
		System.out.println("------ids-------" + ids);
		System.out.println(mPerson.getName() + "4444444444");
		sick_title_mid_tv.setText("正文");
		sick_title_right_tv.setVisibility(View.GONE);
		params1.put("id", ids + "");
		Gridview gWeather = new Gridview();
		gWeather.execute(getUrl(params1));
		s=BaseApplication.name;
	//	BaseApplication.myId = null;
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
		case R.id.iv_baby_collection:
			if (s!=null) {
				
			
			iv_baby_collection.setVisibility(View.GONE);
			iv_baby_collection_1.setVisibility(View.VISIBLE);
			//向服务器发请求
				dao=new HttpUtilsDao();
				dao.sendServerParameter(ww, PATH);
				System.out.println("id1="+id1);
				Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
			}
			//sendServerParameter();
			break;
			//点击取消收藏
		case R.id.iv_baby_collection_1:
			// 提示是否取消收藏
			cancelCollection();
			break;

		default:
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
			
			/*注释：因为只有当点击了收藏按钮事件的时候才会触发dao.sendServerParameter(ww, PATH);这样才能得到id1，
			 * 只有在未收藏状态时点击了收藏按钮后再点击取消收藏才能吧id1传递，但是如果是已经收藏过的，显示已收藏按钮的时候再点击取消收藏就会崩掉。
			 * 所以只能暂时不使用才方法*/
			//	dao.sendCancelServerParameter(id1);
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
	
	/**
	 * 拿到拼接后的url
	 */
	private String getUrl(HashMap<String, String> params) {
		PATH= Constants.CHENGGONGANLIXIANGQING;
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
		//	JSONObject array = null;

			super.onPostExecute(result);
			try {
				System.out.println("收藏返回数据="+result);
				syxwxq = JsonUtils.parser(result,
						ShouYeXinWenXiangQingBean.class);
				CacheUtils.putString("rows", syxwxq.getContent());
				ww = syxwxq.getTitle() + "";
				ee = syxwxq.getContent() + "";
				qq = syxwxq.getClick() + "";
				xinwen_biaoti.setText(ww);
				xinwen_neirong.setText(ee);
				xinwen_liulanliang.setText(qq);
				id = syxwxq.getId();
				System.out.println("收藏成功拿到的信息id="+id);//只有点击收藏之后才会生成，第一次打开是没有的，这个作为以后判断取消收藏
				a = syxwxq.isA();//返回的true用来判断页面上面的收藏或未收藏
				System.out.println("收藏返回boolean="+a);
				if(a==true){
					iv_baby_collection.setVisibility(View.GONE);
					iv_baby_collection_1.setVisibility(View.VISIBLE);
				}else {
					iv_baby_collection.setVisibility(View.VISIBLE);
					iv_baby_collection_1.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// adapter.notifyDataSetChanged();

		}

	}
}
