package com.ryan.slidefragment.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.base.BaseFragment;
import com.ryan.slidefragment.dao.HttpClientDao;
import com.ryan.slidefragment.domain.Fuwu_Bean;
import com.ryan.slidefragment.domain.Gridview_ServeBean;
import com.ryan.slidefragment.generaldemo.ActivityFuWu_hangyehuiyuan_Dialog;
import com.ryan.slidefragment.generaldemo.ActivityFuWu_zhanlanhui_Dialog;
import com.ryan.slidefragment.generaldemo.FuwuQianSanActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.TouRongZiActivity;
import com.ryan.slidefragment.utils.JsonUtils;
import com.ryan.slidefragment.utils.SharedPreferencesUtils;
import com.ryan.slidefragment.utils.ThreadUtils;
import com.volley.CacheUtils;
import com.volley.JsonJudger;

/**联盟服务*/
public class MemberFragment extends BaseFragment implements OnClickListener {
	private ImageWorker mImageLoader;
	private HashMap<String, String> map;
	private GridView gridview;
	private List<HashMap<String, String>> aa = new ArrayList<HashMap<String, String>>();
	private FuWuAdapter adapter = new FuWuAdapter();

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_message, null);
		mImageLoader = new ImageFetcher(getActivity());
		mImageLoader.setImageCache(ImageCache.getInstance(getActivity()));
		gridview = (GridView) view.findViewById(R.id.gridview1);
		// 取消GridView中Item选中时默认的背景色
		gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));

		//缓存当前页面数据
		String result = SharedPreferencesUtils.getString(mActivity,
				"Constants.FUWUGRIDVIEWqwe", "");
		if (!TextUtils.isEmpty(result)) {
			setData(result);
		}else{
			gridview();//接口
		}
		//gridview();
		gridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				switch (position) {
					case 0:
						intent(mActivity, ActivityFuWu_hangyehuiyuan_Dialog.class,
								0 + "");
						break;
					case 1:
						intent(mActivity, FuwuQianSanActivity.class, 1 + "");
						break;
					case 2:
						intent(mActivity, FuwuQianSanActivity.class, 2 + "");
						break;
					case 3:
						intent(mActivity, TouRongZiActivity.class, 3 + "");
						break;
					case 4:
						intent(mActivity, ActivityFuWu_zhanlanhui_Dialog.class,
								4 + "");
						break;
					case 5:
						intent(mActivity, ActivityFuWu_zhanlanhui_Dialog.class,
								5 + "");
						break;
					case 6:
						intent(mActivity, ActivityFuWu_zhanlanhui_Dialog.class,
								6 + "");
						break;
					case 7:
						intent(mActivity, ActivityFuWu_zhanlanhui_Dialog.class,
								7 + "");
						break;
					case 8:
						intent(mActivity, ActivityFuWu_zhanlanhui_Dialog.class,
								8 + "");
						break;
					/*case 9:
						intent(mActivity, ActivityFuWu_zhanlanhui_Dialog.class,
								9 + "");
						break;*/

				}

			}
		});
		return view;
	}

	/**
	 * 联网得到数据
	 */
	private void gridview() {
		final HashMap<String, String> map = new HashMap<String, String>();
		ThreadUtils.newCachedThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				final String resul = HttpClientDao.getListHttpClientPost(
						Constants.FUWUGRIDVIEW, map);
				boolean judger = JsonJudger.JsonJudger(resul, "code", "200");
				if (judger) {

					ThreadUtils.post(new Runnable() {
						public void run() {
							SharedPreferencesUtils.saveString(mActivity,
									"Constants.FUWUGRIDVIEWqwe",resul);
							setData(resul);
						}
					});
				}
			}
		});
	}

	/**
	 * 设置GridView数据
	 *
	 * @param result
	 */
	protected void setData(String result) {
		// TODO Auto-generated method stub
		Gridview_ServeBean gridviewserve = JsonUtils.parser(result,
				Gridview_ServeBean.class);

		aa.clear();

		for (Gridview_ServeBean.Date.Fuwu item : gridviewserve.date.fuwu) {
			map = new HashMap<String, String>();
			map.put("image", item.image);
			map.put("name", item.name);
			aa.add(map);

		}
		System.out.println("aaaaaa" + aa);
		gridview.setAdapter(adapter);
		// adapter.notifyDataSetChanged();
	}

	private class FuWuAdapter extends BaseAdapter {
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
		@SuppressLint("ViewHolder")
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getActivity(), R.layout.item_home, null);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_icon);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_name);
		//	ImageLoader.getInstance().displayImage(aa.get(position).get("image"), iv);
		mImageLoader.loadImage(aa.get(position).get("image"), iv,
					R.drawable.cy_jianzhucailiao_1);
			tv.setText(aa.get(position).get("name"));
			return view;
		}

	}

	// 判断是否已经登录，登录之后才能进入看介绍
	@SuppressWarnings("rawtypes")
	public void intent(Context context, Class clazz, String num) {
		String s = BaseApplication.name;
		if (s != null) {
			BaseApplication.fuwu = "";
			BaseApplication.fuwu = num;
			Intent i8 = new Intent(context, clazz);
			startActivity(i8);
		} else {
			Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
		}
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
}
