package com.ryan.slidefragment.tourongzi.activity.chenggonganli;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.adapter.MyAdapter_jishuzhanshi;
import com.ryan.slidefragment.generaldemo.ChenggonganlixiangqingActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.model.Person;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.adapter.ChengGongAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CengGongAnLiActivity_Tou extends Activity {
    private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv;
    private ImageView sick_title_left_img;
    private PullToRefreshListView pullToRefresh;
    private ChengGongAdapter adapter;
    // listView中item的数据 数据根据布局而定
    public static int pon = 1;
    private ProgressDialog dialog;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    // 封装参数集合
    HashMap<String, Integer> params = new HashMap<String, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ceng_gong_an_li);
        setView();
        params.put("pon", pon);

        dialog = new ProgressDialog(getApplicationContext());
        dialog.setTitle("提示");
        dialog.setMessage("正在加载...");

        String s = getUrl(params);

        getData(s);
        pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                // 上拉刷新
                list.clear();
                if (pon < 1) {
                    pon = 1;
                } else {
                    pon--;
                }
                params.put("pon", pon);
                String s1 = getUrl(params);
                System.out.print("asAS=" + s1);
                getData(s1);
                adapter.notifyDataSetChanged();

            }

            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub

                // 加载更多
                // adapter.notifyDataSetChanged();
                // params.clear();
                list.clear();
                pon++;
                params.put("pon", pon);
                String s1 = getUrl(params);
                System.out.print("asASsdadsd=" + s1);
                getData(s1);
                adapter.notifyDataSetChanged();
            }
        });

        /**
         * item的点击事件
         */
        pullToRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String id1 = list.get(position - 1).get("id").toString();

                Intent mIntent = new Intent(getApplicationContext(),
                        ChenggonganliActivity_XiangQing.class);
                mIntent.putExtra("id", id1);
                startActivity(mIntent);
            }
        });
      //  getData();
    }

    private void setView() {
        sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
        sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
        sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
        sick_title_left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
        sick_title_left_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sick_title_mid_tv.setText("成功案例");
        sick_title_right_tv.setVisibility(View.GONE);

        pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);


        // pullToRefresh.setMode(Mode.BOTH);
        // init();

    }

    private void getData(String url) {
        HttpUtils http=new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                url,
                new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result.toString();
                        System.out.println("参数成功?" + result);

                        JSONAnalysis(result);

                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        System.out.println("传递失败");
                    }
                });

    }


    /**
     * 拿到拼接后的url
     */
    private String getUrl(HashMap<String, Integer> params) {
        String PATH = Constants.TOURONGZI_CHENGGONG;
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



    /**
     * JSON解析
     *
     * @param result
     * @return
     */
    public void JSONAnalysis(String result) {
        JSONObject array = null;
        try {
            array = new JSONObject(result);
            JSONObject jj1_xinwen = array.getJSONObject("date");
            String aab = array.getString("di");
            JSONArray a1_xinwen = jj1_xinwen.getJSONArray("anli");
            for (int i = 0; i < a1_xinwen.length(); i++) {
                JSONObject objectOne = a1_xinwen.optJSONObject(i);
                String id = objectOne.optString("id");
                String content = objectOne.optString("content");
                String title = objectOne.optString("title");
                String fangshi = objectOne.optString("fangshi");
                String province = objectOne.optString("province");
                String clicks = objectOne.optString("clicks");
                String yongtu = objectOne.optString("yongtu");
                String createtime = objectOne.optString("createtime");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", id);
                map.put("content", content);
                map.put("name", title);
                map.put("fangshi", fangshi);
                map.put("province", province);
                map.put("clicks", clicks);
                map.put("yongtu", yongtu);
                map.put("createtime", createtime);
                list.add(map);
            }
            adapter = new ChengGongAdapter(
                    getApplicationContext(), list);
            pullToRefresh.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
            pullToRefresh.onRefreshComplete();
            if (aab.equals("a")) {
                pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);

            } else {
                Toast.makeText(getApplicationContext(), "没有更多数据了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.sick_title_left_img:
                finish();
                break;
            case R.id.sick_title_left_tv:
                finish();
                break;
            default:
                break;
        }
    }

}
