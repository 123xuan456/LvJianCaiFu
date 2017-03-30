package com.ryan.slidefragment.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.slidefragment.base.BaseFragment;
import com.ryan.slidefragment.generaldemo.ActivityChanPinListView;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;

public class ChanPinFragment extends BaseFragment implements OnClickListener,
		OnPageChangeListener {
	private ImageWorker mImageLoader;
	private LayoutInflater inflater;
	public List<HashMap<String, String>> aa = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> bb = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> cc = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> dd = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> ee = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> ff = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> gg = new ArrayList<HashMap<String, String>>();
	private static ImageLoader imageLoader = ImageLoader.getInstance();
	HashMap<String, Integer> params = new HashMap<String, Integer>();
	GgAdapter adapter = new GgAdapter();
	GgAdapter1 adapter1 = new GgAdapter1();
	GgAdapter2 adapter2 = new GgAdapter2();
	GgAdapter3 adapter3 = new GgAdapter3();
	GgAdapter4 adapter4 = new GgAdapter4();
	GgAdapter5 adapter5 = new GgAdapter5();
	GgAdapter6 adapter6 = new GgAdapter6();

	HashMap<String, String> map;

	@ViewInject(R.id.headcar1)
	private ImageView headcar1;
	@ViewInject(R.id.help_item1)
	private TextView help_item1;
	@ViewInject(R.id.help_item2)
	private TextView help_item2;
	@ViewInject(R.id.help_item3)
	private TextView help_item3;
	@ViewInject(R.id.help_item4)
	private TextView help_item4;
	// @ViewInject(R.id.help_item5)
	// private TextView help_item5;
	@ViewInject(R.id.help_item6)
	private TextView help_item6;
	@ViewInject(R.id.help_item7)
	private TextView help_item7;
	@ViewInject(R.id.haha)
	private LinearLayout haha;
	@ViewInject(R.id.gridview)
	private GridView gridview;
	@ViewInject(R.id.gridview0)
	private GridView gridview0;
	@ViewInject(R.id.gridview9)
	private GridView gridview9;
	@ViewInject(R.id.gridview8)
	private GridView gridview8;
	@ViewInject(R.id.gridview7)
	private GridView gridview7;
	@ViewInject(R.id.gridview6)
	private GridView gridview6;
	@ViewInject(R.id.gridview5)
	private GridView gridview5;
	@ViewInject(R.id.qwe)
	private LinearLayout qwe;
	@ViewInject(R.id.rty)
	private LinearLayout rty;
	@ViewInject(R.id.poi)
	private LinearLayout poi;
	@ViewInject(R.id.zxc)
	private LinearLayout zxc;
	@ViewInject(R.id.mnb)
	private LinearLayout mnb;
	@ViewInject(R.id.qaz)
	private LinearLayout qaz;

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.chanpinzhanshi, null);
		mImageLoader = new ImageFetcher(getActivity());
		mImageLoader.setImageCache(ImageCache.getInstance(getActivity()));
		ViewUtils.inject(this, view);
		headcar1.setOnClickListener(this);
		help_item1.setOnClickListener(this);
		help_item2.setOnClickListener(this);
		help_item3.setOnClickListener(this);
		help_item4.setOnClickListener(this);
		// help_item5.setOnClickListener(this);
		help_item6.setOnClickListener(this);
		help_item7.setOnClickListener(this);
		// gridview = (GridView)view.findViewById(R.id.gridview);

		inflater = LayoutInflater.from(getActivity());

		gv();

		return view;

	}

	// GV的点击事件
	private void gv() {
		gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), aa.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(aa.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

		gridview0.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview0.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), bb.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(bb.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

		gridview9.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview9.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), cc.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(cc.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

		gridview8.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview8.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), dd.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(dd.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

		gridview7.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview7.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), ee.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(ee.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

		gridview6.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview6.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), ff.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(ff.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

		gridview5.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridview5.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// String qws = aa.get(position).get("id");
				// Toast.makeText(getActivity(), gg.get(position).get("id"),
				// Toast.LENGTH_SHORT).show();
				Person mPerson = new Person();
				mPerson.setName(gg.get(position).get("id"));
				Intent mIntent = new Intent(getActivity(),
						ActivityChanPinListView.class);
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("pr", mPerson);
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
			}
		});

	}

	// 接口
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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (aa.size() != 0) {
					aa.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("imgurl", i1);
					map.put("id", id);
					aa.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter.notifyDataSetChanged();

		}

	}

	// 接口2
	class Gridview1 extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (bb.size() != 0) {
					bb.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("imgurl", i1);
					map.put("id", id);
					bb.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter1.notifyDataSetChanged();

		}

	}

	// 接口2222
	class Gridview2 extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (cc.size() != 0) {
					cc.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("imgurl", i1);
					map.put("id", id);
					cc.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter2.notifyDataSetChanged();

		}

	}

	// 接口3333
	class Gridview3 extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (dd.size() != 0) {
					dd.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("imgurl", i1);
					map.put("id", id);
					dd.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter3.notifyDataSetChanged();

		}

	}

	// 接口4444
	class Gridview4 extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (ee.size() != 0) {
					ee.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("id", id);
					map.put("imgurl", i1);
					ee.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter4.notifyDataSetChanged();

		}

	}

	// 接口5555
	class Gridview5 extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (ff.size() != 0) {
					ff.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("imgurl", i1);
					map.put("id", id);
					ff.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter5.notifyDataSetChanged();

		}

	}

	// 接口6666
	class Gridview6 extends AsyncTask<String, Void, String> {

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
			super.onPostExecute(result);
			try {
				JSONObject j = new JSONObject(result);
				JSONObject jj1 = j.getJSONObject("date");
				//
				JSONArray a1 = jj1.getJSONArray("feileixx");
				if (gg.size() != 0) {
					gg.clear();
				}
				for (int i = 0; i < a1.length(); i++) {
					JSONObject js1 = a1.optJSONObject(i);

					String s1 = js1.getString("name");
					String i1 = js1.getString("imgurl");
					String id = js1.getString("id");
					System.out.println(i1);
					map = new HashMap<String, String>();
					map.put("name", s1);
					map.put("imgurl", i1);
					map.put("id", id);
					gg.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adapter6.notifyDataSetChanged();

		}

	}

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 点击事件
	 */
	public void onClick(View v) {
		Gridview gWeather;
		switch (v.getId()) {
		// case R.id.headcar1:
		// GouWuCheFragment g = new GouWuCheFragment();
		// fragmentSwitch((SlidingFragmentActivity) getActivity(),
		// ChanPinFragment.this, new GouWuCheFragment(),
		// R.id.contentframe, true, true);
		// break;
		// 111111
		case R.id.help_item1:
			setShow(haha);
			// String url1 = Constants.CHANGRIDVIEW;
			aa.clear();
			params.clear();
			params.put("id", 51);
			gWeather = new Gridview();
			gWeather.execute(getUrl(params));
			// asd
			gridview.setAdapter(adapter);
			break;
		// 2222222
		case R.id.help_item2:
			setShow(qwe);
			bb.clear();
			params.clear();
			params.put("id", 72);
			// gWeather = new Gridview();
			Gridview1 gWeather1 = new Gridview1();
			gWeather1.execute(getUrl(params));
			// asd
			gridview0.setAdapter(adapter1);
			break;
		// 33333
		case R.id.help_item3:

			setShow(rty);
			cc.clear();
			params.clear();
			params.put("id", 80);
			Gridview2 gWeather2 = new Gridview2();
			gWeather2.execute(getUrl(params));
			gridview9.setAdapter(adapter2);
			break;
		// 4444
		case R.id.help_item4:
			setShow(poi);
			dd.clear();
			params.clear();
			params.put("id", 88);
			Gridview3 gWeather3 = new Gridview3();
			gWeather3.execute(getUrl(params));
			gridview8.setAdapter(adapter3);
			break;
		// 5555
		// case R.id.help_item5:
		// setShow(zxc);
		// ee.clear();
		// params.clear();
		// params.put("id", 99);
		// Gridview4 gWeather4 = new Gridview4();
		// gWeather4.execute(getUrl(params));
		// // asd
		// gridview7.setAdapter(adapter4);
		// break;
		// 6666
		case R.id.help_item6:
			setShow(mnb);
			ff.clear();
			params.clear();
			params.put("id", 136);
			Gridview5 gWeather5 = new Gridview5();
			gWeather5.execute(getUrl(params));
			gridview6.setAdapter(adapter5);
			break;
		// 77777
		case R.id.help_item7:
			setShow(qaz);
			gg.clear();
			params.clear();
			params.put("id", 141);
			Gridview6 gWeather6 = new Gridview6();
			gWeather6.execute(getUrl(params));
			gridview5.setAdapter(adapter6);
			break;

		default:
			break;
		}

	}

	public class GgAdapter extends BaseAdapter {
		public GgAdapter() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return aa.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

//			imageLoader.displayImage(aa.get(position).get("imgurl"), iv);
			 mImageLoader.loadImage(aa.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(aa.get(position).get("name"));

			return view;
		}

	}

	public class GgAdapter1 extends BaseAdapter {
		public GgAdapter1() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return bb.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

//			imageLoader.displayImage(bb.get(position).get("imgurl"), iv);
			 mImageLoader.loadImage(bb.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(bb.get(position).get("name"));

			return view;
		}

	}

	// 真正的2222
	public class GgAdapter2 extends BaseAdapter {
		public GgAdapter2() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return cc.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

//			imageLoader.displayImage(cc.get(position).get("imgurl"), iv);
			mImageLoader.loadImage(cc.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(cc.get(position).get("name"));

			return view;
		}

	}

	// 333
	public class GgAdapter3 extends BaseAdapter {
		public GgAdapter3() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return dd.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

//			imageLoader.displayImage(dd.get(position).get("imgurl"), iv);
			mImageLoader.loadImage(dd.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(dd.get(position).get("name"));

			return view;
		}

	}

	// 444
	public class GgAdapter4 extends BaseAdapter {
		public GgAdapter4() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return ee.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

//			imageLoader.displayImage(ee.get(position).get("imgurl"), iv);
			mImageLoader.loadImage(ee.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(ee.get(position).get("name"));

			return view;
		}

	}

	// 5555
	public class GgAdapter5 extends BaseAdapter {
		public GgAdapter5() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return ff.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

//			imageLoader.displayImage(ff.get(position).get("imgurl"), iv);
			mImageLoader.loadImage(ff.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(ff.get(position).get("name"));

			return view;
		}

	}

	// 6666
	public class GgAdapter6 extends BaseAdapter {
		public GgAdapter6() {
			// TODO Auto-generated constructor stub
		}

		// 返回有多少个条目
		public int getCount() {
			return gg.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// 返回每个条目的view对象
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(),
					R.layout.item_home_chengyuan, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
			// BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
			// bitmapUtils.display(iv,aa.get(position).get("image"));

			imageLoader.displayImage(gg.get(position).get("imgurl"), iv);
			mImageLoader.loadImage(gg.get(position).get("imgurl"), iv, R.drawable.cy_jianzhucailiao_1);
			tv.setText(gg.get(position).get("name"));

			return view;
		}

	}

	/**
	 * 拿到拼接后的url
	 */
	private String getUrl(HashMap<String, Integer> params) {
		// private static String PATH =
		// "http://192.168.0.110:8080/lvjiancfAPP/newsselectxinwenquanbu";
		String PATH = Constants.CHANGRIDVIEW;
		// String PATH1 = Constants.JISHUZHANSHIXIANGQING;
		// String PATH = Constants.URL_CS_DATA;
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
			PATH += sb.toString();
		}
		return PATH;
	}

	// fragment跳转fragmentde 的方法
	public static void fragmentSwitch(SlidingFragmentActivity fragmentActivity,
			Fragment ChanPinFragment, Fragment GouWuCheFragment, int id,
			boolean isAddToBackStack, boolean isAnimation) {

		if (fragmentActivity == null || GouWuCheFragment == null) {
			return;
		}
		String tag = ChanPinFragment.getClass().getName();
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager()
				.beginTransaction();
		if (!GouWuCheFragment.isAdded()) {
			// 先判断是否被add过
			if (ChanPinFragment == null) {
				ft.add(id, GouWuCheFragment, tag).show(GouWuCheFragment);
			} else {
				// 隐藏当前的fragment，add下一个ChanPinFragment
				// ft.hide(ChanPinFragment).add(id,GouWuCheFragment,tag);
				ft.add(id, GouWuCheFragment, tag);
			}
		} else {
			if (ChanPinFragment == null) {
				ft.show(GouWuCheFragment);
			} else {
				// 隐藏当前的fragment，显示下一个
				// ft.hide(ChanPinFragment).show(GouWuCheFragment);
				ft.show(GouWuCheFragment);
			}
		}
		if (isAddToBackStack) {
			ft.addToBackStack(tag);
		}
		ft.commitAllowingStateLoss();
	}

	// /** 点击事件 */
	// public void onClick(View v) {
	//
	//
	// }

	// 显示与隐藏
	private void setShow(View v) {

		if (v.getVisibility() == View.GONE) {
			v.setVisibility(View.VISIBLE);
		} else {
			v.setVisibility(View.GONE);
			// ((ViewGroup) v).removeAllViews();
		}
	}

}
