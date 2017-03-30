package com.ryan.slidefragment.tourongzi.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.activity.jurenmai.JuRenMaiXiangQing_Activity;
import com.ryan.slidefragment.tourongzi.activity.zhaozijin.ZhaoZijin_GuQuanTouZi_Activity;
import com.ryan.slidefragment.tourongzi.adapter.JuRenMaiAdapter;
import com.ryan.slidefragment.tourongzi.adapter.TouziAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XiangMuFragment extends Fragment {
    private PullToRefreshListView plistView;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    JuRenMaiAdapter adapter;
    HashMap<String, Integer> params = new HashMap<String, Integer>();
    private int pno=1;
    private ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout l=(LinearLayout) inflater.inflate(R.layout.fragment_xiang_mu, null);
        plistView = (PullToRefreshListView)l.findViewById(R.id.pullToRefresh);
        params.put("pno", pno);

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("提示");
        dialog.setMessage("正在加载...");

        String s = getUrl(params);

        getData(s);

        System.out.print("asAS="+s);
        plistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

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
                System.out.print("asAS=" + s1);
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
                System.out.print("asASsdadsd=" + s1);
                getData(s1);
                adapter.notifyDataSetChanged();
            }
        });

        plistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String id1=list.get(position-1).get("id").toString();
                Intent mIntent = new Intent(getActivity(),
                        JuRenMaiXiangQing_Activity.class);
                mIntent.putExtra("id", id1);
                startActivity(mIntent);
            }
        });
        return l;
    }
    private String getUrl(HashMap<String, Integer> params) {
        String url= Constants.TOURONGZI_JURENMAI;
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
                        String result = responseInfo.result.toString();
                        System.out.println("参数成功?" + result);
                        dialog.show();
                        JSonData(result);

                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        System.out.println("传递失败");
                    }
                });
    }

    private void JSonData(String result) {
        JSONObject ob;
        try {
            ob = new JSONObject(result);
            JSONObject o=ob.getJSONObject("date");
            String nodatatag = ob.getString("di");
            JSONArray ary=o.getJSONArray("jurenmai");
            for (int i = 0; i < ary.length(); i++) {
                JSONObject obj=ary.getJSONObject(i);
                String bie=obj.getString("bie");
                String capid=obj.getString("capid");
                String content=obj.getString("content");
                String id=obj.getString("id");
                String ming=obj.getString("ming");
                String site=obj.getString("site");
                String title=obj.getString("title");
                String url=obj.getString("url");
                String usersid=obj.getString("usersid");


                Map<String, Object> map = new HashMap<String, Object>();
                map.put("bie", bie);
                map.put("capid", capid);
                map.put("id", id);
                map.put("content", content);
                map.put("ming", ming);
                map.put("site", site);
                map.put("title", title);
                map.put("url", url);
                map.put("usersid", usersid);
                list.add(map);
            }
            adapter=new JuRenMaiAdapter(getActivity(),list);
            plistView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
            plistView.onRefreshComplete();
            if (nodatatag.equals("a")) {
                plistView.setMode(PullToRefreshBase.Mode.BOTH);

            } else {
                Toast.makeText(getActivity(), "没有更多数据了",
                        Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

}


