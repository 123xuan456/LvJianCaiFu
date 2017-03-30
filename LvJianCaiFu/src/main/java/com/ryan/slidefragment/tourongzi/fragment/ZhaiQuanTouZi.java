package com.ryan.slidefragment.tourongzi.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.activity.zhaozijin.ZhaoZijin_GuQuanTouZi_Activity;
import com.ryan.slidefragment.tourongzi.adapter.TouziAdapter;

public class ZhaiQuanTouZi extends Fragment{
	private PullToRefreshListView plistView;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	TouziAdapter adapter;
	HashMap<String, Integer> params = new HashMap<String, Integer>();
	private int pno=1;
	private ProgressDialog dialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		LinearLayout l=(LinearLayout) inflater.inflate(R.layout.fragment_zhaiquantouzi, null);
		plistView = (PullToRefreshListView)l.findViewById(R.id.pullToRefresh);
		params.put("pno", pno);

		dialog = new ProgressDialog(getActivity());
		dialog.setTitle("提示");
		dialog.setMessage("正在加载...");

		String s = getUrl(params);

		getData(s);
		System.out.print("asAS="+s);
		plistView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				list.clear();
//				params.clear();
				if (pno < 2) {
					pno = 1;
				} else {
					pno--;
				}
				params.put("pno", pno);
				String s1 = getUrl(params);
				System.out.print("asAS="+s1);
				getData(s1);
				adapter.notifyDataSetChanged();


			}

			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				// 加载更多
				//			 params.clear();
				list.clear();
				pno++;
				params.put("pno", pno);
				String s1 = getUrl(params);
				System.out.print("asASsdadsd="+s1);
				getData(s1);
				adapter.notifyDataSetChanged();
			}
		});

		plistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				String id1=list.get(position-1).get("id").toString();
				Intent mIntent = new Intent(getActivity(),
						ZhaoZijin_GuQuanTouZi_Activity.class);
				mIntent.putExtra("id", id1);
				startActivity(mIntent);
			}
		});
		return l;
	}
	//	String url =Constants.ZHAIQUANTOUZI_LISTVIEW;
//	AllGetUrl aa = new AllGetUrl(params, url);
	private String getUrl(HashMap<String, Integer> params) {
		String url =Constants.ZHAIQUANTOUZI_LISTVIEW;
		// 添加url参数
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			StringBuffer sb = null;
			while (it.hasNext()) {
				String key = it.next();
				Integer value = params.get(key);
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
			url += sb.toString();
		}
		return url;
	}




	private void getData(String url) {

		HttpUtils http=new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				url,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result.toString() ;
						System.out.println("参数成功?"+result);
						dialog.show();
						JSonData(result);

					}
					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("传递失败");
					}
				});
	}

	protected void JSonData(String result) {
		JSONObject ob;
		try {
			ob = new JSONObject(result);
			JSONObject o=ob.getJSONObject("date");
			String nodatatag = ob.getString("di");
			JSONArray ary=o.getJSONArray("zhaiquan");
			for (int i = 0; i < ary.length(); i++) {
				JSONObject obj=ary.getJSONObject(i);
				String address=obj.getString("address");
				String areaid=obj.getString("areaid");
				String bili=obj.getString("bili");
				String cases=obj.getString("cases");
				String cityid=obj.getString("cityid");
				String costtypeid=obj.getString("costtypeid");
				String createtime=obj.getString("createtime");
				String datumtypeid=obj.getString("datumtypeid");
				String description=obj.getString("description");
				String fangshitypeid=obj.getString("fangshitypeid");
				String hangyetypeid=obj.getString("hangyetypeid");
				int id=obj.getInt("id");
				String image=obj.getString("image");
				String infotypeid=obj.getString("infotypeid");
				String intenttypeid=obj.getString("intenttypeid");
				String provinceid=obj.getString("provinceid");
				String qixian=obj.getString("qixian");
				String remarks=obj.getString("remarks");
				String stagetypeid=obj.getString("stagetypeid");
				String summoney=obj.getString("summoney");
				int tiaoshu=obj.getInt("tiaoshu");
				String title=obj.getString("title");
				String zhutitypeid=obj.getString("zhutitypeid");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("address", address);
				map.put("bili", bili);
				map.put("cases", cases);
				map.put("cityid", cityid);
				map.put("costtypeid", costtypeid);
				map.put("areaid", areaid);
				map.put("createtime", createtime);
				map.put("datumtypeid", datumtypeid);
				map.put("description", description);
				map.put("fangshitypeid", fangshitypeid);
				map.put("hangyetypeid", hangyetypeid);
				map.put("image", image);
				map.put("infotypeid", infotypeid);
				map.put("intenttypeid", intenttypeid);
				map.put("provinceid", provinceid);
				map.put("stagetypeid", stagetypeid);
				map.put("qixian", qixian);
				map.put("remarks", remarks);
				map.put("summoney", summoney);
				map.put("tiaoshu", tiaoshu);
				map.put("title", title);
				map.put("zhutitypeid", zhutitypeid);

				list.add(map);

			}
			adapter=new TouziAdapter(getActivity(),list);
			plistView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			dialog.dismiss();
			plistView.onRefreshComplete();
			if (nodatatag.equals("a")) {
				plistView.setMode(Mode.BOTH);

			} else {
				Toast.makeText(getActivity(), "没有更多数据了",
						Toast.LENGTH_SHORT).show();
				// pullToRefresh.setMode(Mode.BOTH);
				// pullToRefresh.setMode(Mode.PULL_FROM_START);
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}

	};



}
