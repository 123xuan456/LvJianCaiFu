package com.ryan.slidefragment.generaldemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.domain.PullBean;

public class ListViewChanPin extends BaseActivity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv;
	private ImageView sick_title_left_img;
	private PullToRefreshListView pullToRefresh;
	// listView中item的数据 数据根据布局而定
	private List<PullBean> data = new ArrayList<PullBean>();
	MyAdapter adapter;

	@Override
	public void findView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_collection);
		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("建筑材料");
		sick_title_right_tv.setVisibility(View.GONE);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
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

					PullBean bean = new PullBean();
					bean.setName("上拉刷新");
					bean.setRenzheng("哈哈");
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
						Intent intent = new Intent(ListViewChanPin.this,
								BabyActivity.class);
						startActivity(intent);

					}
				});
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

	private List<PullBean> getData() {
		List<PullBean> list = new ArrayList<PullBean>();
		for (int i = 0; i < 10; i++) {
			PullBean bean = new PullBean();
			bean.setName("建筑材料");
			bean.setRenzheng("已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证已经经过本公司验证部门认证");
			bean.setSale_num("998");
			list.add(bean);
		}
		return list;
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
		public void addLast(PullBean bean) {
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
				convertView = mInflater.inflate(R.layout.chanpin_item_2, null);
				viewHolder.name = (TextView) convertView
						.findViewById(R.id.t_jishuzhanshi_xinwenbiaoti);
				viewHolder.renzheng = (TextView) convertView
						.findViewById(R.id.t_jishuzhanshi_xinwenneirong);

				// viewHolder.sale_num = (TextView) convertView
				// .findViewById(R.id.sale_num);
				// viewHolder.imageView1 = (View)
				// convertView.findViewById(R.id.imageView1);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			// viewHolder.title.setText(data.get(position).getTitle());
			// viewHolder.sale_num.setText(data.get(position).getSale_num());
			viewHolder.name.setText(data.get(position).getName());
			viewHolder.renzheng.setText(data.get(position).getRenzheng());
			// viewHolder.imageView1.setBackground(data.get(position).getPic());

			return convertView;
		}

		class ViewHolder {
			TextView sale_num;
			TextView name;
			TextView renzheng;
			// View imageView1;
		}

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sick_title_left_img:
			finish();
			break;

		default:
			break;
		}
	}

}