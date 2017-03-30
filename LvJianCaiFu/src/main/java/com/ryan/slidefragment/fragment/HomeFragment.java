package com.ryan.slidefragment.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.ryan.slidefragment.adapter.XinWenListViewAdapter;
import com.ryan.slidefragment.dao.GlideImageLoader;
import com.ryan.slidefragment.generaldemo.CGAL_xinwenxiangqingActivity;
import com.ryan.slidefragment.generaldemo.ChengGongAnLiActivity;
import com.ryan.slidefragment.generaldemo.HangYeZiXunActivity;
import com.ryan.slidefragment.generaldemo.JiShuZhanShiActivity;
import com.ryan.slidefragment.generaldemo.ListViewLianMengXinWen;
import com.ryan.slidefragment.generaldemo.QuanBuChanPinActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.TongZhiGongGaoActivity;
import com.ryan.slidefragment.options.Constants;
import com.youth.banner.Banner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 首页
 *
 * @author Lyz
 *
 */
public class HomeFragment extends Fragment implements OnClickListener{
	private LinearLayout ll,jiShuZhanShi,hangyezixun, chenggonganli , tongzhigonggao, quanbuchanpin;
	private LinearLayout ll_images;

	private TextView gengduoxinwen,zhongyaoxinwen,tv_chjiashu,tv_jishu_jian,tv_jishu_cheng,tv_cases;
	private TextView tv_quanbuchanpin,tv_allthe,tv_products,tv_hangyezixun,tv_industry,tv_information;
	private TextView tv_tongzhigonggao,tv_announce,tv_ments,tv_chenggonganli,tv_Successfulcases;
	//联盟新闻
	private ListView lianMengXinWenListView;
	private List<Map<String, Object>> xinwenlist = new ArrayList<Map<String, Object>>();


	private ImageView iv_jishuzhenshe,iv_quanbuchanpin,iv_hangyezunxun,iv_tongzhigonggao,iv_chenggonganli;

	String url2;
	private Banner banner;

	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate( R.layout.viewpage,container, false);

		url2= Constants.SHOUYEXINWEN;
		String url3 = Constants.SHOUYEZHONGYAOXINWEN;
		Zhongyaoxinwen zyxw = new Zhongyaoxinwen();
		zyxw.execute(url3);
		Xinwen x = new Xinwen();
		x.execute(url2);

		zhongyaoxinwen = (TextView) view.findViewById(R.id.zhongyaoxinwen);
		gengduoxinwen = (TextView) view.findViewById(R.id.gengduoxinwen);
		gengduoxinwen.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent7 = new Intent(getActivity(),
						ListViewLianMengXinWen.class);
				startActivity(intent7);
			}
		});
		ll = (LinearLayout) view.findViewById(R.id.ll);
		lianMengXinWenListView=(ListView) view.findViewById(R.id.LianMengXinWenListView);
		lianMengXinWenListView.setAdapter(listViewAdapter);
		lianMengXinWenListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				System.out.println("xinwenlist="+xinwenlist.toString());
				System.out.println("你点击了"+"position="+position);
				System.out.println("你点击了"+"id="+id);
				try {
					String id1=xinwenlist.get(position).get("id").toString();
					Log.i("HomeFragmentId=", id1);

					Intent mIntent = new Intent(getActivity(),
							CGAL_xinwenxiangqingActivity.class);
					mIntent.putExtra("id",id1);
					startActivity(mIntent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});

		//5个线性布局
		jiShuZhanShi=(LinearLayout) view.findViewById(R.id.JiShuZhanShi);
		hangyezixun=(LinearLayout) view.findViewById(R.id.HangYeZiXun);
		chenggonganli=(LinearLayout) view.findViewById(R.id.ChengGongAnLi);
		tongzhigonggao=(LinearLayout) view.findViewById(R.id.TongZhiGongGao);
		quanbuchanpin=(LinearLayout) view.findViewById(R.id.QuanBuChanPin);

		//全部产品
		iv_quanbuchanpin = (ImageView) view.findViewById(R.id.iv_quanbuchanpin);
		tv_quanbuchanpin = (TextView) view.findViewById(R.id.tv_quanbuchanpin);
		tv_allthe = (TextView) view.findViewById(R.id.tv_allthe);
		tv_products = (TextView) view.findViewById(R.id.tv_products);

		//行业资讯
		iv_hangyezunxun =  (ImageView) view.findViewById(R.id.iv_hangyezunxun);
		tv_hangyezixun = (TextView) view.findViewById(R.id.tv_hangyezixun);
		tv_industry = (TextView) view.findViewById(R.id.tv_industry);
		tv_information = (TextView) view.findViewById(R.id.tv_information);

		//通知公告
		iv_tongzhigonggao = (ImageView) view.findViewById(R.id.iv_tongzhigonggao);
		tv_tongzhigonggao = (TextView) view.findViewById(R.id.tv_tongzhigonggao);
		tv_announce = (TextView) view.findViewById(R.id.tv_announce);
		tv_ments = (TextView) view.findViewById(R.id.tv_ments);

		//成功案例
		iv_chenggonganli = (ImageView) view.findViewById(R.id.iv_chenggonganli);
		tv_chenggonganli = (TextView) view.findViewById(R.id.tv_chenggonganli);
		tv_Successfulcases = (TextView) view.findViewById(R.id.tv_Successfulcases);
		tv_cases = (TextView) view.findViewById(R.id.tv_cases);

		//技术展示
		iv_jishuzhenshe = (ImageView) view.findViewById(R.id.iv_jishuzhenshe);
		tv_chjiashu = (TextView) view.findViewById(R.id.tv_chjiashu);
		tv_jishu_jian = (TextView) view.findViewById(R.id.tv_jishu_jian);
		tv_jishu_cheng = (TextView) view.findViewById(R.id.tv_jishu_cheng);

		jiShuZhanShi.setOnClickListener(this);
		hangyezixun.setOnClickListener(this);
		chenggonganli.setOnClickListener(this);
		tongzhigonggao.setOnClickListener(this);
		quanbuchanpin.setOnClickListener(this);

		banner=(Banner) view.findViewById(R.id.banner);
		lunbo();
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		banner.startAutoPlay();
	}

	@Override
	public void onStop() {
		super.onStop();
		banner.stopAutoPlay();
	}

	private void lunbo() {
		String url4 = Constants.FOCUS_URL;

		OkGo.get(url4).tag(this).execute(new StringCallback() {
			@Override
			public void onSuccess(String s, Call call, Response response) {
				String[] urls= {
						"http://106.2.219.210:8088/upload/1470820168759.jpg",
						"http://106.2.219.210:8088/upload/1470820115534.png",
						"http://106.2.219.210:8088/upload/1470820102550.png",
						"http://106.2.219.210:8088/upload/1470820092755.png",
						"http://106.2.219.210:8088/upload/1470820084012.png"
				};
				List list = Arrays.asList(urls);
				ArrayList images = new ArrayList(list);
				//banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
				//设置图片集合
				banner.setImages(images);
				//设置图片加载器
				banner.setImageLoader(new GlideImageLoader());
				banner.start();

			}

		});


//		HttpUtils http=new HttpUtils();
//		http.send(HttpRequest.HttpMethod.GET,
//				url4,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onSuccess(ResponseInfo<String> responseInfo) {
//						String result = responseInfo.result.toString();
//						System.out.println("参数成功?" + result);
////						Gson gson=new Gson();
////						LunbotuBean lunbo = gson.fromJson(result, LunbotuBean.class);
////						LunbotuBean.DateBean date = lunbo.getDate();
////						List<LunbotuBean.DateBean.NameBean> n = date.getName();
//
//
//						String[] a={
//								"http://106.2.219.210:8088/upload/1470820168759.jpg",
//								"http://106.2.219.210:8088/upload/1470820115534.png",
//								"http://106.2.219.210:8088/upload/1470820102550.png",
//								"http://106.2.219.210:8088/upload/1470820092755.png",
//								"http://106.2.219.210:8088/upload/1470820084012.png"
//						};
//
//
////						for (int i=0;i<n.size();i++){
////							LunbotuBean.DateBean.NameBean n1 = n.get(i);
////							String s1 =n1.getUrlimg();
////							list.add(s1);
////						}
//						List list = Arrays.asList(a);
//						ArrayList list1=new ArrayList(list);
//						Log.d("HomeFragment","list="+ a.toString());
//						banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//						banner.setImages(list1);
//						//设置图片加载器
//						banner.setImageLoader(new GlideImageLoader());
//						banner.start();
//
//					}
//					@Override
//					public void onFailure(HttpException error, String msg) {
//						System.out.println("传递失败");
//					}
//				});

	}


	class Zhongyaoxinwen extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j_2 = new JSONObject(result);
				JSONObject jj1_2 = j_2.getJSONObject("date");
				JSONObject jj1_3 = jj1_2.getJSONObject("tuisong");
				String s1_2 = jj1_3.getString("name");
				System.out.println(s1_2);
				zhongyaoxinwen.setText(s1_2);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}



	private XinWenListViewAdapter listViewAdapter;
	String title;
	private int id2;
	private HashMap<String, Object> map1;
	class Xinwen extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			HttpPost post = new HttpPost(params[0]);
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

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {

				JSONObject j_xinwen = new JSONObject(result);
				JSONObject jj1_xinwen = j_xinwen.getJSONObject("date");
				//
				JSONArray a1 = jj1_xinwen.getJSONArray("xinwen");
				for (int i=0;i<a1.length();i++) {
					JSONObject j=a1.getJSONObject(i);
					title=j.getString("title");
					int ntypeId=j.optInt("ntypeId");
					id2=j.optInt("id");
					Log.i("新闻首页title", title);
					System.out.println(id2);
					System.out.println(ntypeId);
					map1=new HashMap<String, Object>();
					map1.put("id", id2);
					map1.put("ntypeId", ntypeId);
					map1.put("title", title);
					xinwenlist.add(map1);
					//已经取到数据[{id=893, ntypeId=2, title=持续雾霾“新风系统”空气净化装置走俏 不适合所有家庭}]
					//System.out.println("result="+result);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listViewAdapter =new XinWenListViewAdapter(getActivity(), xinwenlist);
			lianMengXinWenListView.setAdapter(listViewAdapter);
			listViewAdapter.notifyDataSetChanged();

		}

	}


	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.JiShuZhanShi:
				Intent i0 = new Intent(getActivity(),
						JiShuZhanShiActivity.class);
				startActivity(i0);
				break;
			case R.id.HangYeZiXun:
				Intent i1 = new Intent(getActivity(),
						HangYeZiXunActivity.class);
				startActivity(i1);
				break;
			case R.id.ChengGongAnLi:
				Intent i3 = new Intent(getActivity(),
						ChengGongAnLiActivity.class);
				startActivity(i3);
				break;
			case R.id.TongZhiGongGao:
				Intent i4 = new Intent(getActivity(),
						TongZhiGongGaoActivity.class);
				startActivity(i4);
				break;
			case R.id.QuanBuChanPin:
				Intent i5 = new Intent(getActivity(),
						QuanBuChanPinActivity.class);
				startActivity(i5);
				break;

			default:
				break;
		}
	}

}