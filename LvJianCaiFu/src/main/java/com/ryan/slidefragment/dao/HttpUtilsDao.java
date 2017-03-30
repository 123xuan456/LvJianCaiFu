package com.ryan.slidefragment.dao;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.options.Constants;

/**收藏网络加载类*/
public class HttpUtilsDao {
	private String id1;
	/**点击收藏时发送服务端*/
	public String sendServerParameter(String ww,String PATH) {
		String userID = BaseApplication.userID;
		//用来判断是否有次用户 
		System.out.println("用户id传递"+userID);

		String Url=Constants.SHOUCHANG_CHANGPINZHANSHI;
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("title",ww);
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
				try {
				id1=Json(result);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}


			@Override
			public void onFailure(HttpException error, String msg) {
				Log.i(msg, error.toString());
				System.out.println("传递失败了，宝贝");
			}
		});


		System.out.println("参数id1dad="+id1);
		return id1;
	}

	public String Json(String result) throws JSONException {
		JSONObject array;
		array = new JSONObject(result);
		
		id1 = array.getString("id");
		System.out.println("参数id1="+id1);
		return id1;

	}

	//取消收藏时发送服务器
	public void sendCancelServerParameter(String id) {
		String url1=Constants.SHOUCHANG_QUXIAO;
		RequestParams params = new RequestParams();
		System.out.println("参数id2有没有"+id);
		if (id==null) {
			params.addQueryStringParameter("id", id1);
		} else if (id==id1) {
			params.addQueryStringParameter("id", id);
		} else {
			params.addQueryStringParameter("id", id);
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

}
