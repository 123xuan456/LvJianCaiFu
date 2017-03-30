package com.ryan.slidefragment.generaldemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ryan.slidefragment.domain.ChengyuanitemBean;

public class Gongsi_Chanpin_ListActivity extends Activity {
	private PullToRefreshListView pullToRefresh;
	// listView中item的数据 数据根据布局而定
	private List<ChengyuanitemBean> data = new ArrayList<ChengyuanitemBean>();
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gongsi__chanpin__list);
		pullToRefresh = (PullToRefreshListView)findViewById(R.id.pullToRefresh);
		data = getData();
		adapter = new MyAdapter(this);
		pullToRefresh.setAdapter(adapter);
		pullToRefresh.setMode(Mode.BOTH);
		init();
		pullToRefresh.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// PullBean bean = new PullBean();
				// bean.setName("下拉刷新");
				// bean.setRenzheng("我的个神");
				// adapter.addFirst(bean);

				new FinishRefresh().execute();
				adapter.notifyDataSetChanged();
			}

			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				for (int i = 0; i < 10; i++) {

					ChengyuanitemBean bean = new ChengyuanitemBean();
					bean.setT_gongsimingcheng("上拉刷新");
					bean.setT_gongsijianjie("哈哈");
					adapter.addLast(bean);
					new FinishRefresh().execute();
					adapter.notifyDataSetChanged();
				}
			}
		});

		// item的点击事件
		pullToRefresh
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Intent intent = new Intent(Gongsi_Chanpin_ListActivity.this,
								BabyActivity.class);
						startActivity(intent);

					}
				});

	}

	private List<ChengyuanitemBean> getData() {
		List<ChengyuanitemBean> list = new ArrayList<ChengyuanitemBean>();
		for (int i = 0; i < 10; i++) {
			ChengyuanitemBean bean = new ChengyuanitemBean();
			bean.setT_gongsimingcheng("item" + i + "测试标题");
			bean.setT_gongsijianjie("测试的假数据测试的假数据测试测试的假数据测试的假数据测试测试的假数据测试的假数据测试测试的假数据测试的假数据测试测试的假数据测试的假数据测试测试的假数据测试的假数据测试");
			list.add(bean);
		}
		return list;
	}

	private void init() {
		// TODO Auto-generated method stub
		ILoadingLayout startLabels = pullToRefresh.getLoadingLayoutProxy(true,
				false);
		startLabels.setPullLabel("下拉刷新。。。");
		startLabels.setRefreshingLabel("正在载入。。。");
		startLabels.setReleaseLabel("放开我，不要~~~");

		ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(false,
				true);
		endLabels.setPullLabel("上拉刷新。。。");
		endLabels.setRefreshingLabel("正在载入。。。");
		endLabels.setReleaseLabel("好的，我放开！！！");

		// 设置下拉刷新文本
		pullToRefresh.getLoadingLayoutProxy(false, true)
				.setPullLabel("上拉刷新。。。");
		pullToRefresh.getLoadingLayoutProxy(false, true).setReleaseLabel(
				"放开刷新。。。");
		pullToRefresh.getLoadingLayoutProxy(false, true).setRefreshingLabel(
				"正在加载...");

		// 设置上拉刷新文本
		pullToRefresh.getLoadingLayoutProxy(true, false)
				.setPullLabel("下拉刷新...");
		pullToRefresh.getLoadingLayoutProxy(true, false).setReleaseLabel(
				"放开刷新...");
		pullToRefresh.getLoadingLayoutProxy(true, false).setRefreshingLabel(
				"正在加载...");

	}

	private class FinishRefresh extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			pullToRefresh.onRefreshComplete();
		}

	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			// TODO Auto-generated constructor stub
			mInflater = LayoutInflater.from(context);
		}

		// listView上边刷新时，刷新出来的
		// public void addFirst(PullBean bean){
		// data.add(0, bean);
		// }
		// listView下边刷新时，刷新出来的
		public void addLast(ChengyuanitemBean bean) {
			// getData();
			data.add(bean);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder = null;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.gongsichanpin_item,
						null);
				viewHolder.t_gongsimingcheng_neirong = (TextView) convertView
						.findViewById(R.id.t_gongsimingcheng_neirong);
				viewHolder.t_gongsijianjie_neirong = (TextView) convertView
						.findViewById(R.id.t_gongsijianjie_neirong);

				// viewHolder.imageView1 = (View)
				// convertView.findViewById(R.id.imageView1);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			// viewHolder.title.setText(data.get(position).getTitle());
			viewHolder.t_gongsimingcheng_neirong.setText(data.get(position)
					.getT_gongsimingcheng());
			viewHolder.t_gongsijianjie_neirong.setText(data.get(position)
					.getT_gongsijianjie());
			// viewHolder.imageView1.setBackground(data.get(position).getPic());

			return convertView;
		}

		class ViewHolder {
			TextView t_gongsimingcheng_neirong;
			TextView t_gongsijianjie_neirong;
			// View imageView1;
		}

	}
}
