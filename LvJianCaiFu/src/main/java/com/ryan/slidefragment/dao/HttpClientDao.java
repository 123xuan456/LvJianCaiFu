package com.ryan.slidefragment.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ryan.slidefragment.utils.StreamTools;

public class HttpClientDao {

	
	public static String getListHttpClientPost(String url,HashMap<String,String> upParamsMap){
		//1.创建HttpClient 的实例
		HttpClient client = new DefaultHttpClient();
		
		//2.创建某种连接方法的实例，在这里是HttpPost。在HttpPost 的构造函数中传入待连接的地址
		String uri = url;
		HttpPost httpPost = new HttpPost(uri);
//		 httpPost.addHeader("Content-Type","text/html;charset=UTF-8");
		try {
			//封装传递参数的集合
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			//往这个集合中添加你要传递的参数
			
			Set<String> keySet = upParamsMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String k = (String) iterator.next();
				String value = upParamsMap.get(k);
				parameters.add(new BasicNameValuePair(k, value));
//				Log.i(LevelItemisClick.LevelClickTag, "K:" + k + " V:" + value + ":被上传了");
			}
			//创建传递参数封装 实例对象
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,"UTF-8");//设置传递参数的编码
			//把实体对象存入到httpPost对象中
			httpPost.setEntity(entity);
			//3.调用第一步中创建好的实例的execute 方法来执行第二步中创建好的method 实例
			HttpResponse response = client.execute(httpPost);//HttpUriRequest的后代对象
			//4.读response
			if (response.getStatusLine().getStatusCode()==200) {//判断状态码
				InputStream is = response.getEntity().getContent();//获取内容
				String result = StreamTools.streamToStr(is);//通过工具类转换文本
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//6.释放连接。无论执行方法是否成功，都必须释放连接
			client.getConnectionManager().shutdown();
		}
		//如果网络连接失败，超时等异常情况会返回0
		return "0";
		
	}
}
