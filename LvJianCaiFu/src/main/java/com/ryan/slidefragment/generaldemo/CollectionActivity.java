package com.ryan.slidefragment.generaldemo;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ryan.slidefragment.base.BaseActivity;
import com.ryan.slidefragment.domain.PullBean;
/**我的收藏列表*/
public class CollectionActivity extends BaseActivity implements OnClickListener{
	private PullToRefreshListView pullToRefresh;
	private List<PullBean> data = new ArrayList<PullBean>();
	List<PullBean> list = new ArrayList<PullBean>();
	MyAdapter adapter;
	private TextView sick_title_mid_tv, sick_title_right_tv;
	private LinearLayout sick_titel_left_layout;
	private RequestQueue requestQueue;

	@Override
	public void findView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_collection);
		pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		// 返回键
		sick_titel_left_layout=(LinearLayout)findViewById(R.id.sick_titel_left_layout);
		requestQueue = Volley.newRequestQueue(this);  
		
	}

	@Override
	public void addListener() {
		sick_title_mid_tv.setText("我的收藏");
		sick_title_right_tv.setVisibility(View.GONE);
		sick_titel_left_layout.setOnClickListener(this);
	}

	@Override
	public void initView() {


	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		getJSONByVolley();
		
//		adapter = new MyAdapter(this);
//		pullToRefresh.setAdapter(adapter);
//		pullToRefresh.setMode(Mode.BOTH);
		//init();


//		pullToRefresh.setOnRefreshListener(new OnRefreshListener2<ListView>() {
//
//			public void onPullDownToRefresh(
//					PullToRefreshBase<ListView> refreshView) {
//				new FinishRefresh().execute();
//				adapter.notifyDataSetChanged();
//			}
//
//			public void onPullUpToRefresh(
//					PullToRefreshBase<ListView> refreshView) {
//				new FinishRefresh().execute();
//				adapter.notifyDataSetChanged();
//
//			}
//		});



//		pullToRefresh.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				String url1 = data.get(position-1).geturl();
//				System.out.println("url1dasdsa="+url1);
//				Intent i=new Intent(CollectionActivity.this,BabyActivity_1.class);
//				i.putExtra("url1", url1);
//						startActivity(i);
//			}
//		});
		pullToRefresh.getRefreshableView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long a) {
				String id = data.get(position-1).getId();
				System.out.println("argposition="+position);
				System.out.println("id="+id);
				sendDelte(id);
				return true;
			}
		});

	}

		protected void sendDelte(final String id) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("是否取消收藏");
			dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					//发送给服务器
					delete(id);
				}
			});
			dialog.setNegativeButton("取消", null);
			dialog.create().show();
	}

		protected void delete(String id) {
			String url =com.ryan.slidefragment.options.Constants.SHOUCHANG_shanchu+id;
			System.out.println("url="+url);
			data.removeAll(data);
			JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {  
	            @Override  
	            public void onResponse(JSONObject response) {  
	            	Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
	            	getJSONByVolley();//删除之后再重新刷新数据
	            }  
	        },new Response.ErrorListener() {  
	            @Override  
	            public void onErrorResponse(VolleyError error) {  
	            }  
	        });  
			requestQueue.add(jr);   			
		}

		private List<PullBean> getData(JSONObject response){
			try {
				JSONArray ary=response.getJSONArray("shoucang");
				for (int i = 0; i < ary.length(); i++) {
					JSONObject obj=ary.getJSONObject(i);
					String Title=obj.getString("title");//显示的数据
					String url=obj.getString("url");//跳转的连接
					String id=obj.getString("id");
					System.out.println("标题"+Title.toString());
					System.out.println("asd"+url.toString());
					PullBean bean = new PullBean();
					bean.setTitle(Title);
					bean.seturl(url);
					bean.setId(id);
					list.add(bean);
					System.out.println("daaa"+list);
					adapter = new MyAdapter(this);
					pullToRefresh.setAdapter(adapter);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}

		private void getJSONByVolley() {  
			String JSONDataUrl =com.ryan.slidefragment.options.Constants.SHOUCHANG_1;  
			final ProgressDialog progressDialog = ProgressDialog.show(this, "别着急", "...Loading...");  
			JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(  
					Request.Method.POST,   
					JSONDataUrl,   
					null, 
					new Response.Listener<JSONObject>() {  
						public void onResponse(JSONObject response) {  
							System.out.println("response="+response);  
							if (progressDialog.isShowing()&&progressDialog!=null) {  
								progressDialog.dismiss();  
							}  
							data =getData(response);
						}  
					},   
					new Response.ErrorListener() {  
						public void onErrorResponse(VolleyError arg0) {  
							System.out.println("sorry,Error");  
						}  
					});  
			requestQueue.add(jsonObjectRequest);  
		}  


		private class FinishRefresh extends AsyncTask<Void,Void,Void>{

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

		private class MyAdapter extends BaseAdapter{
			private LayoutInflater mInflater;

			public MyAdapter(Context context) {
				// TODO Auto-generated constructor stub
				mInflater = LayoutInflater.from(context);
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
				if(convertView == null){
					viewHolder = new  ViewHolder();
					convertView = mInflater.inflate(R.layout.shoucang_item, null);
					viewHolder.title = (TextView) convertView.findViewById(R.id.title);
					viewHolder.content = (TextView) convertView.findViewById(R.id.content);

					convertView.setTag(viewHolder);
				}else{
					viewHolder = (ViewHolder) convertView.getTag();
				}
				viewHolder.title.setText(data.get(position).getTitle());
				return convertView;
			}

			class ViewHolder{
				TextView title;
				TextView content;
			}


		}

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.sick_titel_left_layout:
				finish();
				break;
			default:
				break;
			}
		}


	}