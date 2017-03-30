package com.ryan.slidefragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ryan.slidefragment.adapter.MyAdapter_chengyuan;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.ChengYuan_CommonalityBean;
import com.ryan.slidefragment.generaldemo.GongSiActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.SharedPreferencesUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.JsonJudger;

import org.apache.http.NameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChengYuanGongSi_Listview_2 extends Fragment {
	private PullToRefreshListView pullToRefresh;
	private MyAdapter_chengyuan adapter;
	// listView中item的数据 数据根据布局而定
	public static int pno = 1;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	// 封装参数集合
	HashMap<String, String> upParamsMap = new HashMap<String, String>();
	List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	HashMap<String, Integer> params = new HashMap<String, Integer>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LinearLayout i = (LinearLayout) inflater.inflate(
				R.layout.chengyuangongsi_listview, container, false);
		list.clear();
		pullToRefresh = (PullToRefreshListView) i
				.findViewById(R.id.pullToRefresh);

		// 缓存当前页面数据
		String result = SharedPreferencesUtils.getString(getActivity(),
				Constants.CHENGYUANFULISHIZHANG, "");
		if (!TextUtils.isEmpty(result)) {
			setDate(result);
		} else {
			getdate(pno + "");// 接口
		}


		pullToRefresh.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				// 上拉刷新
				list.clear();
				if (pno < 1) {
					pno = 1;
				} else {
					pno--;
				}
				getdate(pno+"");
			}

			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

				// 加载更多
				// adapter.notifyDataSetChanged();
				// params.clear();
				list.clear();
				pno++;
				getdate(pno+"");
			}
		});

		/**
		 * item的点击事件
		 */
		pullToRefresh.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// Toast.makeText(
				// TongZhiGongGaoActivity.this,
				// "您点击了",
				// Toast.LENGTH_SHORT).show();
				Bundle mBundle = new Bundle();
				mBundle.putInt("id",Integer.parseInt(list.get(position - 1).get("id")));
				Intent mIntent = new Intent(getActivity(),GongSiActivity.class);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});
		return i;
	}

	/**
	 * 网络数据请求
	 *
	 * @return
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}
	private void getdate(String num) {
		final HashMap<String, String> map = new HashMap<String, String>();
		map.put("pno", num);
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				final String result = HttpClientDao.getListHttpClientPost(
						Constants.CHENGYUANFULISHIZHANG, map);
				boolean judger = JsonJudger.JsonJudger(result, "code", "200");
				if (judger) {
					ThreadUtils.post(new Runnable() {
						public void run() {
							SharedPreferencesUtils.saveString(getActivity(),
									Constants.CHENGYUANFULISHIZHANG, result);
							setDate(result);
						}
					});
				}
			}
		});
	}

	public void setDate(String result) {
		// TODO Auto-generated method stub
		ChengYuan_CommonalityBean chengyuan_commonalityBean = JsonUtils.parser(result,
				ChengYuan_CommonalityBean.class);

		String di = chengyuan_commonalityBean.di;
		for (ChengYuan_CommonalityBean.Date.Lishizhang item:chengyuan_commonalityBean.date.lishizhang) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", item.id+"");
			map.put("content", item.content);
			map.put("name", item.title);
			map.put("imgurl", item.imgurl);
			map.put("time", item.time);
			list.add(map);
		}
		if (di.equals("a")) {
			pullToRefresh.setMode(Mode.BOTH);

		}

		adapter = new MyAdapter_chengyuan(getActivity(), list);
		pullToRefresh.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		pullToRefresh.onRefreshComplete();

	}
}
